package InterfazGrafica;

import LogicaNegocio.Empleador;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class VentanaInformacionEmpresa extends JFrame implements MouseListener{
    private Empleador empleador;
    private JPanel panelVentana;
    private JPanel panelTelefono;
    private JPanel panelTelefonoNumero;
    private JPanel panelBoton;
    private JPanel panelInformacion;
    private JPanel panelDireccion;
    private JPanel panelDescripcion;
    private JLabel lblNombreEmpresa;
    private JLabel lblTelefono;
    private JScrollPane scrlDireccion;
    private JScrollPane srclDescripcion;
    private JTextArea txtDireccion;
    private JTextArea txtDescripcion;
    private JButton btnSalir;
    
    public VentanaInformacionEmpresa(Empleador empleador){
        this.empleador = empleador;
        setSize(300,400);
        setTitle("Información: "+this.empleador.getNombreEmpresa());
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/RecursosGraficos/oferta.png")).getImage());
        setMinimumSize(new Dimension(300,400));
        setVisible(true);
        inicializarComponentes();
    }
    public void inicializarComponentes(){
        panelVentana = new JPanel();
        add(panelVentana);
        panelVentana.setLayout(new BorderLayout());
        panelVentana.add(lblNombreEmpresa = new JLabel(empleador.getNombreEmpresa()), BorderLayout.NORTH);
        lblNombreEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
        panelVentana.add(panelTelefono = new JPanel(), BorderLayout.SOUTH);
        panelTelefono.setLayout(new BorderLayout());
        panelTelefono.add(panelBoton = new JPanel(), BorderLayout.SOUTH);  
        panelTelefono.add(panelTelefonoNumero = new JPanel(), BorderLayout.NORTH);
        panelTelefonoNumero.setLayout(new FlowLayout());
        panelTelefonoNumero.add(new JLabel("Telefono: "));
        panelTelefonoNumero.add(lblTelefono = new JLabel(empleador.getNumero()));
        panelBoton.setLayout(new FlowLayout());
        panelBoton.add(btnSalir = new JButton("Salir"));
        btnSalir.addMouseListener(this);
        panelVentana.add(panelInformacion = new JPanel(), BorderLayout.CENTER);
        panelInformacion.setLayout(new GridLayout(2,1));
        panelInformacion.add(panelDireccion = new JPanel());
        panelDireccion.setLayout(new BorderLayout());
        FlowLayout flowDireccion = new FlowLayout(FlowLayout.LEFT);
        flowDireccion.setHgap(20);
        flowDireccion.setVgap(0);
        JPanel panelLabelDireccion = new JPanel(flowDireccion);
        panelLabelDireccion.add(new JLabel("Dirección: "));
        panelDireccion.add(panelLabelDireccion, BorderLayout.NORTH);
        scrlDireccion = new JScrollPane(txtDireccion = new JTextArea(empleador.getDireccion()));
        scrlDireccion.setBorder(null);
        txtDireccion.setWrapStyleWord(true);
        txtDireccion.setLineWrap(true);
        txtDireccion.setEditable(false);
        txtDireccion.setBackground(null);
        txtDireccion.setMargin(new Insets(10, 20, 10, 20));
        panelDireccion.add(scrlDireccion, BorderLayout.CENTER);
        panelInformacion.add(panelDescripcion = new JPanel());
        panelDescripcion.setLayout(new BorderLayout());
        FlowLayout flowDescripcion = new FlowLayout(FlowLayout.LEFT);
        flowDescripcion.setHgap(20);
        flowDescripcion.setVgap(0);
        JPanel panelLabelDescripcion = new JPanel(flowDescripcion);
        panelLabelDescripcion.add(new JLabel("Descripción: "));
        panelDescripcion.add(panelLabelDescripcion, BorderLayout.NORTH);
        panelDescripcion.add(this.srclDescripcion = new JScrollPane(txtDescripcion = new JTextArea(empleador.getDescripcion())), BorderLayout.CENTER);
        this.srclDescripcion.setBorder(null);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(null);
        txtDescripcion.setMargin(new Insets(10, 20, 10, 20));
    }

    /**
     * Implementación de eventos: 
     */
    
    @Override
    public void mouseClicked(MouseEvent evento) {
        if (evento.getSource().equals(this.btnSalir)){
            dispose();
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