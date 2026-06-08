package logica.estados;

import logica.powerups.PowerUp;
import logica.Tablero;
import logica.entidades.Cabeza;
import logica.entidades.Serpiente;

public class EstadoIntangible implements EstadoSerpiente {

    @Override
    public void reaccionarPared(Serpiente s) {s.matar();}

    @Override
    public void reaccionarExplosivo(Serpiente s) {
        s.matar();
    }

    @Override
    public void reaccionarCuerpo(Serpiente s) {
        System.out.println("Atravesaste tu cola porque sos Intangible");
    }

    public int calcularPuntos(int puntosBase) {return puntosBase;}

    @Override
    public void reaccionarPowerUp(PowerUp p, Serpiente s) {
        // Esta vacio aproposito
        // Como ya tenemos un efecto activo, ignoramos este
    }
}