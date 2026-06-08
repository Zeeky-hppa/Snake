package logica;

public class Reloj {
    private int segundosTotales;
    private boolean corriendo;

    public Reloj() {
        this.segundosTotales = 0;
        this.corriendo = false;
    }

    public void iniciarCronometro() {
        this.corriendo = true;
    }

    public void detener() {
        this.corriendo = false;
    }

    public void incrementarSegundo() {
        if (corriendo) {
            segundosTotales++;
        }
    }

    public String getTiempoFormateado() {
        int minutos = segundosTotales / 60;
        int segundos = segundosTotales % 60;
        // aparentemente esto sirve para que siempre tenga dos digitos (ej: 01:05)
        return String.format("%02d:%02d", minutos, segundos);
    }
}