package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Interfaz gráfica de usuario basada en Swing.
 */
public class EnvioView extends JFrame {
    private JTextField txtNumero;
    private JTextField txtCliente;
    private JTextField txtPeso;
    private JTextField txtDistancia;
    private JComboBox<String> cbTipo;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnRetirar;
    private JTable tablaEnvios;
    private DefaultTableModel modeloTabla;

    public EnvioView() {
        setTitle("Operador Logístico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Formulario superior
        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Envío"));

        panelFormulario.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        panelFormulario.add(txtNumero);

        panelFormulario.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"Terrestre", "Aéreo", "Marítimo"});
        panelFormulario.add(cbTipo);

        panelFormulario.add(new JLabel("Cliente:"));
        txtCliente = new JTextField();
        panelFormulario.add(txtCliente);

        panelFormulario.add(new JLabel("Distancia en Km:"));
        txtDistancia = new JTextField();
        panelFormulario.add(txtDistancia);

        panelFormulario.add(new JLabel("Peso en Kg:"));
        txtPeso = new JTextField();
        panelFormulario.add(txtPeso);

        // Botones
        JPanel panelBotonesForm = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        btnRetirar = new JButton("Retirar Envío");

        panelBotonesForm.add(btnGuardar);
        panelBotonesForm.add(btnCancelar);
        panelBotonesForm.add(btnRetirar);

        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelFormulario, BorderLayout.CENTER);
        panelNorte.add(panelBotonesForm, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);

        // Tabla de Registros
        String[] columnas = {"Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEnvios = new JTable(modeloTabla);
        add(new JScrollPane(tablaEnvios), BorderLayout.CENTER);
    }

    // Getters para el controlador
    public String getNumero() { return txtNumero.getText().trim(); }
    public String getCliente() { return txtCliente.getText().trim(); }
    public String getPeso() { return txtPeso.getText().trim(); }
    public String getDistancia() { return txtDistancia.getText().trim(); }
    public String getTipoSeleccionado() { return (String) cbTipo.getSelectedItem(); }
    public JTable getTablaEnvios() { return tablaEnvios; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }

    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnRetirar() { return btnRetirar; }

    public void limpiarCampos() {
        txtNumero.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cbTipo.setSelectedIndex(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}