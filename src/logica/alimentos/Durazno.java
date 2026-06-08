package logica.alimentos;

import logica.entidades.Serpiente;
import logica.Sprite;

import java.awt.Color;

public class Durazno extends Alimento {

    private static final int CRECIMIENTO = 5;
    private static final int PUNTAJE = 80;

    public Durazno(int x, int y) {
        super(x, y, CRECIMIENTO, PUNTAJE);
        this.spriteActual = new Sprite(new Color(255, 150, 0), "/imagenes/alimentos/durazno.png");
    }

    @Override
    public void afectar(Serpiente s) {s.visitar(this);
    }
}