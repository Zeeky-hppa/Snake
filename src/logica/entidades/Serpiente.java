package logica.entidades;

import logica.*;
import logica.alimentos.*;
import logica.estados.EstadoNormal;
import logica.estados.EstadoSerpiente;
import logica.estados.EstadoMultiplicador;
import logica.powerups.PowerUp;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class Serpiente implements VisitanteParaLaSerpiente {

    private Cabeza cabeza;
    private List<Cola> cuerpo;
    private String direccion;
    private boolean viva;
    private int puntos;
    private EstadoSerpiente estadoActual;
    private double velocidad;
    private PowerUp poderActivo = null;
    private int tiempoPoder = 0;
    private boolean modoArcoiris = false;
    private int ticksArcoiris = 0;
    private boolean atraviesaParedes = false;

    public Serpiente(int xInicial, int yInicial) {
        this.cabeza = new Cabeza(xInicial, yInicial);
        this.cuerpo = new ArrayList<>();
        this.direccion = "DERECHA";
        this.velocidad = 1;
        this.estadoActual = new EstadoNormal();
        this.viva = true;
        this.puntos = 0;

        crecer(2);
        actualizarApariencia();
    }

    public void actualizarApariencia() {
        if (this.poderActivo != null || this.modoArcoiris) {
            return;
        }
        if (Juego.modoJuegoActual == 1) {
            this.cabeza.setSpriteActual(new Sprite(new Color(180, 0, 0), "/imagenes/leviatan/cabezaleviatan.png"));
            for (Cola c : cuerpo) {
                c.setSpriteActual(new Sprite(new Color(255, 60, 60), "/imagenes/leviatan/colaleviatan.png"));
            }
        }
        else {
            this.cabeza.setSpriteActual(new Sprite(new Color(0, 200, 0), "/imagenes/serpiente/cabeza_normal.png"));
            for (Cola c : cuerpo) {
                c.setSpriteActual(new Sprite(new Color(50, 255, 50), "/imagenes/serpiente/cuerpo_normal.png"));
            }
        }
    }

    public void aplicarSkinPoder(String rutaCab, String rutaCola) {
        System.out.println("Cargando Skins: " + rutaCab);
        vista.GestorSonido.reproducirEfecto("/sonidos/sonido_spu.wav");

        if (rutaCab == null || rutaCola == null) {
            this.cabeza.setSpriteActual(new Sprite(Color.WHITE));
            for (Cola c : cuerpo) {
                c.setSpriteActual(new Sprite(Color.WHITE));}
            return;
        }
        String cabezaFinal = rutaCab;
        String colaFinal = rutaCola;
        if (Juego.modoJuegoActual == 1) {
            cabezaFinal = "/imagenes/leviatan/cabezaleviatan.png";

            if (rutaCola.contains("normal")) {
                colaFinal = "/imagenes/leviatan/colaleviatan.png";
            }
        }
        this.cabeza.setSpriteActual(new Sprite(Color.WHITE, cabezaFinal));
        for (Cola c : cuerpo) {
            c.setSpriteActual(new Sprite(Color.WHITE, colaFinal));
        }
    }

    public boolean puedeAtravesarParedes() { return atraviesaParedes; }
    public void setAtraviesaParedes(boolean estado) { this.atraviesaParedes = estado; }
    public Cabeza getCabeza() { return cabeza; }
    public List<Cola> getCuerpo() { return cuerpo; }
    public boolean isViva() { return viva; }
    public void matar() { this.viva = false; }
    public int getPuntos() { return puntos; }
    public EstadoSerpiente getEstadoActual() { return estadoActual; }
    public void setEstadoActual(EstadoSerpiente nuevoEstado) { this.estadoActual = nuevoEstado; }
    public void setVelocidad(double nuevaVelocidad) { this.velocidad = nuevaVelocidad; }
    public double getVelocidad() { return this.velocidad; }

    public void sumarPuntos(int puntosExtra) {
        this.puntos += this.estadoActual.calcularPuntos(puntosExtra);
    }

    public void crecer(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Cola ultima = cuerpo.isEmpty() ? null : cuerpo.get(cuerpo.size() - 1);
            Cola nuevaCola = (ultima != null) ? new Cola(ultima.getX(), ultima.getY()) : new Cola(cabeza.getX(), cabeza.getY());
            if (ultima != null && ultima.getSprite() != null) {
                nuevaCola.setSpriteActual(ultima.getSprite());
            } else if (this.cabeza.getSprite() != null) {
                nuevaCola.setSpriteActual(this.cabeza.getSprite());
            }
            cuerpo.add(nuevaCola);
        }
        actualizarApariencia();
    }

    public void reducirCuerpoALaMitad() {
        int cantidadAEliminar = cuerpo.size() / 2;
        if (cantidadAEliminar > 0) {
            for (int i = 0; i < cantidadAEliminar; i++) {
                cuerpo.remove(cuerpo.size() - 1);
            }
        }
    }

    public void setPoderActivo(PowerUp poder, int tiempo) {
        this.poderActivo = poder;
        this.tiempoPoder = tiempo;
    }

    public void activarEfectoArcoiris() {
        this.modoArcoiris = true;
        this.ticksArcoiris = 25;
        vista.GestorSonido.reproducirEfecto("/sonidos/sonido_spu.wav");
    }

    public void cambiarDireccion(String nuevaDir) {
        if ((this.direccion.equals("ARRIBA") && nuevaDir.equals("ABAJO")) ||
                (this.direccion.equals("ABAJO") && nuevaDir.equals("ARRIBA")) ||
                (this.direccion.equals("IZQUIERDA") && nuevaDir.equals("DERECHA")) ||
                (this.direccion.equals("DERECHA") && nuevaDir.equals("IZQUIERDA"))) return;
        this.direccion = nuevaDir;
    }

    public void mover() {
        for (int i = cuerpo.size() - 1; i > 0; i--) {
            cuerpo.get(i).setPosicion(cuerpo.get(i - 1).getX(), cuerpo.get(i - 1).getY());
        }
        if (!cuerpo.isEmpty()) {
            cuerpo.get(0).setPosicion(cabeza.getX(), cabeza.getY());
        }
        switch (direccion) {
            case "ARRIBA": cabeza.setPosicion(cabeza.getX(), cabeza.getY() - 1); break;
            case "ABAJO": cabeza.setPosicion(cabeza.getX(), cabeza.getY() + 1); break;
            case "IZQUIERDA": cabeza.setPosicion(cabeza.getX() - 1, cabeza.getY()); break;
            case "DERECHA": cabeza.setPosicion(cabeza.getX() + 1, cabeza.getY()); break;
        }
    }

    public void actualizarEfectos() {
        if (poderActivo != null) {
            tiempoPoder--;
            if (tiempoPoder <= 0) {
                poderActivo.revertirEfecto(this);
                poderActivo = null;
                actualizarApariencia();
            }
        }

        if (modoArcoiris) {
            ticksArcoiris--;
            String[] skinsArcoiris = {
                    "/imagenes/serpiente/cuerpo_escudo.png",
                    "/imagenes/serpiente/cuerpo_fantasmal.png",
                    "/imagenes/serpiente/cuerpo_invencibilidad.png",
                    "/imagenes/serpiente/cuerpo_multiplicador.png",
                    (Juego.modoJuegoActual == 1) ? "/imagenes/leviatan/colaleviatan.png" : "/imagenes/serpiente/cuerpo_normal.png"
            };
            String skinActual = skinsArcoiris[ticksArcoiris % skinsArcoiris.length];
            String cabezaActual = (Juego.modoJuegoActual == 1) ? "/imagenes/leviatan/cabezaleviatan.png" : "/imagenes/serpiente/cabeza_normal.png";
            this.cabeza.setSpriteActual(new Sprite(Color.WHITE, cabezaActual));
            for (Cola c : cuerpo) {
                c.setSpriteActual(new Sprite(Color.WHITE, skinActual));
            }
            if (ticksArcoiris <= 0) {
                modoArcoiris = false;
                actualizarApariencia();
            }
        }
    }

    @Override public void visitar(Manzana m) { crecer(m.getBloquesCrecimiento()); sumarPuntos(m.getPuntaje()); vista.GestorSonido.reproducirEfecto("/sonidos/comer.wav");}
    @Override public void visitar(Banana b) { crecer(b.getBloquesCrecimiento()); sumarPuntos(b.getPuntaje()); vista.GestorSonido.reproducirEfecto("/sonidos/comer.wav");}
    @Override public void visitar(Pera p) { crecer(p.getBloquesCrecimiento()); sumarPuntos(p.getPuntaje()); vista.GestorSonido.reproducirEfecto("/sonidos/comer.wav");}
    @Override public void visitar(Durazno d) { crecer(d.getBloquesCrecimiento()); sumarPuntos(d.getPuntaje()); vista.GestorSonido.reproducirEfecto("/sonidos/comer.wav");}
    @Override public void visitar(Sandia s) { crecer(s.getBloquesCrecimiento()); sumarPuntos(s.getPuntaje()); vista.GestorSonido.reproducirEfecto("/sonidos/comer.wav");}
}