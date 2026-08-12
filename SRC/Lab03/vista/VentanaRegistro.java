package Lab03.vista;

import Lab03.modelo.Estudiante;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {

    private JTextField txtCarnet;
    private JTextField txtNombre;
    private JTextField txtCarrera;
    private JButton btnGuardar;
    private JButton btnLimpiar;

    // ¡Aquí empieza el CONSTRUCTOR! Todo el código de configuración debe ir aquí adentro.
    public VentanaRegistro() {

        setTitle("Sistema de Gestion de Estudiantes - UNA");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Corregido: ClOSE a CLOSE
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel("Inscripcion de Estudiantes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Nunito", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(200, 16, 46));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 8, 8));

        panelFormulario.add(new JLabel("Carnet:"));
        txtCarnet = new JTextField();
        panelFormulario.add(txtCarnet);

        panelFormulario.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Carrera:"));
        txtCarrera = new JTextField();
        panelFormulario.add(txtCarrera);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnLimpiar = new JButton("Limpiar");
        btnGuardar = new JButton("Guardar Estudiante");

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnGuardar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // EVENTO DEL BOTÓN GUARDAR
        btnGuardar.addActionListener(e -> {
            String carnet = txtCarnet.getText();
            String nombre = txtNombre.getText();
            String carrera = txtCarrera.getText();

            // Todo el IF / ELSE debe ir DENTRO de estas llaves del evento
            if (carnet.isEmpty() || nombre.isEmpty() || carrera.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor complete todos los campos.",
                        "Campos Incompletos",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                Estudiante nuevo = new Estudiante(carnet, nombre, carrera);
                JOptionPane.showMessageDialog(this,
                        "Estudiante registrado con éxito:\n" + nuevo.toString(),
                        "Registro Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // EVENTO DEL BOTÓN LIMPIAR
        btnLimpiar.addActionListener(e -> {
            txtCarnet.setText("");
            txtNombre.setText("");
            txtCarrera.setText("");
        });

        this.add(panelPrincipal);

    } // ¡Aquí termina el constructor!
}