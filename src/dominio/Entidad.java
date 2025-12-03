package dominio;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entidad {
	public int mundoX, mundoY;
	public int velocidad;
	
	public BufferedImage arriba1,arriba2,abajo1,abajo2,izquierda1,izquierda2,derecha1,derecha2;
	public String direccion;
	
	public int count = 0;
	public int numeroSprite = 1;
	
	//Colision
	public Rectangle areaSolida;
	public boolean colisionOn = false;
	public int areaSolidaX, areaSolidaY; 
  
	
	
}
