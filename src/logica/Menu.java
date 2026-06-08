package logica;

import vista.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import vista.PantallaRanking;
import vista.PantallaModos;

public class Menu extends JFrame {

    private final JButton btnJugar;
    private final JButton btnModosJuego;
    private final JButton btnRanking;
    private Font fuenteKingJack;

    public Menu() {
        datos.GestorConfiguracion.cargar();

        setTitle("Snake++ - Menu Principal");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        // Fuente de letras
        try {
            InputStream is = getClass().getResourceAsStream("/fuentes/KingJack.ttf");
            if (is != null) {
                fuenteKingJack = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(36f);
            } else {
                fuenteKingJack = new Font("Arial", Font.BOLD, 36);
            }
        } catch (Exception e) {
            fuenteKingJack = new Font("Arial", Font.BOLD, 36);
        }

        // Para la imagen que cree ponerla en el menu
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    String rutaFondo = (Juego.modoJuegoActual == 0) ? "/imagenes/menu/fondo.png" : "/imagenes/menu/fondo_leviatan.png";
                    Image fondo = new ImageIcon(getClass().getResource(rutaFondo)).getImage();
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    System.out.println("No se encontro la imagen del menu");
                }
            }
        };

        panelPrincipal.setLayout(null);

        //ordenar los botones que esten alineados a mi imagen
        btnJugar = crearBotonSobreCasilla("Jugar Partida", 710, 290, 470, 70);
        btnModosJuego = crearBotonSobreCasilla("Modos de Juego", 710, 393, 470, 70);
        btnRanking = crearBotonSobreCasilla("Ver Ranking", 710, 495, 470, 70);

        configurarEventos();

        panelPrincipal.add(btnJugar);
        panelPrincipal.add(btnModosJuego);
        panelPrincipal.add(btnRanking);

        add(panelPrincipal);

        vista.GestorSonido.reproducirMusicaAleatoria();
    }

    private JButton crearBotonSobreCasilla(String texto, int x, int y, int ancho, int alto) {
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

    private void configurarEventos() {
        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] opcionesPartida = {"Jugar Partida", "Nueva Partida"};
                String tituloVentana = (Juego.modoJuegoActual == 0) ? "Modo Serpiente" : "Modo Leviatan";

                int eleccionPartida = JOptionPane.showOptionDialog(Menu.this,
                        "Elegi un modo",
                        tituloVentana,
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesPartida, opcionesPartida[0]);

                if (eleccionPartida == JOptionPane.YES_OPTION) {
                    Integer[] nivelesDisponibles = new Integer[Juego.nivelMaximoDesbloqueado];
                    for (int i = 0; i < Juego.nivelMaximoDesbloqueado; i++) nivelesDisponibles[i] = i + 1;

                    Integer nivelElegido = (Integer) JOptionPane.showInputDialog(Menu.this,
                            "Selecciona el nivel para jugar:",
                            "Selector de Nivel", JOptionPane.QUESTION_MESSAGE, null, nivelesDisponibles, nivelesDisponibles[0]);

                    if (nivelElegido != null) {
                        arrancarMotor(nivelElegido);
                    }
                } else if (eleccionPartida == JOptionPane.NO_OPTION) {
                    Juego.nivelMaximoDesbloqueado = 1;
                    datos.GestorConfiguracion.guardar();
                    JOptionPane.showMessageDialog(Menu.this,
                            "Progreso reseteado. Se reestablecieron todos los puntos de los niveles a 0",
                            "Nueva Partida",
                            JOptionPane.INFORMATION_MESSAGE);
                    arrancarMotor(1);
                }
            }
        });

        btnModosJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaModos pantallaModos = new PantallaModos(Menu.this);
                pantallaModos.setVisible(true);
            }
        });

        btnRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] opciones = {"Modo Serpiente", "Modo Leviatan"};
                int eleccion = JOptionPane.showOptionDialog(Menu.this, "Que ranking queres consultar?", "Ranking",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                if (eleccion == JOptionPane.YES_OPTION || eleccion == JOptionPane.NO_OPTION) {
                    int modoElegido = (eleccion == JOptionPane.YES_OPTION) ? 0 : 1;

                    Menu.this.setVisible(false);

                    PantallaRanking pantallaRank = new PantallaRanking(modoElegido, Menu.this);
                    pantallaRank.setVisible(true);
                }
            }
        });
    }

    private void arrancarMotor(int nivel) {
        System.out.println("Iniciando Nivel " + nivel + " | Modo: " + Juego.modoJuegoActual);

        GUI ventanaJuego = new GUI();
        ventanaJuego.inicializarVentana();

        Juego motorJuego = new Juego(ventanaJuego, nivel);

        ventanaJuego.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                motorJuego.procesarTecla(evt.getKeyChar());
            }
        });

        motorJuego.iniciarJuego();
        dispose();
    }
}