package logica.alimentos;

import logica.entidades.Serpiente;
import logica.Sprite;

import java.awt.Color;

public class Banana extends Alimento {

    private static final int CRECIMIENTO = 3;
    private static final int PUNTAJE = 40;

    public Banana(int x, int y) {
        super(x, y, CRECIMIENTO, PUNTAJE);
        this.spriteActual = new Sprite(new Color(255, 255, 0), "/imagenes/alimentos/banana.png");
    }

    @Override
    public void afectar(Serpiente s) {
        s.visitar(this);
    }
}

