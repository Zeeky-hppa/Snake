package logica;

public interface ObservadorJuego {

    /**
     * Metodo que se llamara automaticamente cuando halla un cambio en el estado del juego
     * (por ejemplo, cuando la serpiente come una manzana y suma puntos, o cuando pasa un segundo).
     * * @param puntos      El puntaje actual de la partida.
     * @param tiempoTexto El tiempo formateado
     */
    void actualizar(int puntos, String tiempoTexto);

}