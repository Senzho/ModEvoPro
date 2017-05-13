package InterfazGrafica;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import LogicaNegocio.DetallesOfertaDAO;
import LogicaNegocio.Dia;
import LogicaNegocio.Oferta;
import LogicaNegocio.OfertaRespondidaDAO;
import LogicaNegocio.VentanaPrincipalDAO;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class VentanaDetallesOferta extends JFrame implements MouseListener, WindowListener{
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JPanel panelPuesto;
    private JPanel panelDescripcion;
    private JPanel panelHorario;
    private JPanel panelBotones;
    private JPanel panelPrincipal;
    private JPanel panelCentral;
    private JLabel lblPuesto;
    private JPanel panelSalario;
    private JPanel panelLblHorario;
    private JPanel panelLblDescripcion;
    private JPanel panelTxtDescripcion;
    private JLabel lblSalario;
    private JLabel lblNumeroVacantes;
    private JPanel panelDias;
    private JTextArea txtDescripcion;
    private JScrollPane scrollDias;  
    private int idOferta;
    private Oferta oferta;
    private int tipoUsuario;
    private int idSolicitante;
    
    private VentanaPrincipal ventana;
    
    public VentanaDetallesOferta(int idOferta, int tipoUsuario, VentanaPrincipal ventana){
        this.ventana = ventana;
        this.idOferta=idOferta;
        this.tipoUsuario = tipoUsuario;
        DetallesOfertaDAO detallesOferta = new DetallesOfertaDAO();
        oferta = detallesOferta.getOferta(idOferta);
        inicializarComponentes();
        establecerPropiedades();
        mostrarDias();
        setIconImage(new ImageIcon(getClass().getResource("/RecursosGraficos/oferta.png")).getImage());
        setVisible(true);
        setSize(300,400);
        setTitle("Detalles oferta");
        setLocationRelativeTo(null);
    }
    public void inicializarComponentes(){
        add(panelPrincipal = new JPanel());
        panelPrincipal.setLayout(new BorderLayout());
        panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(btnAceptar = new JButton("Lo quiero!"));
        panelBotones.add(btnCancelar = new JButton("Cancelar"));
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        FlowLayout flowDerecho = new FlowLayout(FlowLayout.LEFT);
        flowDerecho.setHgap(20);
        flowDerecho.setVgap(10);
        panelPuesto = new JPanel();
        panelPuesto.setLayout(flowDerecho);
        panelPuesto.add(new JLabel("Vacante:"));
        panelPuesto.add(lblPuesto = new JLabel(oferta.getVacante()));
        panelPrincipal.add(panelPuesto, BorderLayout.NORTH);
        panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(2,1));
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelDescripcion = new JPanel();
        panelDescripcion.setLayout(new BorderLayout());
        panelLblDescripcion = new JPanel();
        FlowLayout flowPanelDescripcion = new FlowLayout(FlowLayout.LEFT);
        flowPanelDescripcion.setHgap(20);
        flowPanelDescripcion.setVgap(10);
        panelLblDescripcion.setLayout(flowPanelDescripcion);
        panelLblDescripcion.add(new JLabel("Descripción:"));
        panelDescripcion.add(panelLblDescripcion, BorderLayout.NORTH);
        txtDescripcion = new JTextArea(oferta.getDescripcion());
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(null);
        txtDescripcion.setMargin(new Insets(10, 20, 10, 20));
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setBorder(null);
        panelDescripcion.add(scrollDescripcion, BorderLayout.CENTER);
        panelSalario = new JPanel();
        FlowLayout flowPanelSalario = new FlowLayout(FlowLayout.LEFT);
        flowPanelSalario.setHgap(20);
        flowPanelSalario.setVgap(10);
        panelSalario.setLayout(flowPanelSalario);
        panelSalario.add(new JLabel("Salario:"));
        panelSalario.add(lblSalario = new JLabel(oferta.getSalario()));
        panelDescripcion.add(panelSalario, BorderLayout.SOUTH);
        panelCentral.add(panelDescripcion);
        panelHorario = new JPanel();
        panelHorario.setLayout(new BorderLayout());
        panelLblHorario = new JPanel();
        panelLblHorario.setLayout(new BorderLayout());
        panelHorario.add(panelLblHorario, BorderLayout.NORTH);
        String numeroOfertas = String.valueOf(oferta.getNumeroVacantes());
        FlowLayout flowLv = new FlowLayout(FlowLayout.LEFT);
        flowLv.setHgap(20);
        JPanel panelLv = new JPanel(flowLv);
        panelLv.add(lblNumeroVacantes = new JLabel("Numero vacantes: "+numeroOfertas+". "));
        panelLblHorario.add(panelLv, BorderLayout.NORTH);
        FlowLayout flowLh = new FlowLayout(FlowLayout.LEFT);
        flowLh.setHgap(20);
        JPanel panelLh = new JPanel(flowLh);
        panelLh.add(new JLabel("Horario:"));
        panelLblHorario.add(panelLh, BorderLayout.SOUTH);
        scrollDias = new JScrollPane(panelDias = new JPanel());
        BoxLayout boxDias = new BoxLayout(panelDias, BoxLayout.Y_AXIS);
        panelDias.setLayout(boxDias);
        JPanel panelScrollDias = new JPanel(new BorderLayout());
        panelScrollDias.setBackground(Color.WHITE);
        panelScrollDias.add(scrollDias, BorderLayout.CENTER);
        panelHorario.add(panelScrollDias, BorderLayout.CENTER);
        panelCentral.add(panelHorario);
    }
    public void establecerPropiedades(){
        this.addWindowListener(this);
        this.btnCancelar.addMouseListener(this);
        this.btnAceptar.addMouseListener(this);
        if (tipoUsuario > 0){
            this.btnAceptar.setText("Ok");
            this.btnCancelar.setVisible(false);
        }
    }
    public void mostrarDias(){
        ArrayList<Dia> dias = oferta.getListaDias();
        for(int c=0;c< dias.size();c++){
            Dia dia = dias.get(c);
            JPanel panelDia = new JPanel();
            panelDia.setBackground(Color.LIGHT_GRAY);
            panelDia.setLayout(new BorderLayout());
            JLabel nombre = new JLabel("  "+dia.getNombre());
            JPanel panelHoras = new JPanel(new BorderLayout());
            panelHoras.setBackground(Color.LIGHT_GRAY);
            JLabel inicio = new JLabel(dia.getHoraInicio());
            JLabel fin = new JLabel(dia.getHoraFin()+"  ");
            panelDia.add(nombre, BorderLayout.LINE_START);
            panelHoras.add(inicio, BorderLayout.LINE_START);
            panelHoras.add(new JLabel(" - "));
            panelHoras.add(fin, BorderLayout.LINE_END);
            panelDia.add(panelHoras, BorderLayout.LINE_END);
            panelDia.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));
            panelDias.add(panelDia);
            panelDias.setVisible(false);
            panelDias.setVisible(true);
        }
    }
    public void setIdSolicitante(int idSolicitante){
        this.idSolicitante = idSolicitante;
    }
    public void responderOferta(){
        OfertaRespondidaDAO ofertaRespondida = new OfertaRespondidaDAO();
        int idEmpleador = ofertaRespondida.getIdEmpleador(idOferta);
        if (ofertaRespondida.guardarOfertaRespondida(idEmpleador, idSolicitante, idOferta)){
            ofertaRespondida.disminuirVacantes(idOferta,oferta.getNumeroVacantes());
            JOptionPane.showMessageDialog(null, "Listo!");
            VentanaPrincipalDAO ventanaPrincipal = new VentanaPrincipalDAO();
            this.ventana.mostrarOfertas(ventanaPrincipal.getOfertasPorClave(this.ventana.getUltimaBusqueda()));
            this.ventana.setVentanaDetallesInstanciada(false);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Los sentimos, no se pudo realizar la operación, intente de nuevo más tarde");
        }
    }
    
    /**
     * Implementación de eventos:
     */

    @Override
    public void mouseClicked(MouseEvent evento) {
        if (evento.getSource().equals(this.btnAceptar)){
            if (this.btnAceptar.getText().equals("Lo quiero!")){
                this.responderOferta();
            }else{
                this.ventana.setVentanaDetallesInstanciada(false);
                dispose();
            }
        }else if(evento.getSource().equals(this.btnCancelar)){
            this.ventana.setVentanaDetallesInstanciada(false);
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

    @Override
    public void windowOpened(WindowEvent evento) {
        
    }
    @Override
    public void windowClosing(WindowEvent evento) {
        this.ventana.setVentanaDetallesInstanciada(false);
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
    public void windowDeactivated(WindowEvent evento) {
        
    }
}