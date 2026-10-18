package controlador;

import factory.*;
import modelo.Envio;
import servicio.EnvioService;
import vista.EnvioView;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador que gestiona los eventos de la vista y coordina con el servicio y las fábricas.
 */
public class EnvioController {
    private final EnvioView vista;
    private final EnvioService servicio;
    private final Map<String, EnvioFactory> fabricas;

    public EnvioController(EnvioView vista, EnvioService servicio) {
        this.vista = vista;
        this.servicio = servicio;
        this.fabricas = new HashMap<>();

        // Registrar las fábricas disponibles
        fabricas.put("Terrestre", new TerrestreFactory());
        fabricas.put("Aéreo", new AereoFactory());
        fabricas.put("Marítimo", new MaritimoFactory());

        // Asignar los listeners a los botones
        this.vista.getBtnGuardar().addActionListener(e -> guardarEnvio());
        this.vista.getBtnCancelar().addActionListener(e -> vista.limpiarCampos());
        this.vista.getBtnRetirar().addActionListener(e -> retirarEnvio());
    }

    private void guardarEnvio() {
        try {
            String codigo = vista.getNumero();
            String cliente = vista.getCliente();
            String tipo = vista.getTipoSeleccionado();

            if (codigo.isEmpty() || cliente.isEmpty() || vista.getPeso().isEmpty() || vista.getDistancia().isEmpty()) {
                vista.mostrarError("Por favor complete todos los campos.");
                return;
            }

            double peso = Double.parseDouble(vista.getPeso());
            double distancia = Double.parseDouble(vista.getDistancia());

            if (peso <= 0 || distancia <= 0) {
                vista.mostrarError("El peso y la distancia deben ser valores mayores a cero.");
                return;
            }

            EnvioFactory factory = fabricas.get(tipo);
            if (factory == null) {
                vista.mostrarError("Tipo de envío no válido.");
                return;
            }

            // Creación mediante Factory Method
            Envio nuevoEnvio = factory.crearEnvio(codigo, cliente, peso, distancia);

            if (servicio.agregarEnvio(nuevoEnvio)) {
                actualizarTabla();
                vista.limpiarCampos();
                vista.mostrarMensaje("Envío guardado con éxito.");
            } else {
                vista.mostrarError("El código de envío ya existe.");
            }

        } catch (NumberFormatException ex) {
            vista.mostrarError("Por favor ingrese valores numéricos válidos para peso y distancia.");
        }
    }

    private void retirarEnvio() {
        int filaSeleccionada = vista.getTablaEnvios().getSelectedRow();

        if (filaSeleccionada >= 0) {
            String codigo = (String) vista.getModeloTabla().getValueAt(filaSeleccionada, 1);
            if (servicio.eliminarEnvio(codigo)) {
                actualizarTabla();
                vista.mostrarMensaje("Envío eliminado correctamente.");
            } else {
                vista.mostrarError("No se pudo eliminar el envío.");
            }
        } else {
            String codigo = JOptionPane.showInputDialog(vista, "Ingrese el código del envío a eliminar:");
            if (codigo != null && !codigo.trim().isEmpty()) {
                if (servicio.eliminarEnvio(codigo.trim())) {
                    actualizarTabla();
                    vista.mostrarMensaje("Envío eliminado correctamente.");
                } else {
                    vista.mostrarError("El código ingresado no existe.");
                }
            }
        }
    }

    private void actualizarTabla() {
        vista.getModeloTabla().setRowCount(0);
        for (Envio e : servicio.obtenerTodos()) {
            // Formatear el costo como número decimal plano sin notación científica
            String costoFormateado = String.format("%.1f", e.calcularTarifa()).replace(',', '.');

            Object[] fila = new Object[]{
                e.getClass().getSimpleName(),
                e.getCodigo(),
                e.getCliente(),
                e.getPeso(),
                e.getDistancia(),
                costoFormateado
            };
            vista.getModeloTabla().addRow(fila);
        }
    }
}