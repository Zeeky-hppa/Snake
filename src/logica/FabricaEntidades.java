package logica;

import logica.alimentos.*;
import logica.powerups.*;
import java.util.Random;

public class FabricaEntidades {

    private static Random random = new Random();

    // fabrica para los alimentos
    public static Alimento crearAlimentoAleatorio(int x, int y) {
        int tipoFruta = random.nextInt(5);
        switch (tipoFruta) {
            case 0: return new Manzana(x, y);
            case 1: return new Banana(x, y);
            case 2: return new Pera(x, y);
            case 3: return new Durazno(x, y);
            case 4: return new Sandia(x, y);
            default: return new Manzana(x, y);
        }
    }

    // fabrica para powerups
    public static PowerUp crearPowerUpAleatorio(int x, int y) {
        int tipoPoder = random.nextInt(5);
        switch (tipoPoder) {
            case 0: return new MultiplicadorPuntos(x, y, 50);
            case 1: return new ArcoirisSerpiente(x, y, 60);
            case 2: return new EscudoAntiExplosion(x, y, 80);
            case 3: return new NoColisionConCuerpo(x, y, 100);
            case 4: return new NoColisionConPared(x, y, 90);
            default: return new MultiplicadorPuntos(x, y, 50);
        }
    }
}