package logica.entidades;
import logica.Juego;
import logica.Sprite;
import java.awt.Color;

public class Cabeza extends Entidad {

    public Cabeza(int x, int y) {
        super(x, y);

        if (Juego.modoJuegoActual == 0) {
            this.spriteActual = new Sprite(new Color(14, 123, 14), "/imagenes/serpiente/cabeza_normal.png");
        } else {
            this.spriteActual = new Sprite(new Color(180, 0, 0), "/imagenes/leviatan/cabezaleviatan.png");
        }
    }

    @Override
    public void afectar(Serpiente s) {
    }
}