package vista;

import datos.Ranking;
import logica.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;

public class PantallaRanking extends JFrame {

    private Font fuenteKingJack;

    public PantallaRanking(int modoElegido, Menu menuAnterior) {
        setTitle("Snake++ - Ranking");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            InputStream is = getClass().getResourceAsStream("/fuentes/KingJack.ttf");
            if (is != null) {
                fuenteKingJack = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(28f);
            } else {
                fuenteKingJack = new Font("Arial", Font.BOLD, 28);
            }
        } catch (Exception e) {
            fuenteKingJack = new Font("Arial", Font.BOLD, 28);
        }

        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    //Aca dependiendo el modo de juego cree 2 imagenes para que cada modo tenga la suya
                    String rutaFondo = (modoElegido == 0) ? "/imagenes/menu/fondo_ranking.png" : "/imagenes/menu/fondo_ranking_leviatan.png";

                    URL urlFondo = getClass().getResource(rutaFondo);
                    if (urlFondo != null) {
                        Image fondo = new ImageIcon(urlFondo).getImage();
                        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                    } else {
                        g.setColor(Color.RED);
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        };
        panelFondo.setLayout(null);
        String textoRanking = Ranking.getInstance().obtenerTextoRanking(modoElegido);
        JTextArea areaTexto = new JTextArea(textoRanking);
        areaTexto.setFont(new Font("Monospaced", Font.BOLD, 18));
        areaTexto.setForeground(new Color(250, 240, 210));
        areaTexto.setEditable(false);
        areaTexto.setOpaque(false);
        areaTexto.setHighlighter(null);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        scrollPane.setBounds(300, 200, 700, 450);
        panelFondo.add(scrollPane);

        JButton btnVolver = new JButton("Volver al Menu");
        btnVolver.setFont(fuenteKingJack);
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBounds(50, 600, 250, 50);
        btnVolver.setOpaque(false);
        btnVolver.setContentAreaFilled(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuAnterior.setVisible(true);
                dispose();
            }
        });
        panelFondo.add(btnVolver);

        setContentPane(panelFondo);

        javax.swing.SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
    }
}