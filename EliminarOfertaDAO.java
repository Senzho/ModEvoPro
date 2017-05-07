package LogicaNegocio;

import AccesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EliminarOfertaDAO implements InterfazEliminarOfertaSql {

    @Override
    public void eliminarOferta(int idOferta) {
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try {    
            PreparedStatement orden;
            orden = conexionInicio.prepareStatement("delete from oferta where idOferta = ?");
            orden.setInt(1, idOferta);
            orden.execute();
        } catch (SQLException | NullPointerException excepcion) {
            System.out.println(excepcion.getMessage());
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        } 
    }
    @Override
    public void eliminarDiasOferta(int idOferta) {
       ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try {    
            PreparedStatement orden;
            orden = conexionInicio.prepareStatement("delete from dias where idOferta = ?");
            orden.setInt(1, idOferta);
            orden.execute();
        } catch (SQLException | NullPointerException excepcion) {
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
    }
    
}
