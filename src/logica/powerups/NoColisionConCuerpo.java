package logica.powerups;

import logica.Sprite;
import logica.entidades.Serpiente;
import logica.estados.EstadoIntangible;
import logica.estados.EstadoNormal;

import java.awt.Color;

public class NoColisionConCuerpo extends SuperPowerUp {

    public NoColisionConCuerpo(int x, int y, int puntaje) {
        super(x, y, puntaje, 25);
        this.spriteActual = new Sprite(new Color(200, 200, 255), "/imagenes/powerups/intangibilidad.png");
    }

    @Override
    public void aplicarEfecto(Serpiente s) {
        System.out.println("Intangibilidad ACTIVADA");
        s.setPoderActivo(this, this.tiempoEfecto);
        s.setEstadoActual(new EstadoIntangible());
        s.aplicarSkinPoder("/imagenes/serpiente/cabeza_normal.png", "/imagenes/serpiente/cuerpo_fantasmal.png");
    }

    @Override
    public void revertirEfecto(Serpiente s) {
        System.out.println("Intangibilidad DESACTIVADA");
        s.setEstadoActual(new EstadoNormal());
        s.actualizarApariencia();
    }
}