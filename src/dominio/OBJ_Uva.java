package dominio;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Uva extends SuperObjeto {

    public OBJ_Uva() {
        nombre = "Uva";
        try {
            // Asegúrate de tener la imagen 'Uvas.png' en la carpeta /Objetos/
            imagen = ImageIO.read(getClass().getResourceAsStream("/Objetos/Uva.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Falta la imagen /Objetos/Uvas.png");
        }
        
        colision = true; // Para detectar cuando el jugador la toca
    }
}