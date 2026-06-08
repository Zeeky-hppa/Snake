package logica.powerups;

import logica.Juego;
import logica.entidades.Entidad;
import logica.entidades.Serpiente;

public abstract class PowerUp extends Entidad {

    protected static final int CRECIMIENTO_FIJO = 3;
    protected int puntaje;
    protected boolean fueConsumido = false;

    public PowerUp(int x, int y, int puntaje) {
        super(x, y);
        this.puntaje = puntaje;
    }

    @Override
    public boolean esConsumible() {
        return fueConsumido;
    }

    public void setFueConsumido(boolean consumido) {
        this.fueConsumido = consumido;
    }

    @Override
    public boolean fueConsumido() {
        return this.fueConsumido;
    }

    public int getCrecimiento() {
        return CRECIMIENTO_FIJO;
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public void afectar(Serpiente s) {
        if (s.getEstadoActual() != null) {
            s.getEstadoActual().reaccionarPowerUp(this, s);
        }
    }

    @Override
    public void regenerar(Juego juego) {
        juego.generarNuevoPowerUp();
    }
    public void revertirEfecto(Serpiente s) {
        // No hace nada por defecto
    }

    public abstract void aplicarEfecto(Serpiente s);
}