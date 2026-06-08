package logica.estados;

import logica.Tablero;
import logica.entidades.Cabeza;
import logica.powerups.PowerUp;
import logica.entidades.Serpiente;

public class EstadoAtravezarPared implements EstadoSerpiente {

    @Override
    public void reaccionarPared(Serpiente s) {
        System.out.println("Atraveza las paredes y destruilas");
        // para teletransportar con las paredes externas
        if (s.puedeAtravesarParedes()) {
            Cabeza cab = s.getCabeza();
            if (cab.getX() < 0) cab.setPosicion(Tablero.ANCHO - 1, cab.getY());
            else if (cab.getX() >= Tablero.ANCHO) cab.setPosicion(0, cab.getY());
            else if (cab.getY() < 0) cab.setPosicion(cab.getX(), Tablero.ALTO - 1);
            else if (cab.getY() >= Tablero.ALTO) cab.setPosicion(cab.getX(), 0);
        } else {
            s.matar();

        }
    }

    @Override
    public void reaccionarExplosivo(Serpiente s) {s.matar();
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