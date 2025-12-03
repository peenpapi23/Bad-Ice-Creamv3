package presentacion;

import javax.swing.JFrame;

public class Pantalla {

    static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("BadIceCream");
        
        // 1. Creamos el menú
        // Fíjate que ya no pasamos 'panelMenu' dentro del paréntesis,
        // simplemente llamamos a cambiarAlJuegoReal() sin argumentos.
        PanelMenu panelMenu = new PanelMenu(new PanelMenu.ListenerInicioJuego() {
            @Override
            public void iniciarJuego() {
                cambiarAlJuegoReal();
            }
        });
        
        window.add(panelMenu);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // --- AQUÍ ESTÁ EL CAMBIO ---
    // Ya no recibe parámetros. Simplemente limpia la ventana.
    public static void cambiarAlJuegoReal() {
        // 1. Borramos TODO lo que haya en la ventana (en este caso, el menú)
        window.getContentPane().removeAll();
        
        // 2. Cargamos la lógica de TU AMIGO
        PanelDeJuego panelDeJuego = new PanelDeJuego();
        window.add(panelDeJuego);
        
        // 3. Refrescamos y damos foco
        window.revalidate();
        window.repaint();
        
        // Importante: Volvemos a hacer pack() por si el juego tiene distinto tamaño que el menú
        window.pack(); 
        window.setLocationRelativeTo(null);
        
        panelDeJuego.requestFocusInWindow(); // Vital para el teclado
        
        // 4. Iniciamos el juego de él
        panelDeJuego.setObjetos();
        panelDeJuego.iniciarHilos();
    }
}