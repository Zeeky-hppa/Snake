package logica;

import java.awt.Color;

public class ModoDeJuego {

    private String nombre;
    private Color colorFondo;
    private Color colorCuadricula;
    private Color colorCabeza;
    private Color colorCola;
    private Color colorMultiplicador;
    private Color colorArcoiris;
    private Color colorEscudo;


    public ModoDeJuego(String nombre, Color colorFondo, Color colorCuadricula,
                       Color colorCabeza, Color colorCola, Color colorMultiplicador,
                       Color colorArcoiris, Color colorEscudo) {
        this.nombre = nombre;
        this.colorFondo = colorFondo;
        this.colorCuadricula = colorCuadricula;
        this.colorCabeza = colorCabeza;
        this.colorCola = colorCola;
        this.colorMultiplicador = colorMultiplicador;
        this.colorArcoiris = colorArcoiris;
        this.colorEscudo = colorEscudo;
    }


    public static final ModoDeJuego MODO_SERPIENTE = new ModoDeJuego(
            "Modo Serpiente",
            new Color(92, 64, 40),  // Fondo Marron tierra
            new Color(120, 80, 50), // Cuadricula Marron claro
            new Color(14, 123, 14), // Cabeza Verde oscuro
            new Color(50, 255, 50), // Cola Verde claro
            new Color(255, 215, 0), // Multiplicador Dorado
            Color.MAGENTA,                  // Arcoiris Magenta
            Color.CYAN                      // Escudo Celeste
    );

    public static final ModoDeJuego MODO_LEVIATAN = new ModoDeJuego(
            "Modo Leviatan",
            new Color(15, 60, 110),  // Fondo Azul mpor el agua
            new Color(30, 90, 160),  // Cuadricula Azul claro
            new Color(180, 0, 0),    // Cabeza Rojo oscuro
            new Color(255, 60, 60),  // Cola Rojo claro
            Color.PINK,                      // Multiplicador Rosa
            Color.ORANGE,                    // Arcoiris Naranja
            Color.WHITE                      // Escudo Blanco
    );

    public String getNombre() { return nombre; }
    public Color getColorFondo() { return colorFondo; }
    public Color getColorCuadricula() { return colorCuadricula; }
    public Color getColorCabeza() { return colorCabeza; }
    public Color getColorCola() { return colorCola; }
    public Color getColorMultiplicador() { return colorMultiplicador; }
    public Color getColorArcoiris() { return colorArcoiris; }
    public Color getColorEscudo() { return colorEscudo; }
}