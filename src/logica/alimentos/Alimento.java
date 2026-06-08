package logica.alimentos;

import logica.entidades.Entidad;
import logica.Juego;

public abstract class Alimento extends Entidad {

    protected int bloquesCrecimiento;
    protected int puntajeOtorgado;

    /**
     * Constructor base para cualquier alimento
     * @param x Posicion en el eje X
     * @param y Posicion en el eje Y
     * @param bloques Cuntos cuadraditos de cola añade al comerlo
     * @param puntaje Cuantos puntos suma a la partida
     */
    public Alimento(int x, int y, int bloques, int puntaje) {
        super(x, y);
        this.bloquesCrecimiento = bloques;
        this.puntajeOtorgado = puntaje;
    }

    public int getPuntaje() {
        return puntajeOtorgado;
    }

    public int getBloquesCrecimiento() {
        return bloquesCrecimiento;
    }

    @Override
    public boolean esConsumible() {
        return true;
    }

    /* * Como Alimento hereda de Entidad (que a su vez
     * implementa Colisionable), No hace falta que escriba el metodo
     * afectar(Serpiente s) de nuevo aca
     * Java ya sabe que las clases hijas de Alimento estan obligadas
     * a implementar ese metodo sí o sí.
     */
    @Override
    public void regenerar(Juego juego) {
        juego.generarNuevoAlimento();
    }
    @Override
    public boolean fueConsumido() {
        return true;
    }
}