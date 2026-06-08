package logica;

import logica.entidades.Cabeza;
import logica.entidades.Cola;
import logica.entidades.Entidad;
import logica.entidades.Serpiente;
import java.util.Random;

public class Tablero {

    public static final int ANCHO = 20;
    public static final int ALTO = 20;

    private Grilla grilla;
    private Serpiente serpiente;
    private Random random;

    public Tablero() {
        this.grilla = new Grilla(ALTO, ANCHO);
        this.random = new Random();

        spawnearSerpienteAleatoria();
        actualizarPosicionesEnGrilla();
    }

    private void spawnearSerpienteAleatoria() {
        int xInicial = random.nextInt(14) + 3;
        int yInicial = random.nextInt(14) + 3;

        this.serpiente = new Serpiente(xInicial, yInicial);

        int dir = random.nextInt(4);
        switch (dir) {
            case 0: serpiente.cambiarDireccion("ARRIBA"); break;
            case 1: serpiente.cambiarDireccion("ABAJO"); break;
            case 2: serpiente.cambiarDireccion("IZQUIERDA"); break;
            case 3: serpiente.cambiarDireccion("DERECHA"); break;
        }
    }

    public void agregarEntidad(Entidad e) {
        if (e != null) {
            grilla.colocarEntidad(e, e.getY(), e.getX());
        }
    }

    public void actualizarPosicionesEnGrilla() {
        limpiarSerpienteDeGrilla();
        Cabeza cab = serpiente.getCabeza();
        grilla.colocarEntidad(cab, cab.getY(), cab.getX());

        for (Cola c : serpiente.getCuerpo()) {
            grilla.colocarEntidad(c, c.getY(), c.getX());
        }
    }

    private void limpiarSerpienteDeGrilla() {
        for (int i = 0; i < ALTO; i++) {
            for (int j = 0; j < ANCHO; j++) {
                Entidad e = grilla.getEntidad(i, j);
                if (e != null && (e == serpiente.getCabeza() || e.esCuerpo())) {
                    grilla.vaciarCasillero(i, j);
                }
            }
        }
    }

    public Entidad getEntidadEn(int x, int y) {
        return grilla.getEntidad(y, x);
    }

    public void vaciarTablero() {
        this.grilla = new Grilla(ALTO, ANCHO);
    }

    public Grilla getGrilla() {
        return grilla;
    }

    public Serpiente getSerpiente() {
        return serpiente;
    }
}