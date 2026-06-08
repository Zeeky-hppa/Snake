package vista;

import javax.swing.*;
import java.awt.*;
import logica.Tablero;
import logica.entidades.Entidad;
import logica.Juego;

public class PanelJuego extends JPanel {

    private Tablero tablero;
    private final int TAMANO_CELDA = 25;

    public PanelJuego() {
        setPreferredSize(new Dimension(500, 500));
        actualizarFondo();
    }

    public void actualizarFondo() {
        if (Juego.modoJuegoActual == 0) {
            setBackground(new Color(92, 64, 40));
        } else {
            setBackground(new Color(15, 60, 110));
        }
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tablero == null) return;

        g.setColor(Juego.modoJuegoActual == 0 ? new Color(120, 80, 50) : new Color(30, 90, 160));
        for (int i = 0; i < 500; i += TAMANO_CELDA) {
            g.drawLine(i, 0, i, 500);
            g.drawLine(0, i, 500, i);
        }

        //con esto dibujo las entidades del tablero
        for (int fila = 0; fila < Tablero.ALTO; fila++) {
            for (int col = 0; col < Tablero.ANCHO; col++) {
                Entidad e = tablero.getEntidadEn(col, fila);
                if (e != null) {
                    if (e.getSprite() != null) {
                        e.getSprite().dibujar(g, col * TAMANO_CELDA, fila * TAMANO_CELDA, TAMANO_CELDA);
                    }
                }
            }
        }
    }
}