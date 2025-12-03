package dominio;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import presentacion.PanelDeJuego;

// 1. EXTENDS ENTIDAD ES OBLIGATORIO
public class Enemigo extends Entidad {

    PanelDeJuego panel;
    public String tipo; 

    public Enemigo(PanelDeJuego panel, String tipo) {
        this.panel = panel;
        this.tipo = tipo;
        
        // 2. PRIMERO CREAMOS EL RECTÁNGULO
        areaSolida = new Rectangle(8, 16, 32, 32);
        
        // 3. LUEGO USAMOS SUS VALORES (Esto fallaría si no heredamos de Entidad)
        areaSolidaX = areaSolida.x;
        areaSolidaY = areaSolida.y;
        
        establecerValoresPorDefecto();
        cargarImagen();
    }

    public void establecerValoresPorDefecto() {
        mundoX = panel.originalTamañoPixel * 5;
        mundoY = panel.originalTamañoPixel * 5;
        velocidad = 2;
        direccion = "abajo";
    }

    public void cargarImagen() {
        try {
            // Nota: Asegúrate de tener estas carpetas o el juego dará error al cargar
            String ruta = "/enemigos/" + tipo.toLowerCase() + "/";
            
            abajo1 = ImageIO.read(getClass().getResourceAsStream(ruta + "abajo1.png"));
            abajo2 = ImageIO.read(getClass().getResourceAsStream(ruta + "abajo2.png"));
            arriba1 = ImageIO.read(getClass().getResourceAsStream(ruta + "arriba1.png"));
            arriba2 = ImageIO.read(getClass().getResourceAsStream(ruta + "arriba2.png"));
            izquierda1 = ImageIO.read(getClass().getResourceAsStream(ruta + "izquierda1.png"));
            izquierda2 = ImageIO.read(getClass().getResourceAsStream(ruta + "izquierda2.png"));
            derecha1 = ImageIO.read(getClass().getResourceAsStream(ruta + "derecha1.png"));
            derecha2 = ImageIO.read(getClass().getResourceAsStream(ruta + "derecha2.png"));
            
        } catch (Exception e) {
            System.out.println("No se encontraron las imágenes para el enemigo: " + tipo);
        }
    }

    public void setAccion() {
        count++;
        if(count > 120) { 
            java.util.Random random = new java.util.Random();
            int i = random.nextInt(100) + 1;
            
            if(i <= 25) direccion = "arriba";
            if(i > 25 && i <= 50) direccion = "abajo";
            if(i > 50 && i <= 75) direccion = "izquierda";
            if(i > 75) direccion = "derecha";
            
            count = 0;
        }
    }
    
    public void actualizar() {
        setAccion();
        colisionOn = false;
        panel.colision.check(this);
        
        if(!colisionOn) {
            switch(direccion) {
                case "arriba": mundoY -= velocidad; break;
                case "abajo": mundoY += velocidad; break;
                case "izquierda": mundoX -= velocidad; break;
                case "derecha": mundoX += velocidad; break;
            }
        }
    }
    
    public void dibujo(Graphics2D g2) {
        int pantallaX = mundoX - panel.jugador.mundoX + panel.jugador.camaraX;
        int pantallaY = mundoY - panel.jugador.mundoY + panel.jugador.camaraY;

        if(mundoX + panel.originalTamañoPixel > panel.jugador.mundoX - panel.jugador.camaraX &&
           mundoX - panel.originalTamañoPixel < panel.jugador.mundoX + panel.jugador.camaraX &&
           mundoY + panel.originalTamañoPixel > panel.jugador.mundoY - panel.jugador.camaraY &&
           mundoY - panel.originalTamañoPixel < panel.jugador.mundoY + panel.jugador.camaraY) {
            
            java.awt.image.BufferedImage imagen = null;
            switch(direccion) {
                case "arriba": imagen = (numeroSprite == 1) ? arriba1 : arriba2; break;
                case "abajo": imagen = (numeroSprite == 1) ? abajo1 : abajo2; break;
                case "izquierda": imagen = (numeroSprite == 1) ? izquierda1 : izquierda2; break;
                case "derecha": imagen = (numeroSprite == 1) ? derecha1 : derecha2; break;
                default: imagen = abajo1; break; 
            }
            g2.drawImage(imagen, pantallaX, pantallaY, panel.originalTamañoPixel, panel.originalTamañoPixel, null);
        }
    }
}