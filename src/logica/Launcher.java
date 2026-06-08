package logica;

import javax.swing.SwingUtilities;

public class Launcher {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // para que el menu prncipal sea visible en pantalla
                Menu menuPrincipal = new Menu();
                menuPrincipal.setVisible(true);
            }
        });
    }
}