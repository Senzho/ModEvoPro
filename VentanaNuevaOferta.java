package InterfazGrafica;

import LogicaNegocio.Dia;
import LogicaNegocio.NuevaOfertaDAO;
import LogicaNegocio.Oferta;
import LogicaNegocio.CodigoErrorOferta;
import LogicaNegocio.EditarOfertaDAO;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class VentanaNuevaOferta extends JFrame implements MouseListener, KeyListener, ItemListener{
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
    private Oferta ofertaGeneral;
    
    private int idEmpleador;
    private String nombreEmpresa;
    private int idCuenta;
    
    public VentanaNuevaOferta(int idEmpleador, String nombreEmpresa, int idCuenta, Oferta oferta){
        this.idCuenta=idCuenta;
        setVisible(true);
        setSize(500,500);
        setTitle("Nueva oferta");
        setLocationRelativeTo(null);
        this.idEmpleador=idEmpleador;
        this.nombreEmpresa=nombreEmpresa;
        this.ofertaGeneral = oferta;
        inicializarComponentes();
        cargarCombos();
        establecerPropiedades();
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
        JLabel lblVacante = new JLabel("Puesto: ");
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
        this.txtAreaDescripcion.addKeyListener(this);
        this.btnCancelar.addMouseListener(this);
        this.btnCrear.addMouseListener(this);
        this.txtVacantes.setText("0");
        this.txtVacantes.addKeyListener(this);
        this.txtVacante.addKeyListener(this);
        this.txtSalario.addKeyListener(this);
        if(ofertaGeneral != null){
            this.btnCrear.setText("Guardar");
            mostrarDatos();
        }
    }
    public void mostrarDatos(){
        this.txtAreaDescripcion.setText(ofertaGeneral.getDescripcion());
        this.txtSalario.setText(ofertaGeneral.getSalario());
        this.txtVacante.setText(ofertaGeneral.getVacante());
        this.txtVacantes.setText(Integer.toString(ofertaGeneral.getNumeroVacantes()));
        for(int i = 0; i<ofertaGeneral.getListaDias().size(); i++){
            Dia dia = ofertaGeneral.getListaDias().get(i);
            String nombre = dia.getNombre();
            nombre = nombre;
            if(dia.getNombre().equals("Lunes")){
                this.checkLunes.setSelected(true);
                this.comboInicioLunes.setSelectedItem(dia.getHoraInicio());
                this.comboFinLunes.setSelectedItem(dia.getHoraFin());
            }
            if(dia.getNombre().equals("Martes")){
                this.checkMartes.setSelected(true);
                this.comboInicioMartes.setSelectedItem(dia.getHoraInicio());
                this.comboFinMartes.setSelectedItem(dia.getHoraFin());
            }
            if(dia.getNombre().equals("Miercoles")){
                this.checkMiercoles.setSelected(true);
                this.comboInicioMiercoles.setSelectedItem(dia.getHoraInicio());
                this.comboFinMiercoles.setSelectedItem(dia.getHoraFin());
            }
            if(dia.getNombre().equals("Jueves")){
                this.checkJueves.setSelected(true);
                this.comboInicioJueves.setSelectedItem(dia.getHoraInicio());
                this.comboFinJueves.setSelectedItem(dia.getHoraFin());
            }
            if(dia.getNombre().equals("Viernes")){
                this.checkViernes.setSelected(true);
                this.comboInicioViernes.setSelectedItem(dia.getHoraInicio());
                this.comboFinViernes.setSelectedItem(dia.getHoraFin());
            }
            if(dia.getNombre().equals("Sabado")){
                this.checkSabado.setSelected(true);
                this.comboInicioSabado.setSelectedItem(dia.getHoraInicio());
                this.comboFinSabado.setSelectedItem(dia.getHoraFin());
            }
            if(dia.getNombre().equals("Domingo")){
                this.checkDomingo.setSelected(true);
                this.comboInicioDomingo.setSelectedItem(dia.getHoraInicio());
                this.comboFinDomingo.setSelectedItem(dia.getHoraFin());
            }
        }
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
        }else if(oferta.getDescripcion().length()>500){
            codigo = CodigoErrorOferta.descripcionLarga;
        }else if(oferta.getSalario().equals("")){
            codigo = CodigoErrorOferta.salarioVacio;
        }else if(String.valueOf(oferta.getNumeroVacantes()).equals("0")){
            codigo = CodigoErrorOferta.vacantesVacia;
        }else if(!validarHorario()){
            codigo = CodigoErrorOferta.horarioVacio;
        }else{
            if (validarHorario()){
                ArrayList<Dia> lista = oferta.getListaDias();
                for(int c=0; c<lista.size(); c++){
                    Dia dia = lista.get(c);
                    int inicio = Integer.parseInt(dia.getHoraInicio().substring(0, 2));
                    int fin = Integer.parseInt(dia.getHoraFin().substring(0, 2));
                    if (!horasValidas(inicio, fin)){
                        codigo= CodigoErrorOferta.horasInvalidas;
                        c=lista.size();
                    }
                }
            }
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
    public ArrayList<Dia> capturarDias(){
        ArrayList<Dia> lista = new ArrayList<Dia>();
        if (this.checkLunes.isSelected()){
            lista.add(new Dia("Lunes", this.comboInicioLunes.getSelectedItem().toString(), this.comboFinLunes.getSelectedItem().toString()));
        }
        if (this.checkMartes.isSelected()){
            lista.add(new Dia("Martes", this.comboInicioMartes.getSelectedItem().toString(), this.comboFinMartes.getSelectedItem().toString()));
        }
        if (this.checkMiercoles.isSelected()){
            lista.add(new Dia("Miercoles", this.comboInicioMiercoles.getSelectedItem().toString(), this.comboFinMiercoles.getSelectedItem().toString()));
        }
        if (this.checkJueves.isSelected()){
            lista.add(new Dia("Jueves", this.comboInicioJueves.getSelectedItem().toString(), this.comboFinJueves.getSelectedItem().toString()));
        }
        if (this.checkViernes.isSelected()){
            lista.add(new Dia("Viernes", this.comboInicioViernes.getSelectedItem().toString(), this.comboFinViernes.getSelectedItem().toString()));
        }
        if (this.checkSabado.isSelected()){
            lista.add(new Dia("Sabado", this.comboInicioSabado.getSelectedItem().toString(), this.comboFinSabado.getSelectedItem().toString()));
        }
        if (this.checkDomingo.isSelected()){
            lista.add(new Dia("Domingo", this.comboInicioDomingo.getSelectedItem().toString(), this.comboFinDomingo.getSelectedItem().toString()));
        }
        return lista;
    }
    public boolean horasValidas(int inicio, int fin){
        boolean validas = false;
        if (inicio<fin && fin>inicio){
            validas = true;
        }
        return validas;
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
            Oferta oferta = new Oferta(idOferta, vacante, empresa, numeroVacantes, salario, descripcion, this.capturarDias());
            CodigoErrorOferta codigo = this.validarOferta(oferta);
            switch(codigo){
                case vacanteVacia:
                    JOptionPane.showMessageDialog(null, "Ingresa el puesto.");
                break;
                case descripcionVacia:
                    JOptionPane.showMessageDialog(null, "Ingresa la descripción.");
                break;
                case descripcionLarga:
                    JOptionPane.showMessageDialog(null, "La descripción es demasiado larga (más de 500 caractéres).");
                break;
                case salarioVacio:
                    JOptionPane.showMessageDialog(null, "Ingresa el salario.");
                break;
                case vacantesVacia:
                    JOptionPane.showMessageDialog(null, "Ingresa el numero de vacantes.");
                break;
                case horarioVacio:
                    JOptionPane.showMessageDialog(null, "Selecciona el horario.");
                break;
                case horasInvalidas:
                    JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser mayor a la hora de fin y viceversa.");
                break;
                case exito:
                    if (oferta != null){
                         EditarOfertaDAO editarOferta = new EditarOfertaDAO();
                         editarOferta.editarOferta(oferta, idEmpleador, ofertaGeneral.getIdOferta());
                         editarOferta.editarDias(oferta.getListaDias(), ofertaGeneral.getIdOferta());
                         JOptionPane.showMessageDialog(null, "Oferta guardada!");
                    }else{
                        nuevaOferta.guardarNuevaOferta(oferta, idEmpleador);
                        nuevaOferta.guardarDiasOferta(oferta.getListaDias(), idOferta);
                        JOptionPane.showMessageDialog(null, "Oferta creada!");
                    }
                    new VentanaPrincipal(idCuenta);
                    dispose();
                break;
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
        if (evento.getSource().equals(this.txtAreaDescripcion)){
            int largoTexto=this.txtAreaDescripcion.getText().length();
            if (largoTexto>499){
                evento.consume();
                JOptionPane.showMessageDialog(null, "No puedes escribir más de 500 caractéres");
            }
        }
        if (evento.getSource().equals(this.txtVacante)){
            int largoTexto=this.txtVacante.getText().length();
            if (largoTexto>49){
                evento.consume();
            }
        }
        if (evento.getSource().equals(this.txtSalario)){
            int largoTexto=this.txtSalario.getText().length();
            if (largoTexto>29){
                evento.consume();
            }
        }
        if (evento.getSource().equals(this.txtVacantes)){
            char caracter = evento.getKeyChar();
            if (!Character.isDigit(caracter) && caracter!=8){
                evento.consume();
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent evento) {
        if (evento.getSource().equals(this.txtVacantes)){
            char caracter = evento.getKeyChar();
            if ((caracter==8 || caracter==127) && this.txtVacantes.getText().length()==1){
                evento.consume();
                this.txtVacantes.setText("0");
            }else if(this.txtVacantes.getText().length()==1 && this.txtVacantes.getText().equals("0") && Character.isDigit(caracter)){
                evento.consume();
                this.txtVacantes.setText("");
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent evento) {
        
    }

    @Override
    public void itemStateChanged(ItemEvent evento) {
        
    }
}
