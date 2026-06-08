package logica.powerups;

import logica.Sprite;
import logica.entidades.Serpiente;
import logica.estados.EstadoAtravezarPared;
import logica.estados.EstadoIntangible;
import logica.estados.EstadoNormal;

import java.awt.Color;

public class NoColisionConPared extends SuperPowerUp {

    public NoColisionConPared(int x, int y, int puntaje) {
        super(x, y, puntaje, 25);
        this.spriteActual = new Sprite(new Color(255, 215, 0), "/imagenes/powerups/invensibilidad.png");
    }

    @Override
    public void aplicarEfecto(Serpiente s) {
        System.out.println("Modo Piedra y Velocidad ACTIVADO");
        s.setPoderActivo(this, this.tiempoEfecto);
        s.setAtraviesaParedes(true);
        s.setEstadoActual(new EstadoAtravezarPared());
        s.setVelocidad(1.25);
        s.aplicarSkinPoder("/imagenes/serpiente/cabeza_normal.png", "/imagenes/serpiente/cuerpo_invencibilidad.png");
    }

    @Override
    public void revertirEfecto(Serpiente s) {
        System.out.println("Modo Piedra y Velocidad DESACTIVADO");
        s.setEstadoActual(new EstadoNormal());
        s.setAtraviesaParedes(false);
        s.setVelocidad(1);
        s.actualizarApariencia();
    }
}