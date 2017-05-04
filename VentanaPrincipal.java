package InterfazGrafica;

import LogicaNegocio.DetallesOfertaDAO;
import LogicaNegocio.Empleador;
import LogicaNegocio.Oferta;
import LogicaNegocio.Solicitante;
import LogicaNegocio.VentanaPrincipalDAO;
import LogicaNegocio.EliminarOfertaDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame implements MouseListener, KeyListener, WindowListener{
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
    private JPanel panelBusqueda;
    
    private Solicitante solicitante;
    private Empleador empleador;
    private ArrayList<Oferta> listaOfertas;
    
    private int tipo;
    private int idCuenta;
    private int bagY = 0;
    
    public VentanaPrincipal(int idCuenta){
        this.idCuenta=idCuenta;
        setTitle("Bolsa de trabajo UV");
        setSize(800,500);
        setMinimumSize(new Dimension(800,500));
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/RecursosGraficos/oferta.png")).getImage());
        setVisible(true);
        setResizable(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
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
            panelBusqueda.setVisible(false);
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
        this.panelBusqueda = new JPanel(new GridLayout(1, 2));
        panelBusqueda.add(txtBusqueda = new JTextField());
        txtBusqueda.addKeyListener(this);
        panelBusqueda.add(btnBuscarOferta = new JButton("Buscar oferta"));
        panelDerecho.add(panelBusqueda);
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
            tituloOfertas = "Vacantes: Ofertas: ";
        }
        panelCentral.add(lblOfertas = new JLabel(tituloOfertas), BorderLayout.PAGE_START);
        panelCentral.add(scrollOfertas = new JScrollPane(panelOfertas = new JPanel()), BorderLayout.CENTER);
        panelOfertas.setBackground(Color.WHITE);
        GridBagLayout bagOfertas = new GridBagLayout();
        panelOfertas.setLayout(bagOfertas);
        if (tipo == 1){
            JPanel panelRespuestas = new JPanel(new FlowLayout());
            JButton boton = new JButton("Respuestas");
            boton.addMouseListener(this);
            panelRespuestas.add(boton);
            contenedor.add(panelRespuestas, BorderLayout.PAGE_END);
        }
        this.addWindowListener(this);
    }
    public void mostrarOfertas(ArrayList<Oferta> listaOfertas){
        if (listaOfertas.size()==0){
            JOptionPane.showMessageDialog(null, "No se encuentran ofertas");
        }else{
            panelOfertas.removeAll();
        }
        for (int c = 0; c<listaOfertas.size(); c++){
            Oferta oferta = listaOfertas.get(c);
            if (oferta.getNumeroVacantes()>0){
                JPanel panelOferta = new JPanel();
                panelOferta.setBackground(Color.LIGHT_GRAY);
                panelOferta.setLayout(new BorderLayout());
                JLabel vacante = new JLabel("Vacante: "+oferta.getVacante());
                JLabel empresa = new JLabel("Empresa: "+oferta.getEmpresa());
                JButton botonVerMas = new JButton("Ver más");
                botonVerMas.setName(String.valueOf(oferta.getIdOferta()));
                botonVerMas.addMouseListener(this);
                JPanel panelBoton = new JPanel();
                panelBoton.setBackground(Color.LIGHT_GRAY);
                panelBoton.setLayout(new FlowLayout());
                panelBoton.add(botonVerMas);
                panelOferta.add(panelBoton, BorderLayout.LINE_END);
                JPanel panelDatos = new JPanel();
                panelDatos.setBackground(Color.LIGHT_GRAY);
                setBackground(Color.red);
                FlowLayout flowDatos = new FlowLayout();
                flowDatos.setHgap(20);
                flowDatos.setVgap(10);
                panelDatos.setLayout(flowDatos);
                panelDatos.add(empresa);
                panelDatos.add(vacante);
                panelOferta.add(panelDatos, BorderLayout.LINE_START);
                panelOferta.setBorder(BorderFactory.createLineBorder(Color.gray));
                GridBagConstraints constantes = new GridBagConstraints();
                constantes.insets= new Insets(5, 5, 5, 5);
                constantes.anchor= GridBagConstraints.NORTHEAST;
                constantes.gridx = 0;
                constantes.gridy = bagY++;
                constantes.gridheight = 1;
                constantes.weightx=2;
                constantes.fill = GridBagConstraints.HORIZONTAL;
                panelOfertas.add(panelOferta, constantes);
            }
        }
        panelOfertas.setVisible(false);
        panelOfertas.setVisible(true);
    }
    public void mostrarOfertasEmpleador(ArrayList<Oferta> listaOfertas){
        panelOfertas.removeAll();
        for (int c = 0; c<listaOfertas.size(); c++){
            Oferta oferta = listaOfertas.get(c);
            JPanel panelOferta = new JPanel();
            panelOferta.setBackground(Color.LIGHT_GRAY);
            panelOferta.setLayout(new BorderLayout());
            JLabel vacante = new JLabel(oferta.getVacante());
            JLabel vacantes = new JLabel(String.valueOf(oferta.getNumeroVacantes()));
            JButton botonVerMas = new JButton("Ver más");
            botonVerMas.setName(String.valueOf(oferta.getIdOferta()));
            botonVerMas.addMouseListener(this);
            JButton botonEditar = new JButton("Editar");
            botonEditar.setName(String.valueOf(oferta.getIdOferta()));
            botonEditar.addMouseListener(this);
            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.setName(String.valueOf(oferta.getIdOferta()));
            botonEliminar.addMouseListener(this);
            JPanel panelBoton = new JPanel();
            panelBoton.setBackground(Color.LIGHT_GRAY);
            panelBoton.setLayout(new FlowLayout(FlowLayout.RIGHT));
            panelBoton.add(botonVerMas);
            panelBoton.add(botonEditar);
            panelBoton.add(botonEliminar);
            JPanel panelDatos = new JPanel();
            panelDatos.setBackground(Color.LIGHT_GRAY);
            FlowLayout flowDatos = new FlowLayout();
            flowDatos.setHgap(20);
            flowDatos.setVgap(10);
            panelDatos.setLayout(flowDatos);
            panelDatos.add(vacantes);
            panelDatos.add(vacante);
            panelOferta.add(panelDatos, BorderLayout.LINE_START);
            panelOferta.add(panelBoton, BorderLayout.LINE_END);
            panelOferta.setBorder(BorderFactory.createLineBorder(Color.gray));
            GridBagConstraints constantes = new GridBagConstraints();
            constantes.insets= new Insets(5, 5, 5, 5);
            constantes.anchor= GridBagConstraints.NORTHEAST;
            constantes.gridx = 0;
            constantes.gridy = bagY++;
            constantes.gridheight = 1;
            constantes.weightx=2;
            constantes.fill = GridBagConstraints.HORIZONTAL;
            panelOfertas.add(panelOferta, constantes);
        }
        panelOfertas.setVisible(false);
        panelOfertas.setVisible(true);
    }
    public void buscarOfertas(){
        VentanaPrincipalDAO ventanaPrincipal = new VentanaPrincipalDAO();
        String coincidencia = this.txtBusqueda.getText();
        if (!coincidencia.trim().equals("")){
            mostrarOfertas(ventanaPrincipal.getOfertasPorClave(this.txtBusqueda.getText()));
        }else{
            mostrarOfertas(ventanaPrincipal.getOfertasPorDescripcion(solicitante.getAreaProfesional()));
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent evento) {
        if (evento.getSource().equals(this.btnBuscarOferta)){
            buscarOfertas();
        }
        if(evento.getSource().equals(btnCerrarSesion)){
            new VentanaInicioSesion();
            dispose();
        }
        if(evento.getSource().equals(btnNuevaOferta)){
            new VentanaNuevaOferta(empleador.getIdEmpleador(), empleador.getNombreEmpresa(), this.idCuenta, null);
            dispose();
        }
        if (evento.getSource() instanceof JButton){
            JButton boton = (JButton) evento.getSource();
            if (boton.getText().equals("Ver más")){
                VentanaDetallesOferta ventana = new VentanaDetallesOferta(Integer.parseInt(boton.getName()), tipo);
                if (tipo==0){
                    ventana.setIdSolicitante(solicitante.getIdSolicitante());
                }
            }else if(boton.getText().equals("Eliminar")){
                EliminarOfertaDAO eliminarOferta = new EliminarOfertaDAO();
                eliminarOferta.eliminarDiasOferta(Integer.parseInt(boton.getName()));
                eliminarOferta.eliminarOferta(Integer.parseInt(boton.getName()));
                JOptionPane.showMessageDialog(null,"Oferta eliminada");
                VentanaPrincipalDAO ventanaPrincipal = new VentanaPrincipalDAO();
                this.empleador=ventanaPrincipal.getEmpleador(idCuenta);
                ArrayList<Oferta> lista = empleador.getListaOfertas();
                this.mostrarOfertasEmpleador(lista);
            }else if(boton.getText().equals("Editar")){
                DetallesOfertaDAO detallesOferta = new DetallesOfertaDAO();
                Oferta oferta = detallesOferta.getOferta(Integer.parseInt(boton.getName()));
                new VentanaNuevaOferta(empleador.getIdEmpleador(), empleador.getNombreEmpresa(), this.idCuenta, oferta);
                dispose();
            }else if(boton.getText().equals("Respuestas")){
                
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

    @Override
    public void keyTyped(KeyEvent evento) {
        if (evento.getSource().equals(this.txtBusqueda)){
            if (evento.getKeyChar()==10){
                buscarOfertas();
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent evento) {
        
    }
    @Override
    public void keyReleased(KeyEvent evento) {
        
    }

    @Override
    public void windowOpened(WindowEvent evento) {
        
    }
    @Override
    public void windowClosing(WindowEvent evento) {
        new VentanaInicioSesion();
    }
    @Override
    public void windowClosed(WindowEvent evento) {
        
    }
    @Override
    public void windowIconified(WindowEvent evento) {
        
    }
    @Override
    public void windowDeiconified(WindowEvent evento) {
        
    }
    @Override
    public void windowActivated(WindowEvent evento) {
        
    }
    @Override
    public void windowDeactivated(WindowEvent evento){
        
    }
}
