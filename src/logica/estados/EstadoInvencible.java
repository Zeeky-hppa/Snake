package logica.estados;

import logica.powerups.PowerUp;
import logica.entidades.Serpiente;

public class EstadoInvencible implements EstadoSerpiente {

    @Override
    public void reaccionarPared(Serpiente s) {
        s.matar();
    }

    @Override
    public void reaccionarExplosivo(Serpiente s) {
        System.out.println("Bomba bloqueada por el Escudo");
    }

    @Override
    public void reaccionarCuerpo(Serpiente s) {s.matar();}

    public int calcularPuntos(int puntosBase) {return puntosBase;}

    @Override
    public void reaccionarPowerUp(PowerUp p, Serpiente s) {
        // Esta vacio aproposito
        // Como ya tenemos un efecto activo, ignoramos este
    }
}