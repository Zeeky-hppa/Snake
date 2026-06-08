package logica.powerups;

import logica.Sprite;
import logica.entidades.Serpiente;
import logica.estados.EstadoArcoiris;
import logica.estados.EstadoNormal;
import java.awt.Color;

public class ArcoirisSerpiente extends PowerUp {

    public ArcoirisSerpiente(int x, int y, int puntaje) {
        super(x, y, puntaje);
        this.spriteActual = new Sprite(Color.MAGENTA, "/imagenes/powerups/arcoiris.png");
    }

    @Override
    public void aplicarEfecto(Serpiente s) {
        s.setEstadoActual(new EstadoArcoiris());
        s.reducirCuerpoALaMitad();
        s.setPoderActivo(this, 25);
        s.activarEfectoArcoiris();
        System.out.println("Efecto arcoiris ACTIVADO");
    }

    @Override
    public void revertirEfecto(Serpiente s) {
        System.out.println("Efecto arcoiris DESACTIVADO");
        s.setEstadoActual(new EstadoNormal());
        s.actualizarApariencia();
    }
}