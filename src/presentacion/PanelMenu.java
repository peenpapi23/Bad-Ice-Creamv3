package presentacion;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelMenu extends JPanel {

    // --- ESTADOS INTERNOS DEL MENÚ ---
    private static final int ESTADO_INTRO = 0;
    private static final int ESTADO_MENU = 1;
    private static final int ESTADO_MODOS = 2;
    private static final int ESTADO_SELECCION = 3;
    
    private int estadoActual = ESTADO_INTRO;

    // --- INTERFAZ PARA COMUNICARSE CON EL MAIN ---
    public interface ListenerInicioJuego {
        void iniciarJuego(); // Método que llamaremos al terminar el menú
    }
    private ListenerInicioJuego listener;

    // --- RECURSOS ---
    private Image imagenFondo; 
    private BufferedImage imagenMenu;
    private Font fuenteMenu = new Font("Arial", Font.BOLD, 20); // Fuente por defecto
    private Font fuenteTitulo = new Font("Arial", Font.BOLD, 14);
    private GestorSonido musicaFondo = new GestorSonido();

    // --- DIMENSIONES (Ajústalas al tamaño del juego de tu amigo) ---
    private final int ANCHO_MUNDO = 800; // EJEMPLO
    private final int ALTO_MUNDO = 600;  // EJEMPLO
    private int menuX, menuY;
    private final int menuAncho = 420;
    private final int menuAlto = 280;

    public PanelMenu(ListenerInicioJuego listener) {
        this.listener = listener;
        this.setPreferredSize(new Dimension(ANCHO_MUNDO, ALTO_MUNDO));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        // Centrado
        menuX = (ANCHO_MUNDO - menuAncho) / 2;
        menuY = ((ALTO_MUNDO - menuAlto) / 2) + 60;

        cargarImagenesUI();

        // 1. TEMPORIZADOR PARA LA INTRO (GIF)
        // Muestra el GIF 4 segundos y pasa al menú
        Timer timerIntro = new Timer(4000, e -> {
            estadoActual = ESTADO_MENU;
            repaint();
        });
        timerIntro.setRepeats(false);
        timerIntro.start();
        
        
        musicaFondo.setArchivo(0); // Carga menu.wav
        musicaFondo.play();
        musicaFondo.loop();

        // 2. DETECTOR DE CLICS (Lógica de navegación)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarClic(e.getX(), e.getY());
            }
        });
    }

    private void cargarImagenesUI() {
        try {
            // Ajusta las rutas según donde tu amigo tenga la carpeta 'ui'
            imagenFondo = new ImageIcon(getClass().getResource("/ui/animado.gif")).getImage();
            imagenMenu = ImageIO.read(getClass().getResourceAsStream("/ui/menu_limpio.png"));
        } catch (Exception e) {
            System.out.println("Error cargando imágenes. Verifica las rutas /ui/");
        }
    }

    // --- LÓGICA DE CLICS ---
    private void manejarClic(int x, int y) {
        if (estadoActual == ESTADO_MENU) {
            // Coordenadas APROXIMADAS del botón "JUGAR" (Ajusta según tu imagen)
            if (x > menuX + 100 && x < menuX + 300 && y > menuY + 70 && y < menuY + 110) {
                estadoActual = ESTADO_MODOS;
                repaint();
            }
            // Botón SALIR
            else if (x > menuX + 100 && x < menuX + 300 && y > menuY + 170 && y < menuY + 210) {
                System.exit(0);
            }
        } 
        else if (estadoActual == ESTADO_MODOS) {
            // Si hace clic en cualquier modo, pasamos a selección de color
            // (Aquí puedes guardar qué modo eligió en una variable estática si quieres)
            if (y > menuY + 100 && y < menuY + 220) {
                estadoActual = ESTADO_SELECCION;
                repaint();
            }
        } 
        else if (estadoActual == ESTADO_SELECCION) {
            // Si hace clic en los colores (Ajusta coordenadas)
            if (y > menuY + 100 && y < menuY + 150) {
                // AQUÍ TERMINA EL MENÚ -> LLAMAMOS AL MAIN
            	
            	musicaFondo.stop();
                listener.iniciarJuego(); 
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (estadoActual == ESTADO_INTRO) {
            dibujarBaseMenu(g2); // Asumo que el intro es solo el GIF de fondo
        } else if (estadoActual == ESTADO_MENU) {
            dibujarMenu(g2);
        } else if (estadoActual == ESTADO_MODOS) {
            dibujarModos(g2);
        } else if (estadoActual == ESTADO_SELECCION) {
            dibujarSeleccion(g2);
        }
        g2.dispose();
    }

    // --- TUS MÉTODOS DE DIBUJO (Copiados de tu código) ---
    private void dibujarBaseMenu(Graphics2D g2) {
        if (imagenFondo != null) {
            g2.drawImage(imagenFondo, 0, 0, ANCHO_MUNDO, ALTO_MUNDO, this);
        }
        if (imagenMenu != null && estadoActual != ESTADO_INTRO) {
            g2.drawImage(imagenMenu, menuX, menuY, menuAncho, menuAlto, null);
        }
    }

    private void dibujarMenu(Graphics2D g2) {
        dibujarBaseMenu(g2);
        g2.setFont(fuenteMenu);
        g2.setColor(Color.BLACK);
        g2.drawString("JUGAR", menuX + 160, menuY + 90);
        g2.drawString("OPCIONES", menuX + 140, menuY + 140);
        g2.drawString("SALIR", menuX + 165, menuY + 190);
    }

    private void dibujarModos(Graphics2D g2) {
        dibujarBaseMenu(g2);
        g2.setColor(Color.BLACK);
        g2.setFont(fuenteMenu); g2.drawString("MODO DE JUEGO", menuX + 110, menuY + 60);
        g2.setFont(fuenteTitulo);
        g2.drawString("> PLAYER VS PLAYER", menuX + 110, menuY + 110);
        g2.drawString("> MACHINE VS MACHINE", menuX + 110, menuY + 150);
        g2.drawString("> PLAYER VS MACHINE", menuX + 110, menuY + 190);
    }

    private void dibujarSeleccion(Graphics2D g2) {
        dibujarBaseMenu(g2);
        g2.setColor(Color.BLACK);
        g2.setFont(fuenteMenu);
        g2.drawString("ELIGE TU COLOR", menuX + 110, menuY + 60);

        // Cuadros de colores
        g2.setColor(Color.WHITE); g2.fillRect(menuX + 80, menuY + 100, 50, 50);
        g2.setColor(new Color(101, 67, 33)); g2.fillRect(menuX + 180, menuY + 100, 50, 50);
        g2.setColor(new Color(255, 105, 180)); g2.fillRect(menuX + 280, menuY + 100, 50, 50);

        // Bordes
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
        g2.drawRect(menuX + 80, menuY + 100, 50, 50);
        g2.drawRect(menuX + 180, menuY + 100, 50, 50);
        g2.drawRect(menuX + 280, menuY + 100, 50, 50);
        
        g2.setFont(fuenteTitulo);
        g2.drawString("Clic en un color para iniciar", menuX + 90, menuY + 200);
    }
}