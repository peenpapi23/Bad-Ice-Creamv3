package presentacion;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Nivel1 {
	
	PanelDeJuego panel;
	public  Mapa[] mapa;
	public int mapaNivel1[][];
	
	public Nivel1(PanelDeJuego panel) {
		
		this.panel = panel;
		mapa = new Mapa[20];
		mapaNivel1 = new int[panel.maximoMundoColumna][panel.maximoMundoFila];
		
		
		obtenerImagen();
		cargarMapa("/mapa/nivel1.txt");
		
	}
	
	public void obtenerImagen() {
		try {
			
			mapa[0] = new Mapa();
			mapa[0].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/bordes.png"));
			mapa[0].colision = true;
			
			mapa[1] = new Mapa();
			mapa[1].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/bordes2.png"));
			mapa[1].colision = true;
			
			mapa[3] = new Mapa();
			mapa[3].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/nieve.png"));
			
			mapa[4] = new Mapa();
			mapa[4].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/nieve2.png"));
			
			mapa[5] = new Mapa();
			mapa[5].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/nieve3.png"));
			
			mapa[6] = new Mapa();
			mapa[6].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/nieve4.png"));
			
			mapa[7] = new Mapa();
			mapa[7].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/nieve5.png"));
			
			mapa[8] = new Mapa();
			mapa[8].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/nieve6.png"));
			
			mapa[9] = new Mapa();
			mapa[9].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/nieve7.png"));
			
			mapa[2] = new Mapa();
			mapa[2].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/nieve8.png"));
			
			mapa[10] = new Mapa();
			mapa[10].imagen = ImageIO.read(getClass().getResourceAsStream("/tile/nieve9.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void cargarMapa(String archivo) {
		
		try {
			InputStream cargarMapa = getClass().getResourceAsStream(archivo);
			BufferedReader lector = new BufferedReader(new InputStreamReader(cargarMapa));
			
			int columna = 0;
			int fila = 0;
			
			while(columna < panel.maximoMundoColumna && fila < panel.maximoMundoFila) {
				String linea = lector.readLine();
				
				while(columna < panel.maximoMundoColumna) {
					
					String numeros[] = linea.split(" ");
					
					int num = Integer.parseInt(numeros[columna]);
					
					mapaNivel1[columna][fila] = num;
					columna++;
					
				}
				if (columna == panel.maximoMundoColumna) {
					columna = 0;
					fila++;
				}
				
			}
			lector.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void dibujo(Graphics2D graficos2D) {
		
		int mundoColumna = 0;
		int mundoFila = 0;

		
		while (mundoColumna < panel.maximoMundoColumna && mundoFila < panel.maximoMundoFila) {
			
			int numeroMap = mapaNivel1[mundoColumna][mundoFila];
			
		 	int mundoX = mundoColumna * panel.originalTamañoPixel;
			int mundoY = mundoFila * panel.originalTamañoPixel;
			int pantallaX = mundoX - panel.jugador.mundoX + panel.jugador.camaraX;
			int pantallaY = mundoY - panel.jugador.mundoY + panel.jugador.camaraY;
			
			graficos2D.drawImage(mapa[numeroMap].imagen,pantallaX,pantallaY,panel.originalTamañoPixel, panel.originalTamañoPixel, null );
			mundoColumna++;			
			if (mundoColumna == panel.maximoMundoColumna) {
				mundoColumna = 0;
				mundoFila++;		
			}
			
		}
		
	}
}
