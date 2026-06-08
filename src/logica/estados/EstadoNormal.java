package logica.estados;

import logica.powerups.PowerUp;
import logica.entidades.Serpiente;

public class EstadoNormal implements EstadoSerpiente {

    @Override
    public void reaccionarPared(Serpiente s) {
        s.matar();
    }

    @Override
    public void reaccionarExplosivo(Serpiente s) {
        s.matar();
    }

    @Override
    public void reaccionarCuerpo(Serpiente s) {s.matar();}

    public int calcularPuntos(int puntosBase) {return puntosBase;}

    @Override
    public void reaccionarPowerUp(PowerUp p, Serpiente s) {
        p.aplicarEfecto(s);
        s.crecer(p.getCrecimiento());
        s.sumarPuntos(p.getPuntaje());
        p.setFueConsumido(true); // Le avisamos al juego que lo consumimos para que lo borre
    }
}