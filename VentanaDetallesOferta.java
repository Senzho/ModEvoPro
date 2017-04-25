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
import java.util.ArrayList;
import javax.swing.BoxLayout;

public class VentanaDetallesOferta extends JFrame {
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
    
    public VentanaDetallesOferta(int idOferta){
        this.idOferta=idOferta;
        DetallesOfertaDAO detallesOferta = new DetallesOfertaDAO();
        oferta = detallesOferta.getOferta(idOferta);
        inicializarComponentes();
        mostrarDias();
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
        panelTxtDescripcion = new JPanel();
        panelTxtDescripcion.setLayout(flowPanelDescripcion);
        txtDescripcion = new JTextArea(oferta.getDescripcion());
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(null);
        panelTxtDescripcion.add(txtDescripcion);
        panelDescripcion.add(panelTxtDescripcion, BorderLayout.CENTER);
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
        FlowLayout flowLblHorario = new FlowLayout();
        panelLblHorario.setLayout(new BorderLayout());
        panelHorario.add(panelLblHorario, BorderLayout.NORTH);
        String numeroOfertas = String.valueOf(oferta.getNumeroVacantes());
        panelLblHorario.add(lblNumeroVacantes = new JLabel("Numero vacantes: "+numeroOfertas+". "), BorderLayout.NORTH);
         panelLblHorario.add(new JLabel("Horario:"), BorderLayout.SOUTH);
        scrollDias = new JScrollPane(panelDias = new JPanel());
        BoxLayout boxDias = new BoxLayout(panelDias, BoxLayout.Y_AXIS);
        panelDias.setLayout(boxDias);
        panelHorario.add(scrollDias, BorderLayout.CENTER);
        panelCentral.add(panelHorario);
    }
    public void mostrarDias(){
        ArrayList<Dia> dias = oferta.getListaDias();
        for(int c=0;c< dias.size();c++){
            Dia dia = dias.get(c);
            JPanel panelDia = new JPanel();
            panelDia.setLayout(new BorderLayout());
            JLabel nombre = new JLabel(dia.getNombre());
            JPanel panelHoras = new JPanel(new FlowLayout());
            JLabel inicio = new JLabel(dia.getHoraInicio());
            JLabel fin = new JLabel(dia.getHoraFin());
            panelDia.add(nombre, BorderLayout.LINE_START);
            panelHoras.add(inicio);
            panelHoras.add(fin);
            panelDia.add(panelHoras, BorderLayout.LINE_END);
            panelDias.add(panelDia);
            panelDias.setVisible(false);
            panelDias.setVisible(true);
        }
    }
}