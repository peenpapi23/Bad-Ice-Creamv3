package dominio;

import presentacion.PanelDeJuego;

public class Configuracion {
	PanelDeJuego panel;
	public Configuracion (PanelDeJuego panel) {
		this.panel = panel;
	}
	
	// Cambiamos el nombre a 'cargarNivel' y pedimos el número de nivel
    public void cargarNivel(int nivel) {
        
        // Primero borramos los objetos viejos para que no se mezclen
        for(int i = 0; i < panel.obj.length; i++) {
            panel.obj[i] = null;
        }

        if (nivel == 1) {
            // --- NIVEL 1: SOLO BANANAS ---
            panel.obj[0] = new OBJ_Banana();
            panel.obj[0].mundoX = 2 * panel.originalTamañoPixel;
            panel.obj[0].mundoY = 2 * panel.originalTamañoPixel; 
            
            panel.obj[1] = new OBJ_Banana();
            panel.obj[1].mundoX = 14 * panel.originalTamañoPixel;
            panel.obj[1].mundoY = 2 * panel.originalTamañoPixel; 
            // ... puedes agregar más bananas aquí ...
        }
        else if (nivel == 2) {
            // --- NIVEL 2: BANANAS Y UVAS ---
            
            // Una Banana en el centro
            panel.obj[0] = new OBJ_Banana();
            panel.obj[0].mundoX = 8 * panel.originalTamañoPixel;
            panel.obj[0].mundoY = 5 * panel.originalTamañoPixel;
            
            // Uvas en las esquinas
            panel.obj[1] = new OBJ_Uva();
            panel.obj[1].mundoX = 2 * panel.originalTamañoPixel;
            panel.obj[1].mundoY = 8 * panel.originalTamañoPixel;
            
            panel.obj[2] = new OBJ_Uva();
            panel.obj[2].mundoX = 14 * panel.originalTamañoPixel;
            panel.obj[2].mundoY = 8 * panel.originalTamañoPixel;
            
         // ... dentro del if (nivel == 2) ...

         // Recuerda importar dominio.Troll; arriba

         // Ejemplo: Poner un Troll en la posición (5, 5)
         panel.enemigos[0] = new Troll(panel); 
         panel.enemigos[0].mundoX = 5 * panel.originalTamañoPixel;
         panel.enemigos[0].mundoY = 5 * panel.originalTamañoPixel;
        }
    }
}
