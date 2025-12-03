package dominio;

import presentacion.PanelDeJuego;

public class Colision {

    PanelDeJuego panel;

    public Colision(PanelDeJuego panel) {
        this.panel = panel;
    }

    public void check(Entidad entidad) {

        // Hitbox del jugador dentro del mundo
        int izquierda = entidad.mundoX + entidad.areaSolida.x;
        int derecha = entidad.mundoX + entidad.areaSolida.x + entidad.areaSolida.width;
        int arriba = entidad.mundoY + entidad.areaSolida.y;
        int abajo = entidad.mundoY + entidad.areaSolida.y + entidad.areaSolida.height;

        // Convertir a coordenadas de tiles
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
}
