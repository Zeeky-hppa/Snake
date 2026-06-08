package logica.alimentos;

import logica.entidades.Serpiente;
import logica.Sprite;

import java.awt.Color;

public class Manzana extends Alimento {

    private static final int CRECIMIENTO = 2;
    private static final int PUNTAJE = 25;

    public Manzana(int x, int y) {
        super(x, y, CRECIMIENTO, PUNTAJE);
        this.spriteActual = new Sprite(new Color(255, 0, 0), "/imagenes/alimentos/manzana.png");
    }

    @Override
    public void afectar(Serpiente s) {s.visitar(this);}
}