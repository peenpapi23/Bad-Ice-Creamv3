package dominio;

import java.util.Random;
import javax.imageio.ImageIO;
import presentacion.PanelDeJuego;

public class Troll extends Enemigo {

    public Troll(PanelDeJuego panel) {
        super(panel, "troll"); // Llamamos al constructor padre
        
        this.velocidad = 2; // Velocidad del Troll
        this.direccion = "abajo"; // Dirección inicial
        
        // Cargamos TUS imágenes específicas
        cargarImagenesTroll();
    }

    public void cargarImagenesTroll() {
        try {
            // Nota: Aquí usamos los nombres que tú me dijiste (aba1, arr1, etc.)
            
            arriba1 = ImageIO.read(getClass().getResourceAsStream("/troll/arr1.png"));
            arriba2 = ImageIO.read(getClass().getResourceAsStream("/troll/arr2.png"));
            abajo1 = ImageIO.read(getClass().getResourceAsStream("/troll/aba1.png"));
            abajo2 = ImageIO.read(getClass().getResourceAsStream("/troll/aba2.png"));
            izquierda1 = ImageIO.read(getClass().getResourceAsStream("/troll/izq1.png"));
            izquierda2 = ImageIO.read(getClass().getResourceAsStream("/troll/izq2.png"));
            derecha1 = ImageIO.read(getClass().getResourceAsStream( "/troll/der1.png"));
            derecha2 = ImageIO.read(getClass().getResourceAsStream("/troll/der2.png"));
            
        } catch (Exception e) {
            System.out.println("❌ Error cargando imágenes del Troll. Verifica la carpeta /enemigos/troll/");
            e.printStackTrace();
        }
    }

    @Override
    public void setAccion() {
        // --- INTELIGENCIA ARTIFICIAL DEL TROLL ---
        // Regla: Moverse recto siempre. Solo cambiar si choca.
        
        if (colisionOn) {
            // Si en el frame anterior chocó, elegimos nueva dirección al azar
            cambiarDireccion();
            
            // Importante: Reiniciamos la variable para que intente moverse de nuevo
            colisionOn = false; 
        }
        
        // Si no ha chocado, NO hacemos nada. Mantiene su 'direccion' actual 
        // y sigue caminando recto infinitamente.
    }
    
    private void cambiarDireccion() {
        Random random = new Random();
        int i = random.nextInt(4); // Genera 0, 1, 2 o 3
        
        switch(i) {
            case 0: direccion = "arriba"; break;
            case 1: direccion = "abajo"; break;
            case 2: direccion = "izquierda"; break;
            case 3: direccion = "derecha"; break;
        }
    }
}