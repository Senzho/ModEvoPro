package LogicaNegocio;

import AccesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InicioSesionDAO implements InterfazLoginDatosSql{
    @Override
    public CuentaValidada validarCuenta(String usuario, String contrasena) {
        CuentaValidada resultadoCuenta = new CuentaValidada();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        boolean encontrado=false;
        try{
            Statement orden=conexionInicio.createStatement();
            ResultSet resultadoConsulta;
            resultadoConsulta=orden.executeQuery("select * FROM cuenta");
            while(resultadoConsulta.next() && !encontrado){
                String resultadoUsuario=resultadoConsulta.getObject(2).toString();
                String resultadoContrasena=resultadoConsulta.getObject(3).toString();
                if (resultadoUsuario.equals(usuario) && resultadoContrasena.equals(contrasena)){
                    resultadoCuenta.setIdCuenta(Integer.parseInt(resultadoConsulta.getObject(1).toString()));
                    resultadoCuenta.setCodigoAutenticacion(CodigoAutenticacion.AutenticacionExitosa);
                    encontrado=true;
                }else{
                    if (resultadoUsuario.equals(usuario) || resultadoContrasena.equals(contrasena)){
                        resultadoCuenta.setCodigoAutenticacion(CodigoAutenticacion.AutenticacionFallida);
                        encontrado=true;
                    }else{
                        resultadoCuenta.setCodigoAutenticacion(CodigoAutenticacion.UsuarioNoEncontrado);
                    }
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return resultadoCuenta;
    }
}
