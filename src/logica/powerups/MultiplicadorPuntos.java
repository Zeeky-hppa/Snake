package logica.powerups;

import logica.Sprite;
import logica.entidades.Serpiente;
import logica.estados.EstadoMultiplicador;
import logica.estados.EstadoNormal;
import java.awt.Color;

public class MultiplicadorPuntos extends PowerUp {

    public MultiplicadorPuntos(int x, int y, int puntaje) {
        super(x, y, puntaje);
        this.spriteActual = new Sprite(new Color(255, 128, 0), "/imagenes/powerups/duplicadorpuntos.png");
    }

    @Override
    public void aplicarEfecto(Serpiente s) {
        System.out.println("Multiplicador de puntos ACTIVADO");
        s.setEstadoActual(new EstadoMultiplicador());
        s.setPoderActivo(this, 25);
        s.aplicarSkinPoder("/imagenes/serpiente/cabeza_normal.png", "/imagenes/serpiente/cuerpo_multiplicador.png");
    }

    @Override
    public void revertirEfecto(Serpiente s) {
        System.out.println("Multiplicador de puntos DESACTIVADO");
        s.setEstadoActual(new EstadoNormal());
        s.actualizarApariencia();
    }
}