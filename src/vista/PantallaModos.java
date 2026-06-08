package vista;

import logica.Juego;
import logica.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;

public class PantallaModos extends JDialog {

    private Font fuenteKingJack;

    public PantallaModos(Menu menuPadre) {
        super(menuPadre, "Seleccionar Modo", true);

        setSize(800, 450);
        setLocationRelativeTo(menuPadre);
        setResizable(false);
        setUndecorated(true);

        try {
            InputStream is = getClass().getResourceAsStream("/fuentes/KingJack.ttf");
            if (is != null) {
                fuenteKingJack = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(26f);
            } else {
                fuenteKingJack = new Font("Arial", Font.BOLD, 26);
            }
        } catch (Exception e) {
            fuenteKingJack = new Font("Arial", Font.BOLD, 26);
        }
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    URL urlFondo = getClass().getResource("/imagenes/menu/modos_de_juego.png");
                    if (urlFondo != null) {
                        Image fondo = new ImageIcon(urlFondo).getImage();
                        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                    } else {
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        };
        panelFondo.setLayout(null);

        JButton btnSerpiente = crearBotonConTexto("MODO SERPIENTE", 80, 210, 310, 65);
        JButton btnLeviatan = crearBotonConTexto("MODO LEVIATAN", 410, 210, 310, 65);
        JButton btnCerrar = crearBotonConTexto("CERRAR", 300, 380, 200, 40);
        btnCerrar.setFont(fuenteKingJack.deriveFont(20f));

        btnSerpiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Este es para el modo serpientita
                Juego.modoJuegoActual = 0;
                datos.GestorConfiguracion.guardar();
                menuPadre.repaint();
                dispose();
            }
        });

        btnLeviatan.addActionListener(new ActionListener() { //Y este para el modo leviatan
            @Override
            public void actionPerformed(ActionEvent e) {
                Juego.modoJuegoActual = 1;
                datos.GestorConfiguracion.guardar();
                menuPadre.repaint();
                dispose();
            }
        });

        btnCerrar.addActionListener(e -> dispose());

        panelFondo.add(btnSerpiente);
        panelFondo.add(btnLeviatan);
        panelFondo.add(btnCerrar);

        setContentPane(panelFondo);
    }

    private JButton crearBotonConTexto(String texto, int x, int y, int ancho, int alto) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, ancho, alto);
        boton.setFont(fuenteKingJack);
        boton.setForeground(new Color(250, 240, 210));
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
}