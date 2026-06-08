package logica;

import logica.entidades.Entidad;

public class Grilla {

    // Usamos Entidad[][] para que pueda guardar paredes, frutas o partes de la serpiente.
    private Entidad[][] matriz;
    private int cantFilas;
    private int cantColumnas;

    public Grilla(int filas, int columnas) {
        this.cantFilas = filas;
        this.cantColumnas = columnas;
        this.matriz = new Entidad[filas][columnas];
    }

    public boolean esPosicionValida(int f, int c) {
        return f >= 0 && f < cantFilas && c >= 0 && c < cantColumnas;
    }

    public Entidad getEntidad(int f, int c) {
        if (esPosicionValida(f, c)) {
            return matriz[f][c];
        }
        return null;
    }

    public void colocarEntidad(Entidad e, int f, int c) {
        if (esPosicionValida(f, c)) {
            matriz[f][c] = e;
            // actualizo tambien las cordenadas de la entidad
            if (e != null) {
                e.setPosicion(c, f);
            }
        }
    }

    public void vaciarCasillero(int f, int c) {
        if (esPosicionValida(f, c)) {
            matriz[f][c] = null;
        }
    }

    // por si alguna otra clase necesita saber el tamaño exacto del mapa
    public int getCantFilas() {
        return cantFilas;
    }

    public int getCantColumnas() {
        return cantColumnas;
    }
}