package dominio;

import presentacion.PanelDeJuego;

public class Configuracion {
    
    PanelDeJuego panel;
    
    public Configuracion(PanelDeJuego panel) {
        this.panel = panel;
    }
    
    public void cargarNivel(int nivel) {
        
        // --- 1. LIMPIEZA TOTAL (¡Esto arregla que el juego se vuelva loco!) ---
        // Borramos frutas viejas
        for(int i = 0; i < panel.obj.length; i++) {
            panel.obj[i] = null;
        }
        // Borramos enemigos viejos
        for(int i = 0; i < panel.enemigos.length; i++) {
            panel.enemigos[i] = null;
        }

        // --- NIVEL 1 ---
        if (nivel == 1) {
            // Frutas (Bananas)
            panel.obj[0] = new OBJ_Banana();
            panel.obj[0].mundoX = 2 * panel.originalTamañoPixel;
            panel.obj[0].mundoY = 2 * panel.originalTamañoPixel; 
            
            panel.obj[1] = new OBJ_Banana();
            panel.obj[1].mundoX = 14 * panel.originalTamañoPixel;
            panel.obj[1].mundoY = 2 * panel.originalTamañoPixel; 
            
            // ENEMIGOS (2 TROLLS)
            // Troll 1
            panel.enemigos[0] = new Troll(panel);
            panel.enemigos[0].mundoX = 8 * panel.originalTamañoPixel;
            panel.enemigos[0].mundoY = 8 * panel.originalTamañoPixel;
            
            // Troll 2 (¡Nuevo!)
            panel.enemigos[1] = new Troll(panel);
            panel.enemigos[1].mundoX = 12 * panel.originalTamañoPixel;
            panel.enemigos[1].mundoY = 5 * panel.originalTamañoPixel;
        }
        // --- NIVEL 2 ---
        else if (nivel == 2) {
            // Frutas (Bananas y Uvas)
            panel.obj[0] = new OBJ_Banana();
            panel.obj[0].mundoX = 8 * panel.originalTamañoPixel;
            panel.obj[0].mundoY = 5 * panel.originalTamañoPixel;
            
            panel.obj[1] = new OBJ_Uva();
            panel.obj[1].mundoX = 2 * panel.originalTamañoPixel;
            panel.obj[1].mundoY = 8 * panel.originalTamañoPixel;
            
            panel.obj[2] = new OBJ_Uva();
            panel.obj[2].mundoX = 14 * panel.originalTamañoPixel;
            panel.obj[2].mundoY = 8 * panel.originalTamañoPixel;
            
            // Enemigos del Nivel 2
            panel.enemigos[0] = new Troll(panel); 
            panel.enemigos[0].mundoX = 5 * panel.originalTamañoPixel;
            panel.enemigos[0].mundoY = 5 * panel.originalTamañoPixel;
        }
    }
}
