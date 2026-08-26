package vista;

import modelo.FuncionPelicula;
import modelo.ProductoDulceria;
import modelo.TipoFormato;
import modelo.TipoProducto;
import servicio.CineServicio;

import javax.swing.*;
import java.util.Optional;

public class VentanaCine extends JFrame {
    private JPanel panelRaiz;
    private JPanel panelEncabezado;
    private JLabel lblTitulo;
    private JTabbedPane tabPrincipal;
    private JPanel tabCartelera;
    private JPanel tabDulceria;
    private JPanel pnlFormPelicula;
    private JTextField txtCodigo;
    private JTextField txtDuracion;
    private JComboBox cbxFormato;
    private JLabel Codigo;
    private JLabel Titulo;
    private JLabel Duracion;
    private JLabel Sala;
    private JLabel Capacidad;
    private JLabel cbxFormatoo;
    private JLabel PrecioBase;
    private JScrollPane pniTableCartelera;
    private JTextArea txaCartelera;
    private JButton btnRegistrarPelicula;
    private JButton btnMostrarFuncion;
    private JPanel pnlBotones;
    private JButton btnVenderBoleto;
    private JButton btnPromoEstudiante;
    private JTextField txtCodDulceria;
    private JTextField txtNomDulceria;
    private JComboBox cbxTipoDulceria;
    private JTextField txtPrecioDulceria;
    private JTextArea txaDulceria;
    private JButton bntRegistrarDulceria;
    private JButton btnMostrarCatalogoDulceria;
    private JButton btnVenderDulceria;
    private JButton btnPromoDulceria;
    private JPanel bnlBotones;
    private JTextField txtTitulo;
    private JTextField txtSala;
    private JTextField txtCapacidad;
    private JTextField txtPrecioBase;

    private final CineServicio cineServicio = new CineServicio();

    public VentanaCine() {
        setTitle("NovaCinema - Gestion de Cine");
        setContentPane(panelRaiz);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);

        setLocationRelativeTo(null);

        cbxFormato.setModel(new DefaultComboBoxModel<>(TipoFormato.values()));

        cbxTipoDulceria.setModel(new DefaultComboBoxModel<>(TipoProducto.values()));
        configurarEventos();
    }

    private void configurarEventos(){
        btnRegistrarPelicula.addActionListener(e -> {
            try {
                String codigo = txtCodigo.getText().trim();
                String titulo = txtTitulo.getText().trim();
                int duracion = Integer.parseInt(txtDuracion.getText().trim()) ;
                int sala = Integer.parseInt(txtSala.getText().trim());
                int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
                double precio = Double.parseDouble(txtPrecioBase.getText().trim());
                TipoFormato formato = (TipoFormato) cbxFormato.getSelectedItem();
                FuncionPelicula pelicula = new FuncionPelicula(codigo, titulo, formato, precio,sala, duracion , capacidad);
                        cineServicio.registrarPelicula(pelicula);
                actualizarCartelera();
                limpiarCamposCartelera();


            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog( this, "Revise los campos numericos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnVenderBoleto.addActionListener( e ->{
            String codigo = JOptionPane.showInputDialog(this , "Ingrese el codigo de la funcion");
            if (codigo == null || codigo.isBlank()){
                return;
            }
            Optional<FuncionPelicula> resultado = cineServicio.buscarPelicula(codigo.trim());
            if(resultado.isEmpty()){
                JOptionPane.showMessageDialog(this, "No se encontro una funcion con el codigo" + codigo, "Sin Resultado", JOptionPane.WARNING_MESSAGE);
                return;
            }
           FuncionPelicula pelicula = resultado.get();

String textoCantidad = JOptionPane.showInputDialog( this,  "Pelicula: " + pelicula.getNombre() + "\nButacas Disponibles: " + pelicula.getAsientosDisponibles() + "\n\nCantidad de boletos: ");
            if (textoCantidad== null || textoCantidad.isBlank()){
                return;
            }
       try{
           int cantidad = Integer.parseInt(textoCantidad.trim());

           Double total = cineServicio.venderBoletos(codigo.trim(), cantidad);
           if (total == null){
               JOptionPane.showMessageDialog(this , "No se pudo realizar la venta \n" + "Verifique la cantidad y las butacas disponibles. ", "Venta no realiza", JOptionPane.WARNING_MESSAGE);
               return;
           }
           JOptionPane.showMessageDialog(this , String.format("Venta realizada correctamente" + "\n\nPelicula: %s"+ "\n\nBoletos: %d" + "\nTotal: $%.2f", pelicula.getNombre(), cantidad, total), "taquilla", JOptionPane.INFORMATION_MESSAGE);

           actualizarCartelera();
       }catch (NumberFormatException ex){
           JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad valida", "Errr", JOptionPane.ERROR_MESSAGE);
       }
        });

        bntRegistrarDulceria.addActionListener( e -> {
            try{
                String codigo = txtCodDulceria.getText().trim();
               String nombre = txtNomDulceria.getText().trim();
               double precio = Double.parseDouble(txtPrecioDulceria.getText().trim());
               TipoProducto tipo = (TipoProducto)  cbxTipoDulceria.getSelectedItem();

                ProductoDulceria producto = new ProductoDulceria(codigo, nombre, tipo, precio );
                cineServicio.registrarProducto(producto);

                actualizarDulceria();
                limpiarCamposDulceria();
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this , "Ingrese un precio valido", "Error" , JOptionPane.ERROR_MESSAGE);
            }

        });

        btnVenderDulceria.addActionListener(e ->{
            String codigo = JOptionPane.showInputDialog(this , "Ingrese el codigo del producto o combo");
            if (codigo == null || codigo.isBlank()) {
                return;
            }

            Optional<ProductoDulceria> resultado = cineServicio.buscarProducto(codigo.trim());

            if(resultado.isEmpty()){
                JOptionPane.showMessageDialog(this , "No se encontro un producto con el codigo: " + codigo, "Sin Rwsultado" , JOptionPane.WARNING_MESSAGE);
                return;
            }

            ProductoDulceria producto = resultado.get();

            String textoCantidad = JOptionPane.showInputDialog(this , "Producto:" + producto.getNombre() + "\nTipo: " +producto.getCategoria() + "\nPrecio: " + String.format(("%.2f", producto.calcularPrecio()) + "\nCantidad ");)

            if (codigo == null || textoCantidad.isBlank()){
                retrun;
            }
            try{
                int cantidad = Integer.parseInt(textoCantidad.trim());
                Double total = cineServicio.venderProducto(codigo.trim(), cantidad);

            }
        });
    }
}
