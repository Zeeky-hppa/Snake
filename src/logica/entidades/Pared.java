package logica.entidades;

import logica.Juego;
import logica.Sprite;
import java.awt.Color;

public class Pared extends Entidad {

    public Pared(int x, int y) {
        super(x, y);

        if (Juego.modoJuegoActual == 0) {
            this.spriteActual = new Sprite(Color.DARK_GRAY); // Modo Serpiente
        } else {
            this.spriteActual = new Sprite(new Color(40, 80, 100)); // Modo Leviata
        }
    }

    @Override
    public void afectar(Serpiente s) {
        if (s.getEstadoActual() != null) {
            s.getEstadoActual().reaccionarPared(s);
        }
    }
}