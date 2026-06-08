package logica.estados;

import logica.powerups.PowerUp;
import logica.entidades.Serpiente;

public class EstadoMultiplicador implements EstadoSerpiente {

    @Override
    public void reaccionarPared(Serpiente s) {
        s.matar();
    }

    @Override
    public void reaccionarExplosivo(Serpiente s) {
        s.matar();
    }

    @Override
    public void reaccionarCuerpo(Serpiente s) {
        s.matar();
    }

    @Override
    public int calcularPuntos(int puntosBase) {return puntosBase * 2;}

    @Override
    public void reaccionarPowerUp(PowerUp p, Serpiente s) {
        System.out.println("Efecto bloqueado: Ya tenés el multiplicador activo.");
    }
}