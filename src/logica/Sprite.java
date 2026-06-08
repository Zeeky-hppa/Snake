package logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.net.URL;

public class Sprite {
    private Color color;
    private Image imagen;

    public Sprite(Color color) {
        this.color = color;
    }

    public Sprite(Color color, String rutaImagen) {
        this.color = color;

        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            try {
                URL url = getClass().getResource(rutaImagen);

                if (url != null) {
                    this.imagen = ImageIO.read(url);
                } else {
                    System.out.println("LA RUTA DE LA CARGA DE LA IMAGEN NO ES LA CORRECTA : " + rutaImagen);
                }
            } catch (Exception e) {
                System.out.println("ERROR AL CARGAR IMAGEN: " + rutaImagen + " " + e.getMessage());
            }
        }
    }

    public void setColor(Color nuevoColor) {
        this.color = nuevoColor;
    }

    public void dibujar(Graphics g, int x, int y, int tamano) {
        if (this.imagen != null) {
            g.drawImage(this.imagen, x, y, tamano, tamano, null);
        } else {
            g.setColor(color);
            g.fillRect(x, y, tamano, tamano);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, tamano, tamano);
        }
    }
}