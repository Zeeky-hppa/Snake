package logica.entidades;

import logica.Juego;
import logica.Sprite;

public abstract class Entidad implements Colisionable {

    protected int x;
    protected int y;
    protected Sprite spriteActual;

    public Entidad(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSpriteActual(Sprite nuevoSprite) {
        this.spriteActual = nuevoSprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean esCuerpo() {
        return false;
    }

    public Sprite getSprite() {
        return spriteActual;
    }

    public boolean esConsumible() {
        return false;
    }

    public void regenerar(Juego juego) {
    }

    public boolean requiereReubicacion() {
        return false;
    }

    public boolean fueConsumido() {
        return false;
    }

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
