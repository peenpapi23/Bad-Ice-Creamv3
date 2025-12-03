package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import dominio.Colision;
import dominio.Configuracion;
import dominio.Enemigo;
import dominio.Jugador;
import dominio.SuperObjeto;

public class PanelDeJuego extends JPanel implements Runnable{
	
	//Configuracion de Pantalla
	final int tamañoPixel = 16;
	final int escala = 3; 
	public int nivelActual = 1;
	
	public final int originalTamañoPixel = tamañoPixel * escala; //Seria de 48 pixeles
	final int maximoColumna = 16;
	final int maximoFila = 12;
	private GestorSonido sonidoJuego = new GestorSonido();
	
	public final int anchoPantalla = originalTamañoPixel * maximoColumna; //768 pixeles
	public final int largoPantalla = originalTamañoPixel * maximoFila; // 576 pixeles
	
	
	//Parametros Mundo
	public final int maximoMundoColumna = 18;
	public final int maximoMundoFila = 18; 
	public final int mundoAncho = originalTamañoPixel * maximoMundoColumna;
	public final int mundoLargo = maximoMundoFila * maximoMundoFila;
	
	//FPS
	int FPS = 60;
	
	
	public Nivel1 nivel = new Nivel1(this);
	Teclado tecla = new Teclado();
	Thread hilos;
	public Colision colision = new Colision(this);
	
	
	public Jugador jugador = new Jugador(this, tecla);
	public SuperObjeto obj[] = new SuperObjeto[10];
	public Configuracion conf = new Configuracion(this);
	
	public Enemigo enemigos[] = new Enemigo[10];
	
	
	
	
	public PanelDeJuego() {
		this.setPreferredSize(new Dimension(anchoPantalla,largoPantalla));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(tecla);
		this.setFocusable(true);
		sonidoJuego.setArchivo(1); // Carga juego.wav
        sonidoJuego.play();
        sonidoJuego.loop();
	}
	
	public void setObjetos() {
		conf.cargarNivel(nivelActual);
	}
	
	
	public void iniciarHilos() {
		hilos = new Thread(this);
		hilos.start();
	}

	@Override
	public void run() {
		
		double intervaloDeDibujo = 1000000000 / FPS;
		double delta = 0;
		long ultimaHora = System.nanoTime();
		long horaActual;
		
		while (hilos != null) {
			
			horaActual = System.nanoTime();
			delta += (horaActual - ultimaHora)/ intervaloDeDibujo;
			
			ultimaHora = horaActual;
			
			if(delta >= 1) {
				actualizar();
				repaint();
				delta--;
			}
		}
		
	}
	
public void actualizar() {
    // 1. Mover Jugador
    jugador.actualizar();

    // 2. Mover Enemigos y chequear colisión
    for(int i = 0; i < enemigos.length; i++) {
        if(enemigos[i] != null) {
            enemigos[i].actualizar();
            
            // Detectar muerte por choque
            java.awt.Rectangle rJugador = new java.awt.Rectangle(
                jugador.mundoX + jugador.areaSolida.x, 
                jugador.mundoY + jugador.areaSolida.y, 
                jugador.areaSolida.width, jugador.areaSolida.height
            );
            java.awt.Rectangle rEnemigo = new java.awt.Rectangle(
                enemigos[i].mundoX + enemigos[i].areaSolida.x, 
                enemigos[i].mundoY + enemigos[i].areaSolida.y, 
                enemigos[i].areaSolida.width, enemigos[i].areaSolida.height
            );

            if(rJugador.intersects(rEnemigo)) {
                System.out.println("💀 ¡El Troll te ha matado!");
                jugador.defaults(); // Reiniciar al jugador
            }
        }
    }
    
    // 3. Detectar Victoria
    int frutasRestantes = 0;
    for(int i = 0; i < obj.length; i++) {
        if(obj[i] != null) frutasRestantes++;
    }
    if(frutasRestantes == 0) {
        System.out.println("🏆 ¡Nivel Completado!");
        avanzarSiguienteNivel();
    }
}
	@Override 
	public void paintComponent(Graphics graficos) {
	    super.paintComponent(graficos);
	    Graphics2D graficos2D = (Graphics2D) graficos;
	    
	    // 1. Dibujar Nivel (Fondo)
	    nivel.dibujo(graficos2D);
	    
	    // 2. DIBUJAR OBJETOS (¡ESTO FALTABA!)
	    for(int i = 0; i < obj.length; i++) {
	        if(obj[i] != null) {
	            obj[i].dibujo(graficos2D, this);
	        }
	    }
	    
	    for(int i = 0; i < enemigos.length; i++) {
	        if(enemigos[i] != null) {
	            enemigos[i].dibujo(graficos2D);
	        }
	    }
	    
	    // 3. Dibujar Jugador
	    jugador.dibujo(graficos2D);

	    graficos2D.dispose();
	}
	
	public void avanzarSiguienteNivel() {
        nivelActual++; // Aumentamos 1 -> 2
        
        // 1. Cargamos el nuevo mapa de texto
        // Nota: Tu clase Nivel1 debería tener el método cargarMapa público
        nivel.cargarMapa("/mapa/nivel" + nivelActual + ".txt");
        
        // 2. Ponemos las frutas del nuevo nivel
        setObjetos();
        
        // 3. Reiniciamos al jugador a la posición inicial
        jugador.defaults();
        
        System.out.println("¡Bienvenido al Nivel " + nivelActual + "!");
    }

	
}
