package logica.alimentos;

import logica.entidades.Serpiente;
import logica.Sprite;

import java.awt.Color;

public class Sandia extends Alimento {

    private static final int CRECIMIENTO = 6;
    private static final int PUNTAJE = 100;

    public Sandia(int x, int y) {
        super(x, y, CRECIMIENTO, PUNTAJE);
        this.spriteActual = new Sprite(new Color(255, 50, 50), "/imagenes/alimentos/sandia.png");
    }

    @Override
    public void afectar(Serpiente s) {
        s.visitar(this);
    }
}