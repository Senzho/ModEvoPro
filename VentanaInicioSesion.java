package InterfazGrafica;

import LogicaNegocio.CodigoAutenticacion;
import LogicaNegocio.CuentaValidada;
import LogicaNegocio.InicioSesionDAO;
import java.awt.Font;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class VentanaInicioSesion extends JFrame implements KeyListener, ActionListener, FocusListener{
	private JButton botonSalir;
	private JButton botonEntrar;
	private JTextField textoUsuario;
	private JTextField textoContrasenia;
	private Container contenedorPrincipal;
        private String contrasena="";

	public VentanaInicioSesion(){
            inicializaComponentes();
            setTitle("Bolsa de trabajo UV");
            setSize(395,240);
            setLocationRelativeTo(null);
            //setIconImage(new ImageIcon(getClass().getResource(icono.getRutaIcono())).getImage());
            setVisible(true);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void inicializaComponentes(){
            contenedorPrincipal = getContentPane();
            contenedorPrincipal.setLayout(null);
            contenedorPrincipal.add(textoUsuario = new JTextField("Ingresa tu usuario"));
            contenedorPrincipal.add(textoContrasenia = new JTextField("Ingresa tu contraseña"));
            contenedorPrincipal.add(botonEntrar = new JButton("Entrar"));
            contenedorPrincipal.add(botonSalir = new JButton("Salir"));
            textoUsuario.setBounds(20,20,350,50);
            textoUsuario.setFont(new Font(" Arial " , Font.PLAIN , 20 ));
            textoUsuario.addKeyListener(this);
            textoUsuario.addFocusListener(this);
            textoContrasenia.setBounds(20,90,350,50);
            textoContrasenia.setFont(new Font(" Arial " , Font.PLAIN , 20 ));
            textoContrasenia.addFocusListener(this);
            textoContrasenia.addKeyListener(this);
            botonSalir.setBounds(285, 160, 85,30);
            botonSalir.addActionListener(this);
            botonEntrar.setBounds(180, 160, 85,30);
            botonEntrar.addActionListener(this);
        }
        public void inicioSesion(){
        	InicioSesionDAO accesoSesion = new InicioSesionDAO();
            CuentaValidada cuentaValidada = accesoSesion.validarCuenta(textoUsuario.getText(), contrasena);
            if (cuentaValidada.getCodigoAutenticacion()==CodigoAutenticacion.AutenticacionExitosa){
                new VentanaPrincipal(cuentaValidada.getIdCuenta());
                dispose();
            }else{
                if (cuentaValidada.getCodigoAutenticacion()==CodigoAutenticacion.UsuarioNoEncontrado){
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado, prueba otra vez");
                }else{
            	    JOptionPane.showMessageDialog(null, "Usuario o contraseña inválida");
                }
            }
        }
        @Override
        public void keyPressed(KeyEvent evento){
            int codigoTecla=evento.getKeyCode();
            if (evento.getSource()==textoUsuario){
                if (textoUsuario.getText().equals("Ingresa tu usuario")){
                    textoUsuario.setText("");
                }else{
                    if (textoUsuario.getText().length()==1 && codigoTecla==8){
                        evento.consume();
                        textoUsuario.setText("Ingresa tu usuario");
                    }
                }
            }else if(evento.getSource()==textoContrasenia){
                if (textoContrasenia.getText().equals("Ingresa tu contraseña")){
                    textoContrasenia.setText("");
                }else{
                    if (contrasena.length()>0 && codigoTecla==8){
                        contrasena=contrasena.substring(0, contrasena.length()-1);
                    }
                    if (textoContrasenia.getText().length()==1 && codigoTecla==8){
                        evento.consume();
                        textoContrasenia.setText("Ingresa tu contraseña");
                    }
                    if (codigoTecla==10){
                        inicioSesion();
                    }
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent evento){
            if(evento.getSource()==textoContrasenia){
                if (textoContrasenia.getText().equals("Ingresa tu contraseña")==false){
                    String texto = textoContrasenia.getText();
                    boolean uno=false;
                    for (int c=0;c<texto.length();c++){
                        if (texto.charAt(c)!='*' && uno==false){
                            uno=true;
                            String reemplazo = texto.replace(texto.charAt(c), '*');
                            contrasena=contrasena+texto.charAt(c);
                            textoContrasenia.setText(reemplazo);
                        }
                    }
                }
            }
        }
        @Override
        public void keyTyped(KeyEvent evento){
            
        }
        @Override
        public void actionPerformed(ActionEvent evento){
            if (evento.getSource()==botonEntrar){
                inicioSesion();
            }else if(evento.getSource()==botonSalir){
                dispose();
            }
        }

    @Override
    public void focusGained(FocusEvent evento) {
        
    }

    @Override
    public void focusLost(FocusEvent evento) {
        if (evento.getSource().equals(textoUsuario)){
            if (textoUsuario.getText().equals("")){
                textoUsuario.setText("Ingresa tu usuario");
            }
        }else if(evento.getSource().equals(textoContrasenia)){
            if (textoContrasenia.getText().equals("")){
                textoContrasenia.setText("Ingresa tu contraseña");
            }
        }
    }
}