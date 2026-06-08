package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

    private PanelJuego panelJuego;
    private PanelHUD panelHUD;

    public GUI() {
        panelJuego = new PanelJuego();
        panelHUD = new PanelHUD();
    }

    public void inicializarVentana() {
        setTitle("Snake Game - Jugando");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // HUD arriba (NORTH) y el Juego en el centro (CENTER)
        add(panelHUD, BorderLayout.NORTH);
        add(panelJuego, BorderLayout.CENTER);

        pack(); // aparentemente ajusta automaticamente el tamaño de la ventana al de los paneles
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                System.out.println("Tecla presionada: " + KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        // Esto hace que la ventana capture las teclas sin tener que hacer clic
        setFocusable(true);
        requestFocusInWindow();
    }

    public void actualizarPantalla() {
        if (panelJuego != null) {
            panelJuego.repaint();
        }
    }
    public PanelJuego getPanelJuego() {
        return panelJuego;
    }

    public PanelHUD getPanelHUD() {
        return panelHUD;
    }
}