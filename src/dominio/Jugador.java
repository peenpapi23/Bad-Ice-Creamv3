package dominio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import presentacion.PanelDeJuego;
import presentacion.Teclado;

public class Jugador extends Entidad{
	
	PanelDeJuego panelJuego;
	Teclado tecla;
	
	public final int camaraX;
	public final int camaraY;
	public int puntuacion = 0;
	private int countObjet = 0;  // Cuenta cuántas frutas llevas (para saber si ganaste)
	
	public Jugador(PanelDeJuego panelJuego, Teclado teclado) {
		
		this.panelJuego = panelJuego;
		this.tecla = teclado;
		
		camaraX = panelJuego.anchoPantalla/2 - (panelJuego.originalTamañoPixel/2);
		camaraY =panelJuego.largoPantalla/2 - (panelJuego.originalTamañoPixel/2);
		
		areaSolida = new Rectangle(8,16,32,32);
		
		
		defaults();
		imagenJugador();
		
	}
	
	public void defaults() {
		
		mundoX = panelJuego.originalTamañoPixel * 9;
		mundoY = panelJuego.originalTamañoPixel * 11;
		velocidad =4;
		direccion = "abajo";
		
	}
	
	public void imagenJugador(){
		
		try {
			arriba1 = ImageIO.read(getClass().getResourceAsStream("/jugador/arriba1.png"));
			arriba2 = ImageIO.read(getClass().getResourceAsStream("/jugador/arriba2.png"));
			abajo1 = ImageIO.read(getClass().getResourceAsStream("/jugador/abajo1.png"));
			abajo2 = ImageIO.read(getClass().getResourceAsStream("/jugador/abajo2.png"));
			derecha1 = ImageIO.read(getClass().getResourceAsStream("/jugador/derecha1.png"));
			derecha2 = ImageIO.read(getClass().getResourceAsStream("/jugador/derecha2.png"));
			izquierda1 = ImageIO.read(getClass().getResourceAsStream("/jugador/izquierda1.png"));
			izquierda2 = ImageIO.read(getClass().getResourceAsStream("/jugador/izquierda2.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
public void almacenar(int i) {
        
        if(i != 999) {
            String nombreObjeto = panelJuego.obj[i].nombre;
            
            switch(nombreObjeto) {
                
                case "Banana":
                    // ANTES: countObjet++;
                    
                    // AHORA (Corregido según PDF):
                    puntuacion += 100; // Sumar 100 puntos
                    countObjet++;      // Seguir contando frutas para saber cuándo ganar
                    
                    panelJuego.obj[i] = null; // Borrar la banana
                    System.out.println("¡Comiste Banana! Puntos: " + puntuacion);
                    break;
                    
                case "Uva":
                    puntuacion += 50;
                    countObjet++; // También deberías contar las uvas para el total
                    panelJuego.obj[i] = null;
                    System.out.println("¡Comiste Uva! Puntos: " + puntuacion);
                    break;
            }
        }
    }

	
public void actualizar() {
    
    // Detección de teclas (Igual que antes)
    if (tecla.arriba) direccion = "arriba";
    else if (tecla.abajo) direccion = "abajo";
    else if (tecla.izquierda) direccion = "izquierda";
    else if (tecla.derecha) direccion = "derecha";

    if (tecla.arriba || tecla.abajo || tecla.izquierda || tecla.derecha) {

        // 1. Chequear Colisión con Paredes
        colisionOn = false;
        panelJuego.colision.check(this);

        // 2. ¡NUEVO! Chequear Colisión con Objetos (Frutas)
        // Esto detecta si tocas algo y devuelve su índice (o 999 si nada)
        int objIndex = panelJuego.colision.checkObjeto(this, true);
        
        // 3. ¡NUEVO! Cobrar la fruta
        almacenar(objIndex);

        // 4. Mover si no hay pared
        if(!colisionOn) {
            switch(direccion) {
            case "arriba": mundoY -= velocidad; break;
            case "abajo": mundoY += velocidad; break;
            case "izquierda": mundoX -= velocidad; break;
            case "derecha": mundoX += velocidad; break;
            }
        }

        // Animación
        count++;
        if(count > 12) {
            numeroSprite = (numeroSprite == 1 ? 2 : 1);
            count = 0;
        }
    }
}

	
	public void dibujo(Graphics2D graficos2D) {
		
		BufferedImage imagen = null;
		
		switch(direccion) {
		case"arriba":
			if (numeroSprite==1) {
				imagen = arriba1;
			}
			if (numeroSprite == 2) {
				imagen = arriba2;
			}
			break;
		case"abajo":
			if (numeroSprite==1) {
				imagen = abajo1;
			}
			if (numeroSprite == 2) {
				imagen = abajo2;
			}
			break;
		case"izquierda":
			if (numeroSprite==1) {
				imagen = izquierda1;
			}
			if (numeroSprite == 2) {
				imagen = izquierda2;
			}
			break;
		case"derecha":
			if (numeroSprite==1) {
				imagen = derecha1;
			}
			if (numeroSprite == 2) {
				imagen = derecha2;
			}
			break;
		}
		
		graficos2D.drawImage(imagen,camaraX,camaraY,panelJuego.originalTamañoPixel,panelJuego.originalTamañoPixel,null);
	}
}
