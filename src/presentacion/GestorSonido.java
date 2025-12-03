package presentacion;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GestorSonido {
    Clip clip;
    URL[] rutasSonido = new URL[5]; 

    public GestorSonido() {
        // ID 0: Música del Menú
        rutasSonido[0] = getClass().getResource("/sonido/menu.wav");
        // ID 1: Música del Juego
        rutasSonido[1] = getClass().getResource("/sonido/juego.wav");
    }

    public void setArchivo(int i) {
        try {
            if (rutasSonido[i] == null) return;
            AudioInputStream ais = AudioSystem.getAudioInputStream(rutasSonido[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("Error cargando sonido ID: " + i);
        }
    }

    public void play() {
        if (clip != null) clip.start();
    }

    public void loop() {
        if (clip != null) clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}