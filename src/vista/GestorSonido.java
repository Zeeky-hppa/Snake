package vista;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.Random;

public class GestorSonido {

    private static Clip clipFondo;
    private static Random random = new Random();

    //aca solo poner las canciones que quiero que se reproduzcan NO SONIDOS ESPECIFICOS
    private static String[] playlist = {
            "/sonidos/cancion1.wav",
            "/sonidos/cancion2.wav",
            "/sonidos/cancion3.wav",
            "/sonidos/cancion4.wav",
            "/sonidos/cancion5.wav",
            "/sonidos/cancion6.wav",
            "/sonidos/cancion7.wav",
            "/sonidos/cancion8.wav"
    };

    public static void reproducirMusicaAleatoria() {
        detenerMusica();

        String cancionElegida = playlist[random.nextInt(playlist.length)];

        try {
            URL url = GestorSonido.class.getResource(cancionElegida);
            if (url != null) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                clipFondo = AudioSystem.getClip();
                clipFondo.open(audioIn);
                clipFondo.loop(Clip.LOOP_CONTINUOUSLY);
                clipFondo.start();
            } else {
                System.out.println("No se encontro la cancion " + cancionElegida);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void detenerMusica() {
        if (clipFondo != null && clipFondo.isRunning()) {
            clipFondo.stop();
            clipFondo.close();
        }
    }
    //sonido bombini
    public static void reproducirSonidoBomba() {
        reproducirEfecto("/sonidos/bomba.wav");
    }


    public static void reproducirEfecto(String rutaEfecto) {
        try {
            URL url = GestorSonido.class.getResource(rutaEfecto);
            if (url != null) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip clipEfecto = AudioSystem.getClip();
                clipEfecto.open(audioIn);
                clipEfecto.start();

                clipEfecto.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clipEfecto.close();
                    }
                });
            } else {
                System.out.println("SFX NO ENCONTRADO: " + rutaEfecto);
            }
        } catch (Exception e) {
            System.out.println("Error SFX: " + e.getMessage());
        }
    }
}