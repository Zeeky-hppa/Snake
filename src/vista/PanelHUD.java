package vista;

import javax.swing.*;
import java.awt.*;
import logica.ObservadorJuego; // Importamos la interfaz que creamos antes

public class PanelHUD extends JPanel implements ObservadorJuego {

    private JLabel lblPuntos;
    private JLabel lblTiempo;

    public PanelHUD() {
        setBackground(new Color(40, 40, 40));
        setLayout(new GridLayout(1, 2));
        setPreferredSize(new Dimension(500, 40));

        lblPuntos = new JLabel("Puntos: 0", SwingConstants.CENTER);
        lblPuntos.setForeground(Color.WHITE);
        lblPuntos.setFont(new Font("Arial", Font.BOLD, 16));

        lblTiempo = new JLabel("Tiempo: 00:00", SwingConstants.CENTER);
        lblTiempo.setForeground(Color.WHITE);
        lblTiempo.setFont(new Font("Arial", Font.BOLD, 16));

        add(lblPuntos);
        add(lblTiempo);
    }

    @Override
    public void actualizar(int puntos, String tiempoTexto) {
        lblPuntos.setText("Puntos: " + puntos);
        lblTiempo.setText("Tiempo: " + tiempoTexto);
    }
}