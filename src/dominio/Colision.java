package dominio;

import presentacion.PanelDeJuego;

public class Colision {

    PanelDeJuego panel;

    public Colision(PanelDeJuego panel) {
        this.panel = panel;
    }

    // --- MÉTODO 1: Chequear paredes (Ya lo tenías) ---
    public void check(Entidad entidad) {

        int izquierda = entidad.mundoX + entidad.areaSolida.x;
        int derecha = entidad.mundoX + entidad.areaSolida.x + entidad.areaSolida.width;
        int arriba = entidad.mundoY + entidad.areaSolida.y;
        int abajo = entidad.mundoY + entidad.areaSolida.y + entidad.areaSolida.height;

        int izquierdaCol = izquierda / panel.originalTamañoPixel;
        int derechaCol = derecha / panel.originalTamañoPixel;
        int arribaFila = arriba / panel.originalTamañoPixel;
        int abajoFila = abajo / panel.originalTamañoPixel;

        int tile1, tile2;

        switch(entidad.direccion) {
        case "arriba":
            arribaFila = (arriba - entidad.velocidad) / panel.originalTamañoPixel;
            tile1 = panel.nivel.mapaNivel1[izquierdaCol][arribaFila];
            tile2 = panel.nivel.mapaNivel1[derechaCol][arribaFila];
            if (panel.nivel.mapa[tile1].colision || panel.nivel.mapa[tile2].colision) {
                entidad.colisionOn = true;
            }
            break;
        case "abajo":
            abajoFila = (abajo + entidad.velocidad) / panel.originalTamañoPixel;
            tile1 = panel.nivel.mapaNivel1[izquierdaCol][abajoFila];
            tile2 = panel.nivel.mapaNivel1[derechaCol][abajoFila];
            if (panel.nivel.mapa[tile1].colision || panel.nivel.mapa[tile2].colision) {
                entidad.colisionOn = true;
            }
            break;
        case "izquierda":
            izquierdaCol = (izquierda - entidad.velocidad) / panel.originalTamañoPixel;
            tile1 = panel.nivel.mapaNivel1[izquierdaCol][arribaFila];
            tile2 = panel.nivel.mapaNivel1[izquierdaCol][abajoFila];
            if (panel.nivel.mapa[tile1].colision || panel.nivel.mapa[tile2].colision) {
                entidad.colisionOn = true;
            }
            break;
        case "derecha":
            derechaCol = (derecha + entidad.velocidad) / panel.originalTamañoPixel;
            tile1 = panel.nivel.mapaNivel1[derechaCol][arribaFila];
            tile2 = panel.nivel.mapaNivel1[derechaCol][abajoFila];
            if (panel.nivel.mapa[tile1].colision || panel.nivel.mapa[tile2].colision) {
                entidad.colisionOn = true;
            }
            break;
        }
    }

    // --- MÉTODO 2: Chequear Frutas/Objetos (EL QUE ACABAS DE PEGAR) ---
    public int checkObjeto(Entidad entidad, boolean jugador) {
        int index = 999;
        
        for(int i = 0; i < panel.obj.length; i++) {
            if(panel.obj[i] != null) {
                // 1. Obtener posición de entidad
                entidad.areaSolida.x = entidad.mundoX + entidad.areaSolida.x;
                entidad.areaSolida.y = entidad.mundoY + entidad.areaSolida.y;
                
                // 2. Obtener posición del objeto
                panel.obj[i].areaSolida.x = panel.obj[i].mundoX + panel.obj[i].areaSolida.x;
                panel.obj[i].areaSolida.y = panel.obj[i].mundoY + panel.obj[i].areaSolida.y;
                
                switch(entidad.direccion) {
                case "arriba": entidad.areaSolida.y -= entidad.velocidad; break;
                case "abajo": entidad.areaSolida.y += entidad.velocidad; break;
                case "izquierda": entidad.areaSolida.x -= entidad.velocidad; break;
                case "derecha": entidad.areaSolida.x += entidad.velocidad; break;
                }
                
                // 3. Verificar si se tocan
                if(entidad.areaSolida.intersects(panel.obj[i].areaSolida)) {
                    if(panel.obj[i].colision) {
                        entidad.colisionOn = true;
                    }
                    if(jugador) {
                        index = i;
                    }
                }
                
                // 4. Resetear posiciones (¡Muy Importante!)
                entidad.areaSolida.x = entidad.areaSolidaX;
                entidad.areaSolida.y = entidad.areaSolidaY;
                panel.obj[i].areaSolida.x = panel.obj[i].areaSolidaX;
                panel.obj[i].areaSolida.y = panel.obj[i].areaSolidaY;
            }
        }
        return index;
    }

}
