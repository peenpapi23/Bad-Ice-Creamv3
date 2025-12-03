package dominio;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import presentacion.PanelDeJuego;

public class SuperObjeto {
	public BufferedImage imagen;
	public String nombre;
	public boolean colision = false;
	public int mundoX, mundoY;
	
	public void dibujo(Graphics2D graficos2D,PanelDeJuego panel) {
		
		int pantallaX = mundoX - panel.jugador.mundoX + panel.jugador.camaraX;
		int pantallaY = mundoY - panel.jugador.mundoY + panel.jugador.camaraY;
		
		graficos2D.drawImage(imagen,pantallaX,pantallaY,panel.originalTamañoPixel, panel.originalTamañoPixel, null );
		
		
	}
}
