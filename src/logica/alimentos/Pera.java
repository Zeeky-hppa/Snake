package logica.alimentos;

import logica.entidades.Serpiente;
import logica.Sprite;

import java.awt.Color;

public class Pera extends Alimento {

    private static final int CRECIMIENTO = 4;
    private static final int PUNTAJE = 60;

    public Pera(int x, int y) {
        super(x, y, CRECIMIENTO, PUNTAJE);
        this.spriteActual = new Sprite(new Color(150, 255, 100), "/imagenes/alimentos/pera.png");
    }

    @Override
    public void afectar(Serpiente s) {
        s.visitar(this);
    }
}