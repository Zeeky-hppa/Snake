package datos;

import logica.Juego;
import java.io.*;

public class GestorConfiguracion {

    public static void guardar() {
        try (PrintWriter out = new PrintWriter(new FileWriter("progreso.txt"))) {
            out.println(Juego.nivelMaximoDesbloqueado);
            out.println(Juego.modoJuegoActual);

            for (int m = 0; m < 2; m++) {
                for (int n = 0; n < 5; n++) {
                    for (int p = 0; p < 5; p++) {
                        out.println(Ranking.getInstance().puntosTop[m][n][p]);
                        out.println(Ranking.getInstance().nombresTop[m][n][p]);
                        out.println(Ranking.getInstance().tiemposTop[m][n][p]);
                    }
                }
            }

            System.out.println("Progreso guardado via GestorConfiguracion.");
        } catch (IOException e) {
            System.out.println("Error al intentar guardar: " + e.getMessage());
        }
    }

    public static void cargar() {
        try (BufferedReader br = new BufferedReader(new FileReader("progreso.txt"))) {
            Juego.nivelMaximoDesbloqueado = Integer.parseInt(br.readLine());
            Juego.modoJuegoActual = Integer.parseInt(br.readLine());

            for (int m = 0; m < 2; m++) {
                for (int n = 0; n < 5; n++) {
                    for (int p = 0; p < 5; p++) {
                        String puntosStr = br.readLine();
                        String nombreStr = br.readLine();
                        String tiempoStr = br.readLine();

                        if (puntosStr == null || nombreStr == null || tiempoStr == null || nombreStr.equals("null")) {
                            throw new Exception();
                        }

                        Ranking.getInstance().puntosTop[m][n][p] = Integer.parseInt(puntosStr);
                        Ranking.getInstance().nombresTop[m][n][p] = nombreStr;
                        Ranking.getInstance().tiemposTop[m][n][p] = tiempoStr;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("No hay progreso previo. Se usaran valores por defecto.");

            for (int m = 0; m < 2; m++) {
                for (int n = 0; n < 5; n++) {
                    for (int p = 0; p < 5; p++) {
                        Ranking.getInstance().puntosTop[m][n][p] = 0;
                        Ranking.getInstance().nombresTop[m][n][p] = "---";
                        Ranking.getInstance().tiemposTop[m][n][p] = "00:00";
                    }
                }
            }
        }
    }
}