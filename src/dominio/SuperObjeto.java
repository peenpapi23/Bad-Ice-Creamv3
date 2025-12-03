package dominio;

import java.awt.Graphics2D;
import java.awt.Rectangle; // ¡Importante importar esto!
import java.awt.image.BufferedImage;
import presentacion.PanelDeJuego;

public class SuperObjeto {
    
    public BufferedImage imagen;
    public String nombre;
    public boolean colision = false;
    public int mundoX, mundoY;
    
    // --- ESTAS SON LAS VARIABLES QUE TE FALTAN ---
    // Creamos un rectángulo de 48x48 (tamaño del tile) por defecto
    public Rectangle areaSolida = new Rectangle(0, 0, 48, 48); 
    public int areaSolidaX = 0;
    public int areaSolidaY = 0;
    // --------------------------------------------

    public void dibujo(Graphics2D graficos2D, PanelDeJuego panel) {
        int pantallaX = mundoX - panel.jugador.mundoX + panel.jugador.camaraX;
        int pantallaY = mundoY - panel.jugador.mundoY + panel.jugador.camaraY;
        
        if (imagen != null) {
            graficos2D.drawImage(imagen, pantallaX, pantallaY, panel.originalTamañoPixel, panel.originalTamañoPixel, null);
        }
    }
}
