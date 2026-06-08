package logica.powerups;

import logica.Sprite;
import logica.entidades.Serpiente;
import logica.estados.EstadoInvencible;
import logica.estados.EstadoNormal;

import java.awt.Color;

public class EscudoAntiExplosion extends SuperPowerUp {

    public EscudoAntiExplosion(int x, int y, int puntaje) {
        super(x, y, puntaje, 25);
        this.spriteActual = new Sprite(Color.CYAN, "/imagenes/powerups/escudoantibomba.png");
    }

    @Override
    public void aplicarEfecto(Serpiente s) {
        System.out.println("Escudo AntiExplosivo ACTIVADO");
        s.setPoderActivo(this, this.tiempoEfecto);
        s.setEstadoActual(new EstadoInvencible());
        s.aplicarSkinPoder("/imagenes/serpiente/cabeza_normal.png", "/imagenes/serpiente/cuerpo_escudo.png");
    }

    @Override
    public void revertirEfecto(Serpiente s) {
        System.out.println("Escudo DESACTIVADO");
        s.setEstadoActual(new EstadoNormal());
        s.actualizarApariencia();
    }
}