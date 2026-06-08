package logica.entidades;
import logica.Juego;
import logica.Sprite;

import java.awt.Color;

public class Cola extends Entidad {

    public Cola(int x, int y) {
        super(x, y);

        if (Juego.modoJuegoActual == 0) {
            this.spriteActual = new Sprite(new Color(50, 255, 50), "/imagenes/serpiente/cuerpo_normal.png");
        } else {
            this.spriteActual = new Sprite(new Color(255, 60, 60), "/imagenes/leviatan/colaleviatan.png");
        }
    }

    public void setSpriteActual(Sprite nuevoSprite) {
        this.spriteActual = nuevoSprite;
    }

    public boolean esCuerpo() {
        return true;
    }

    public void afectar(Serpiente s) {
        System.out.println("Colision detectada, la cabeza con la cola.");

        if (s.getEstadoActual() != null) {
            s.getEstadoActual().reaccionarCuerpo(s);
        } else {
            System.out.println("Error: La serpiente no tiene un estado asignado.");
        }
    }
}