package InterfazGrafica;

import LogicaNegocio.Empleador;
import LogicaNegocio.Oferta;
import LogicaNegocio.Solicitante;
import LogicaNegocio.VentanaPrincipalDAO;
import LogicaNegocio.EliminarOfertaDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame implements MouseListener{
    private JButton btnEditarInformacion;
    private JButton btnNuevaOferta;
    private JButton btnCerrarSesion;
    private JButton btnBuscarOferta;
    private JTextField txtBusqueda;
    private JPanel panelOferta;
    private JPanel contenedor;
    private JLabel lblNombre;
    private JScrollPane scrollOfertas;
    private JPanel panelOfertas;
    private JLabel lblOfertas;
    
    private Solicitante solicitante;
    private Empleador empleador;
    private ArrayList<Oferta> listaOfertas;
    
    private int tipo;
    private int idCuenta;
    
    public VentanaPrincipal(int idCuenta){
        this.idCuenta=idCuenta;
        setTitle("Bolsa de trabajo UV");
        setSize(800,500);
        setMinimumSize(new Dimension(800,500));
        setLocationRelativeTo(null);
        //setIconImage(new ImageIcon(getClass().getResource("/RecursosGraficos/iconoDrakzo3.png")).getImage());
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        VentanaPrincipalDAO ventanaPrincipal = new VentanaPrincipalDAO();
        this.tipo = ventanaPrincipal.getTipoCuenta(idCuenta);
        inicializarComponentes();
        if (tipo == 0){
            btnNuevaOferta.setVisible(false);
            solicitante = ventanaPrincipal.getSolicitante(idCuenta);
            lblNombre.setText(solicitante.getNombre());
            mostrarOfertas(ventanaPrincipal.getOfertasPorDescripcion(solicitante.getAreaProfesional()));
        }else{
            empleador = ventanaPrincipal.getEmpleador(idCuenta);
            lblNombre.setText("Empresa: "+empleador.getNombreEmpresa());
            txtBusqueda.setVisible(false);
            btnBuscarOferta.setVisible(false);
            mostrarOfertasEmpleador(empleador.getListaOfertas());
        }
    }
    
    public void inicializarComponentes(){
        contenedor = new JPanel();
        contenedor.setLayout(new BorderLayout());
        add(contenedor);
        JPanel panelBotonesGeneral = new JPanel();
        panelBotonesGeneral.setLayout(new BorderLayout());
        JPanel panelIzquierdo = new JPanel();
        FlowLayout flowIzquierdo = new FlowLayout();
        flowIzquierdo.setHgap(10);
        flowIzquierdo.setVgap(10);
        panelIzquierdo.setLayout(flowIzquierdo);
        panelIzquierdo.add(btnEditarInformacion = new JButton("Editar información"));
        panelIzquierdo.add(lblNombre = new JLabel("Nombre"));
        JPanel panelDerecho = new JPanel();
        FlowLayout flowDerecho = new FlowLayout(FlowLayout.RIGHT);
        flowDerecho.setHgap(10);
        flowDerecho.setVgap(10);
        panelDerecho.setLayout(flowDerecho);
        panelDerecho.add(btnNuevaOferta = new JButton("Nueva oferta"));
        panelDerecho.add(txtBusqueda = new JTextField("hola mundo")); 
        panelDerecho.add(btnBuscarOferta = new JButton("Buscar oferta"));
        panelDerecho.add(btnCerrarSesion = new JButton("Cerrar sesión"));
        btnNuevaOferta.addMouseListener(this);
        btnCerrarSesion.addMouseListener(this);
        btnBuscarOferta.addMouseListener(this);
        panelBotonesGeneral.add(panelIzquierdo, BorderLayout.WEST);
        panelBotonesGeneral.add(panelDerecho, BorderLayout.EAST);
        contenedor.add(panelBotonesGeneral, BorderLayout.PAGE_START);
        BorderLayout borderCentral = new BorderLayout();
        borderCentral.setVgap(10);
        JPanel panelCentral = new JPanel(borderCentral);
        contenedor.add(panelCentral, BorderLayout.CENTER);
        String tituloOfertas = "";
        if (tipo==0){
            tituloOfertas = "Resultados: ";
        }else{
            tituloOfertas = "Ofertas: ";
        }
        panelCentral.add(lblOfertas = new JLabel(tituloOfertas), BorderLayout.PAGE_START);
        panelCentral.add(scrollOfertas = new JScrollPane(panelOfertas = new JPanel()), BorderLayout.CENTER);
        GridLayout gridOfertas = new GridLayout();
        gridOfertas.setColumns(1);
        gridOfertas.setRows(10);
        panelOfertas.setLayout(gridOfertas);
    }
    public void mostrarOfertas(ArrayList<Oferta> listaOfertas){
        panelOfertas.removeAll();
        for (int c = 0; c<listaOfertas.size(); c++){
            Oferta oferta = listaOfertas.get(c);
            JPanel panelOferta = new JPanel();
            panelOferta.setLayout(new BorderLayout());
            JLabel vacante = new JLabel("Vacante: "+oferta.getVacante());
            JLabel empresa = new JLabel("Empresa: "+oferta.getEmpresa());
            JButton botonVerMas = new JButton("Ver más");
            botonVerMas.setName(String.valueOf(oferta.getIdOferta()));
            botonVerMas.addMouseListener(this);
            JPanel panelBoton = new JPanel();
            panelBoton.setLayout(new FlowLayout());
            panelBoton.add(botonVerMas);
            panelOferta.add(panelBoton, BorderLayout.LINE_END);
            JPanel panelDatos = new JPanel();
            FlowLayout flowDatos = new FlowLayout();
            flowDatos.setHgap(20);
            flowDatos.setVgap(10);
            panelDatos.setLayout(flowDatos);
            panelDatos.add(empresa);
            panelDatos.add(vacante);
            panelOferta.add(panelDatos, BorderLayout.LINE_START);
            panelOfertas.add(panelOferta);
            panelOferta.setBorder(BorderFactory.createLineBorder(Color.gray));
        }
        panelOfertas.setVisible(false);
        panelOfertas.setVisible(true);
    }
    public void mostrarOfertasEmpleador(ArrayList<Oferta> listaOfertas){
        panelOfertas.removeAll();
        for (int c = 0; c<listaOfertas.size(); c++){
            Oferta oferta = listaOfertas.get(c);
            JPanel panelOferta = new JPanel();
            panelOferta.setLayout(new BorderLayout());
            JLabel vacante = new JLabel(oferta.getVacante());
            JLabel vacantes = new JLabel("Numero de vacantes: "+String.valueOf(oferta.getNumeroVacantes()));
            JButton botonEditar = new JButton("Editar");
            botonEditar.setName(String.valueOf(oferta.getIdOferta()));
            botonEditar.addMouseListener(this);
            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.setName(String.valueOf(oferta.getIdOferta()));
            botonEliminar.addMouseListener(this);
            JPanel panelBoton = new JPanel();
            panelBoton.setLayout(new FlowLayout(FlowLayout.RIGHT));
            panelBoton.add(botonEditar);
            panelBoton.add(botonEliminar);
            JPanel panelDatos = new JPanel();
            FlowLayout flowDatos = new FlowLayout();
            flowDatos.setHgap(20);
            flowDatos.setVgap(10);
            panelDatos.setLayout(flowDatos);
            panelDatos.add(vacante);
            panelDatos.add(vacantes);
            panelOferta.add(panelDatos, BorderLayout.LINE_START);
            panelOferta.add(panelBoton, BorderLayout.LINE_END);
            panelOfertas.add(panelOferta);
            panelOferta.setBorder(BorderFactory.createLineBorder(Color.gray));
        }
        panelOfertas.setVisible(false);
        panelOfertas.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent evento) {
        if (evento.getSource().equals(this.btnBuscarOferta)){
            VentanaPrincipalDAO ventanaPrincipal = new VentanaPrincipalDAO();
            mostrarOfertas(ventanaPrincipal.getOfertasPorClave(this.txtBusqueda.getText()));
        }
        if(evento.getSource().equals(btnCerrarSesion)){
            new VentanaInicioSesion();
            dispose();
        }
        if(evento.getSource().equals(btnNuevaOferta)){
            new VentanaNuevaOferta(empleador.getIdEmpleador(), empleador.getNombreEmpresa(), this.idCuenta);
            dispose();
        }
        if (evento.getSource() instanceof JButton){
            JButton boton = (JButton) evento.getSource();
            if (boton.getText().equals("Ver más")){
                new VentanaDetallesOferta(Integer.parseInt(boton.getName()));
            }else if(boton.getText().equals("Eliminar")){
            	EliminarOfertaDAO eliminarOferta = new EliminarOfertaDAO();
            	eliminarOferta.eliminarDiasOferta(Integer.parseInt(boton.getName()));
            	eliminarOferta.eliminarOferta(Integer.parseInt(boton.getName()));
            	JOptionPane.showMessageDialog(null,"Oferta eliminada");
                VentanaPrincipalDAO ventanaPrincipal = new VentanaPrincipalDAO();
                this.empleador=ventanaPrincipal.getEmpleador(idCuenta);
                ArrayList<Oferta> lista = empleador.getListaOfertas();
                this.mostrarOfertasEmpleador(lista);
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent evento) {
        
    }
    @Override
    public void mouseReleased(MouseEvent evento) {
        
    }
    @Override
    public void mouseEntered(MouseEvent evento) {
        
    }
    @Override
    public void mouseExited(MouseEvent evento) {
        
    }
}
