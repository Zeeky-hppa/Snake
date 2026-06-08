package datos;

public class Ranking {

    private static Ranking instancia;

    public int[][][] puntosTop = new int[2][5][5];
    public String[][][] nombresTop = new String[2][5][5];
    public String[][][] tiemposTop = new String[2][5][5];

    private Ranking() {
        for (int m = 0; m < 2; m++) {
            for (int n = 0; n < 5; n++) {
                for (int p = 0; p < 5; p++) {
                    nombresTop[m][n][p] = "---";
                    tiemposTop[m][n][p] = "00:00";
                }
            }
        }
    }

    public static Ranking getInstance() {
        if (instancia == null) {
            instancia = new Ranking();
        }
        return instancia;
    }

    public boolean esTop5(int modo, int nivel, int puntos) {
        int indiceNivel = nivel - 1;
        for (int i = 0; i < 5; i++) {
            if (puntos > puntosTop[modo][indiceNivel][i]) {
                return true;
            }
        }
        return false;
    }

    public void agregarAlTop5(int modo, int nivel, String nombre, int puntos, String tiempo) {
        int indiceNivel = nivel - 1;

        for (int i = 0; i < 5; i++) {
            if (puntos > puntosTop[modo][indiceNivel][i]) {
                for (int j = 4; j > i; j--) {
                    puntosTop[modo][indiceNivel][j] = puntosTop[modo][indiceNivel][j - 1];
                    nombresTop[modo][indiceNivel][j] = nombresTop[modo][indiceNivel][j - 1];
                    tiemposTop[modo][indiceNivel][j] = tiemposTop[modo][indiceNivel][j - 1];
                }

                puntosTop[modo][indiceNivel][i] = puntos;
                nombresTop[modo][indiceNivel][i] = nombre;
                tiemposTop[modo][indiceNivel][i] = tiempo;
                break;
            }
        }
        GestorConfiguracion.guardar();
    }

    public String obtenerTextoRanking(int modo) {
        StringBuilder sb = new StringBuilder();
        String modoStr = (modo == 0) ? "SERPIENTE" : "LEVIATAN";
        sb.append("RECORDS: MODO ").append(modoStr).append("\n\n");
        sb.append(String.format("%-10s %-5s %-20s %-10s %-15s\n", "Nivel", "TOP", "Nombre", "Puntos", "Tiempo"));
        sb.append("-----------------------------------------------------------------\n");

        for (int n = 0; n < 5; n++) {
            for (int p = 0; p < 5; p++) {
                String nivelStr = (p == 0) ? "Nivel " + (n + 1) + ":" : "";
                String topStr = (p + 1) + ".";
                String nombre = nombresTop[modo][n][p];
                int puntos = puntosTop[modo][n][p];
                String tiempo = tiemposTop[modo][n][p];
                sb.append(String.format("%-10s %-5s %-20s %-10d %-15s\n", nivelStr, topStr, nombre, puntos, tiempo));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}