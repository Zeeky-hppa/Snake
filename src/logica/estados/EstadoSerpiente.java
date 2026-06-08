package logica.estados;

import logica.powerups.PowerUp;
import logica.entidades.Serpiente;

public interface EstadoSerpiente {

    int calcularPuntos(int puntosBase);
    void reaccionarPared(Serpiente s);
    void reaccionarExplosivo(Serpiente s);
    void reaccionarPowerUp(PowerUp p, Serpiente s);
    void reaccionarCuerpo(Serpiente s);
}