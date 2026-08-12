package Lab03;


import Lab03.vista.VentanaRegistro;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            VentanaRegistro ventana = new VentanaRegistro();
            ventana.setVisible(true);
        });

    }
}
