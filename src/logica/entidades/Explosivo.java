package logica.entidades;

import logica.Juego;
import logica.Sprite;
import vista.GestorSonido;
import java.awt.Color;

public class Explosivo extends Entidad {

    public Explosivo(int x, int y) {
        super(x, y);

        if (Juego.modoJuegoActual == 0) {
            this.spriteActual = new Sprite(new Color(30, 30, 30), "/imagenes/bomba/bomba.png");
        } else {
            this.spriteActual = new Sprite(new Color(30, 30, 30), "/imagenes/bomba/bomba.png");
        }
    }

    @Override
    public void afectar(Serpiente s) {
        if (s.getEstadoActual() != null) {
            s.getEstadoActual().reaccionarExplosivo(s);
            GestorSonido.reproducirSonidoBomba();
        }
    }
}