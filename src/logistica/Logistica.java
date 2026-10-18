package logistica;

import controlador.EnvioController;
import servicio.EnvioService;
import vista.EnvioView;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación.
 */
public class Logistica {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EnvioView vista = new EnvioView();
            EnvioService servicio = new EnvioService();
            new EnvioController(vista, servicio);
            vista.setVisible(true);
        });
    }
}
