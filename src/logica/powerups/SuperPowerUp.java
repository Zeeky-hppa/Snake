package logica.powerups;

import logica.entidades.Serpiente;

public abstract class SuperPowerUp extends PowerUp {

    // los ticks dicen cuantos segs dura el efecto
    protected int tiempoEfecto;

    /**
     * Constructor base para cualquier poder de estado temporal.
     * @param x Posicion en el eje X.
     * @param y Posicion en el eje Y.
     * @param puntaje Puntos que otorga
     * @param tiempoEfecto Duracion del poder temporal
     */
    public SuperPowerUp(int x, int y, int puntaje, int tiempoEfecto) {
        super(x, y, puntaje);
        this.tiempoEfecto = tiempoEfecto;
    }

    public int getTiempoEfecto() {
        return tiempoEfecto;
    }

    /**
     * Define como devolver a la serpiente a la normalidad (ej. sacarle la
     * invencibilidad y devolverla al EstadoNormal) una vez que el tiempo
     * del SuperPowerUp se termina
     * @param s La instancia de la Serpiente.
     */
    public abstract void revertirEfecto(Serpiente s);
}