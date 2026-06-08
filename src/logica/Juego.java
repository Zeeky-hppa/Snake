package logica;

import datos.Ranking;
import logica.alimentos.*;
import logica.entidades.*;
import logica.powerups.*;
import vista.GUI;

import javax.swing.JOptionPane;

public class Juego implements Runnable {

    private Tablero tablero;
    private Reloj relojPrincipal;
    private GUI gui;
    private java.util.Random random;
    private boolean enEjecucion;
    private Thread hiloJuego;
    private int contadorTicks = 0;
    private Alimento alimentoActual;
    private PowerUp powerUpActual;

    // VAR PARA NIVELES
    private int nivelActual;
    private boolean yaDesbloqueoSiguiente;
    public static int nivelMaximoDesbloqueado = 1;

    // VAR PARA MODOS
    public static int modoJuegoActual = 0;

    public Juego(GUI gui, int nivelActual) {
        this.gui = gui;
        this.nivelActual = nivelActual;
        this.yaDesbloqueoSiguiente = false;
        this.tablero = new Tablero();
        datos.Nivel.cargarLaberinto(this.tablero, nivelActual);
        this.relojPrincipal = new Reloj();
        this.enEjecucion = false;
        this.random = new java.util.Random();
        this.gui.getPanelJuego().setTablero(this.tablero);

        //  GENERAR LOS PRIMETOS ITEMS
        generarNuevoAlimento();
        generarNuevoPowerUp();
    }

    public void iniciarJuego() {
        this.enEjecucion = true;
        this.relojPrincipal.iniciarCronometro();
        this.hiloJuego = new Thread(this);
        this.hiloJuego.start();
        vista.GestorSonido.reproducirMusicaAleatoria();
    }

    private void verificarDesbloqueoNivel() {
        if (yaDesbloqueoSiguiente) return;
        int puntos = tablero.getSerpiente().getPuntos();
        boolean metaAlcanzada = false;
        if (nivelActual == 1 && puntos >= 500) metaAlcanzada = true;
        else if (nivelActual == 2 && puntos >= 750) metaAlcanzada = true;
        else if (nivelActual == 3 && puntos >= 1000) metaAlcanzada = true;
        else if (nivelActual == 4 && puntos >= 1200) metaAlcanzada = true;

        if (metaAlcanzada) {
            yaDesbloqueoSiguiente = true;

            if (nivelMaximoDesbloqueado == nivelActual && nivelActual < 5) {
                nivelMaximoDesbloqueado++;
                datos.GestorConfiguracion.guardar();
                JOptionPane.showMessageDialog(gui,
                        "Alcanzaste la meta de " + puntos + " puntos.\n" +
                                "NIVEL " + nivelMaximoDesbloqueado + " DESBLOQUEADO!\n\n" +
                                "Cerrar cartel",
                        "Nuevo Nivel!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void run() {
        while (enEjecucion) {
            try {
                double velActual = tablero.getSerpiente().getVelocidad();
                int tiempoEspera = (int) (200 / velActual);
                Thread.sleep(tiempoEspera);
                loop();
            } catch (InterruptedException e) {
                enEjecucion = false;
            }
        }
    }

    public void loop() {
        Serpiente s = tablero.getSerpiente();
        int ultimaColaX = s.getCuerpo().get(s.getCuerpo().size() - 1).getX();
        int ultimaColaY = s.getCuerpo().get(s.getCuerpo().size() - 1).getY();
        s.mover();
        verificarColisiones();
        s.actualizarEfectos();

        if (!enEjecucion) return;

        tablero.getGrilla().vaciarCasillero(ultimaColaY, ultimaColaX);
        tablero.actualizarPosicionesEnGrilla();

        if (powerUpActual != null && !powerUpActual.fueConsumido()) {
            if (tablero.getEntidadEn(powerUpActual.getX(), powerUpActual.getY()) == null) {
                tablero.getGrilla().colocarEntidad(powerUpActual, powerUpActual.getY(), powerUpActual.getX());
            }
        }

        if (alimentoActual != null && !alimentoActual.fueConsumido()) {
            if (tablero.getEntidadEn(alimentoActual.getX(), alimentoActual.getY()) == null) {
                tablero.getGrilla().colocarEntidad(alimentoActual, alimentoActual.getY(), alimentoActual.getX());
            }
        }

        contadorTicks++;
        if (contadorTicks >= 5) {
            relojPrincipal.incrementarSegundo();
            contadorTicks = 0;
        }

        gui.actualizarPantalla();
        gui.getPanelHUD().actualizar(s.getPuntos(), relojPrincipal.getTiempoFormateado());
        verificarDesbloqueoNivel();

        if (random.nextInt(50) == 0) {
            vista.GestorSonido.reproducirEfecto("/sonidos/hiss.wav");
        }

        gui.actualizarPantalla();
    }

    public void verificarColisiones() {
        Serpiente s = tablero.getSerpiente();
        Cabeza cab = s.getCabeza();

        // PAREDES EXTERNAS
        if (cab.getX() < 0 || cab.getX() >= Tablero.ANCHO ||
                cab.getY() < 0 || cab.getY() >= Tablero.ALTO) {
            Pared paredExterna = new Pared(cab.getX(), cab.getY());
            paredExterna.afectar(s);
        } else {
            // COLISIONES Para Frutas, Poderes y Paredes de los niveles
            Entidad e = tablero.getEntidadEn(cab.getX(), cab.getY());

            if (e != null && e != cab && !e.esCuerpo()) {
                e.afectar(s);

                if (e.fueConsumido()) {
                    tablero.getGrilla().vaciarCasillero(e.getY(), e.getX());
                    e.regenerar(this);
                }
            }

            // Revisamos si la cabeza piso la cola
            for (Cola c : s.getCuerpo()) {
                if (cab.getX() == c.getX() && cab.getY() == c.getY()) {
                    c.afectar(s);
                    break;
                }
            }
        }


        //  GAME OVER Y RETORNO AL MENU
        if (!s.isViva()) {
            enEjecucion = false;
            relojPrincipal.detener();

            boolean esNuevoRecord = Ranking.getInstance().esTop5(modoJuegoActual, nivelActual, s.getPuntos());

            if (esNuevoRecord) {
                String nombreJugador = JOptionPane.showInputDialog(gui,
                        "¡NUEVO RECORD EN EL NIVEL " + nivelActual + "!\n" +
                                "Puntos: " + s.getPuntos() + "\n" +
                                "Tiempo: " + relojPrincipal.getTiempoFormateado() + "\n\n" +
                                "Ingresa tu nombre para estar el Ranking:",
                        "Top 5 Alcanzado!",
                        JOptionPane.QUESTION_MESSAGE);

                if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
                    nombreJugador = "Jugador Anonimo";
                }

                // ACÁ ESTABA EL ERROR: Cambiado de esTop5 a agregarAlTop5
                Ranking.getInstance().agregarAlTop5(modoJuegoActual, nivelActual, nombreJugador, s.getPuntos(), relojPrincipal.getTiempoFormateado());
            } else {
                JOptionPane.showMessageDialog(gui,
                        "PERDISTE\n" +
                                "Puntos: " + s.getPuntos() + "\n" +
                                "Tiempo: " + relojPrincipal.getTiempoFormateado() + "\n\n" +
                                "Que malo que sos, no entraste ni al top  Segui practicando",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            gui.dispose();
            Menu menuPrincipal = new Menu();
            menuPrincipal.setVisible(true);
        }
    }

    public void procesarTecla(char tecla) {
        Serpiente s = tablero.getSerpiente();
        switch (Character.toUpperCase(tecla)) {
            case 'W': s.cambiarDireccion("ARRIBA"); break;
            case 'S': s.cambiarDireccion("ABAJO"); break;
            case 'A': s.cambiarDireccion("IZQUIERDA"); break;
            case 'D': s.cambiarDireccion("DERECHA"); break;
        }
    }

    public void generarNuevoAlimento() {
        int x, y;
        do {
            x = random.nextInt(Tablero.ANCHO);
            y = random.nextInt(Tablero.ALTO);
        } while (tablero.getEntidadEn(x, y) != null);

        this.alimentoActual = FabricaEntidades.crearAlimentoAleatorio(x, y);
        tablero.agregarEntidad(this.alimentoActual);
    }

    public void generarNuevoPowerUp() {
        int x, y;
        do {
            x = random.nextInt(Tablero.ANCHO);
            y = random.nextInt(Tablero.ALTO);
        } while (tablero.getEntidadEn(x, y) != null);

        this.powerUpActual = FabricaEntidades.crearPowerUpAleatorio(x, y);
        tablero.agregarEntidad(this.powerUpActual);
    }
}