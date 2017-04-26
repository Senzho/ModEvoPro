package InterfazGrafica;

import LogicaNegocio.Dia;
import LogicaNegocio.NuevaOfertaDAO;
import LogicaNegocio.Oferta;
import LogicaNegocio.CodigoErrorOferta;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaNuevaOferta extends JFrame implements MouseListener{
    private JPanel panelPrincipal;
    private JTextField txtVacante;
    private JScrollPane scrollDescripcion;
    private JTextArea txtAreaDescripcion;
    private JTextField txtSalario;
    private JTextField txtVacantes;
    private JButton btnCrear;
    private JButton btnCancelar;
    private JCheckBox checkDomingo;
    private JCheckBox checkLunes;
    private JCheckBox checkMartes;
    private JCheckBox checkMiercoles;
    private JCheckBox checkJueves;
    private JCheckBox checkViernes;
    private JCheckBox checkSabado;
    private JComboBox comboInicioLunes;
    private JComboBox comboInicioMartes;
    private JComboBox comboInicioMiercoles;
    private JComboBox comboInicioJueves;
    private JComboBox comboInicioViernes;
    private JComboBox comboInicioSabado;
    private JComboBox comboInicioDomingo;
    private JComboBox comboFinLunes;
    private JComboBox comboFinMartes;
    private JComboBox comboFinMiercoles;
    private JComboBox comboFinJueves;
    private JComboBox comboFinViernes;
    private JComboBox comboFinSabado;
    private JComboBox comboFinDomingo;
    
    private int idEmpleador;
    private String nombreEmpresa;
    
    public VentanaNuevaOferta(int idEmpleador, String nombreEmpresa){
        setVisible(true);
        setSize(500,500);
        setTitle("Nueva oferta");
        setLocationRelativeTo(null);
        this.idEmpleador=idEmpleador;
        this.nombreEmpresa=nombreEmpresa;
        inicializarComponentes();
        establecerPropiedades();
        cargarCombos();
    }
    
    public void inicializarComponentes(){
        add(panelPrincipal = new JPanel());
        GridBagLayout gridBagPrincipal = new GridBagLayout();
        panelPrincipal.setLayout(gridBagPrincipal);
        GridBagConstraints constantes = new GridBagConstraints();
        constantes.insets= new Insets(5, 5, 5, 5);
        constantes.anchor= GridBagConstraints.NORTHWEST;
        constantes.gridx = 0;
        constantes.gridy = 0;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        constantes.fill = GridBagConstraints.NONE;
        JLabel lblVacante = new JLabel("Vacante: ");
        panelPrincipal.add(lblVacante, constantes);
        constantes.gridx = 1;
        constantes.gridy = 0;
        constantes.gridwidth = 4;
        constantes.gridheight = 1;
        constantes.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(txtVacante= new JTextField(), constantes);
        JLabel lblDescripcion = new JLabel("Descripción: ");
        constantes.weighty=1;
        constantes.gridx=0;
        constantes.gridy=1;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        constantes.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblDescripcion, constantes);
        constantes.weighty=0;
        constantes.gridx=1;
        constantes.gridy=1;
        constantes.gridwidth = 4;
        constantes.gridheight = 5;
        constantes.fill = GridBagConstraints.BOTH;
        panelPrincipal.add(scrollDescripcion = new JScrollPane(txtAreaDescripcion = new JTextArea()), constantes);
        JLabel salario = new JLabel("Salario: ");
        constantes.gridx=0;
        constantes.gridy=6;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        constantes.fill = GridBagConstraints.NONE;
        panelPrincipal.add(salario, constantes);
        constantes.gridx=1;
        constantes.gridy=6;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        constantes.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(txtSalario = new JTextField(), constantes);
        JLabel lblOrden = new JLabel("Especificar periodo");
        constantes.gridx=1;
        constantes.gridy=7;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        constantes.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblOrden, constantes);
        JLabel lblVacantes = new JLabel("Vacantes: ");
        constantes.gridx=0;
        constantes.gridy=8;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(lblVacantes, constantes);
        constantes.gridx=1;
        constantes.gridy=8;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        constantes.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(txtVacantes= new JTextField(), constantes);
        JLabel lblHorario = new JLabel("Horario");
        constantes.gridx=2;
        constantes.gridy=6;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        constantes.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblHorario, constantes);
        constantes.gridx=2;
        constantes.gridy=7;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(checkLunes = new JCheckBox("Lunes"), constantes);
        constantes.gridx=3;
        constantes.gridy=7;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboInicioLunes = new JComboBox();
        panelPrincipal.add(comboInicioLunes, constantes);
        constantes.gridx=4;
        constantes.gridy=7;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboFinLunes = new JComboBox();
        panelPrincipal.add(comboFinLunes, constantes);
        constantes.gridx=2;
        constantes.gridy=8;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(checkMartes = new JCheckBox("Martes"), constantes);
        constantes.gridx=3;
        constantes.gridy=8;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboInicioMartes = new JComboBox();
        panelPrincipal.add(comboInicioMartes, constantes);
        constantes.gridx=4;
        constantes.gridy=8;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboFinMartes = new JComboBox();
        panelPrincipal.add(comboFinMartes, constantes);
        constantes.gridx=2;
        constantes.gridy=9;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(checkMiercoles = new JCheckBox("Miercoles"), constantes);
        constantes.gridx=3;
        constantes.gridy=9;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboInicioMiercoles = new JComboBox();
        panelPrincipal.add(comboInicioMiercoles, constantes);
        constantes.gridx=4;
        constantes.gridy=9;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboFinMiercoles = new JComboBox();
        panelPrincipal.add(comboFinMiercoles, constantes);
        constantes.gridx=2;
        constantes.gridy=10;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(checkJueves = new JCheckBox("Jueves"), constantes);
        constantes.gridx=3;
        constantes.gridy=10;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboInicioJueves = new JComboBox();
        panelPrincipal.add(comboInicioJueves, constantes);
        constantes.gridx=4;
        constantes.gridy=10;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboFinJueves = new JComboBox();
        panelPrincipal.add(comboFinJueves, constantes);
        constantes.gridx=2;
        constantes.gridy=11;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(checkViernes = new JCheckBox("Viernes"), constantes);
        constantes.gridx=3;
        constantes.gridy=11;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboInicioViernes = new JComboBox();
        panelPrincipal.add(comboInicioViernes, constantes);
        constantes.gridx=4;
        constantes.gridy=11;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboFinViernes = new JComboBox();
        panelPrincipal.add(comboFinViernes, constantes);
        constantes.gridx=2;
        constantes.gridy=12;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(checkSabado = new JCheckBox("Sabado"), constantes);
        constantes.gridx=3;
        constantes.gridy=12;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboInicioSabado = new JComboBox();
        panelPrincipal.add(comboInicioSabado, constantes);
        constantes.gridx=4;
        constantes.gridy=12;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboFinSabado = new JComboBox();
        panelPrincipal.add(comboFinSabado, constantes);
        constantes.gridx=2;
        constantes.gridy=13;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(checkDomingo = new JCheckBox("Sabado"), constantes);
        constantes.gridx=3;
        constantes.gridy=13;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboInicioDomingo = new JComboBox();
        panelPrincipal.add(comboInicioDomingo, constantes);
        constantes.gridx=4;
        constantes.gridy=13;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        comboFinDomingo = new JComboBox();
        panelPrincipal.add(comboFinDomingo, constantes);
        constantes.gridx=3;
        constantes.gridy=15;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(this.btnCrear = new JButton("Crear"), constantes);
        constantes.gridx=4;
        constantes.gridy=15;
        constantes.gridwidth = 1;
        constantes.gridheight = 1;
        panelPrincipal.add(this.btnCancelar = new JButton("Cancelar"), constantes);
    }
    public void establecerPropiedades(){
        this.txtAreaDescripcion.setWrapStyleWord(true);
        this.txtAreaDescripcion.setLineWrap(true);
        this.btnCancelar.addMouseListener(this);
        this.btnCrear.addMouseListener(this);
    }
    public void cargarCombos(){
        cargarHoras(this.comboInicioDomingo);
        cargarHoras(this.comboInicioLunes);
        cargarHoras(this.comboInicioMartes);
        cargarHoras(this.comboInicioMiercoles);
        cargarHoras(this.comboInicioJueves);
        cargarHoras(this.comboInicioViernes);
        cargarHoras(this.comboInicioSabado);
        cargarHoras(this.comboFinDomingo);
        cargarHoras(this.comboFinLunes);
        cargarHoras(this.comboFinMartes);
        cargarHoras(this.comboFinMiercoles);
        cargarHoras(this.comboFinJueves);
        cargarHoras(this.comboFinViernes);
        cargarHoras(this.comboFinSabado);
    }
    public void cargarHoras(JComboBox combo){
        for(int c=0; c<25;c++){
            if (c<10){
                combo.addItem("0"+c+":00");
            }else{
                combo.addItem(c+":00");
            }
        }
    }
    public CodigoErrorOferta validarOferta(Oferta oferta){
        CodigoErrorOferta codigo = CodigoErrorOferta.exito;
        if (oferta.getVacante().equals("")){
            codigo = CodigoErrorOferta.vacanteVacia;
        }else if(oferta.getDescripcion().equals("")){
            codigo = CodigoErrorOferta.descripcionVacia;
        }else if(oferta.getSalario().equals("")){
            codigo = CodigoErrorOferta.salarioVacio;
        }else if(String.valueOf(oferta.getNumeroVacantes()).equals("")){
            codigo = CodigoErrorOferta.vacantesVacia;
        }else if(!validarHorario()){
            codigo = CodigoErrorOferta.horarioVacio;
        }
        return codigo;
    }
    public boolean validarHorario(){
        boolean valido = false;
        if (checkLunes.isSelected() || checkDomingo.isSelected() || checkMartes.isSelected() || checkMiercoles.isSelected() || checkJueves.isSelected() || checkViernes.isSelected() || checkSabado.isSelected()){
            valido=true;
        }
        return valido;
    }

    /**
     * Implementación de eventos:
     */
    
    @Override
    public void mouseClicked(MouseEvent evento) {
        if (evento.getSource().equals(this.btnCancelar)){
            dispose();
        }
        if (evento.getSource().equals(this.btnCrear)){
            NuevaOfertaDAO nuevaOferta = new NuevaOfertaDAO();
            int idOferta = nuevaOferta.getUltimoIdOferta()+1;
            String vacante = this.txtVacante.getText();
            String empresa = this.nombreEmpresa;
            int numeroVacantes = Integer.parseInt(this.txtVacantes.getText());
            String salario = this.txtSalario.getText();
            String descripcion = this.txtAreaDescripcion.getText();
            Oferta oferta = new Oferta(idOferta, vacante, empresa, numeroVacantes, salario, descripcion, new ArrayList<Dia>());
            CodigoErrorOferta codigo = this.validarOferta(oferta);
            if (codigo.equals(CodigoErrorOferta.vacanteVacia)){
                JOptionPane.showMessageDialog(null, "Ingresa la vacante.");
            }else if(codigo.equals(CodigoErrorOferta.descripcionVacia)){
                JOptionPane.showMessageDialog(null, "Ingresa la descripción.");
            }else if(codigo.equals(CodigoErrorOferta.salarioVacio)){
                JOptionPane.showMessageDialog(null, "Ingresa el salario.");
            }else if(codigo.equals(CodigoErrorOferta.vacantesVacia)){
                JOptionPane.showMessageDialog(null, "Ingresa el numero de vacantes.");
            }else if(codigo.equals(CodigoErrorOferta.horarioVacio)){
                JOptionPane.showMessageDialog(null, "Selecciona el horario.");
            }else{
                nuevaOferta.guardarNuevaOferta(oferta, idEmpleador);
                /**
                 * Debe guardar los días en este punto.
                 */
                JOptionPane.showMessageDialog(null, "Oferta guardada!");
                dispose();
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
