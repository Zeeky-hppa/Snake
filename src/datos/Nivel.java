package datos;

import logica.Tablero;
import logica.entidades.Pared;
import logica.entidades.Explosivo;
import logica.powerups.MultiplicadorPuntos;
import logica.powerups.ArcoirisSerpiente;
import logica.powerups.EscudoAntiExplosion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Nivel {

    public static void cargarLaberinto(Tablero tablero, int nivel) {

        String rutaArchivo = "nivel" + nivel + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int fila = 0;

            while ((linea = br.readLine()) != null && fila < Tablero.ALTO) {
                for (int col = 0; col < linea.length() && col < Tablero.ANCHO; col++) {
                    char c = linea.charAt(col);

                    if (c == '#') {
                        tablero.agregarEntidad(new Pared(col, fila));
                    } else if (c == 'E') {
                        tablero.agregarEntidad(new Explosivo(col, fila));
                    } else if (c == 'M') {
                        tablero.agregarEntidad(new MultiplicadorPuntos(col, fila, 50));
                    } else if (c == 'A') {
                        tablero.agregarEntidad(new ArcoirisSerpiente(col, fila, 60));
                    } else if (c == 'S') {
                        tablero.agregarEntidad(new EscudoAntiExplosion(col, fila, 80));
                    }
                }
                fila++;
            }
            System.out.println("Laberinto cargado (Nivel " + nivel + ")");

        } catch (IOException e) {
            System.out.println("No se encontro " + rutaArchivo + ". Jugando sin ovstaculos.");
        }
    }
}