package LogicaNegocio;

import AccesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OfertaRespondidaDAO implements InterfazOfertaRespondida{
    @Override
    public boolean guardarOfertaRespondida(int idEmpleador, int idSolicitante, int idOferta) {
        boolean exito = false;
        ConexionSQL conexionSql = new ConexionSQL();
        Connection conexion = conexionSql.getConexion();
        try{
            PreparedStatement orden = conexion.prepareStatement("insert into respuestas (idEmpleador, idSolicitante, idOferta) values (?, ?, ?)");
            orden.setInt(1, idEmpleador);
            orden.setInt(2, idSolicitante);
            orden.setInt(3, idOferta);
            orden.execute();
            exito = true;
        }catch(SQLException | NullPointerException excepcion){
            System.out.println(excepcion.getMessage());
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexionSql.cerrarConexion();
        }
        return exito;
    }
    @Override
    public int getIdEmpleador(int idOferta) {
        int idEmpleador = -1;
        ConexionSQL conexionSql = new ConexionSQL();
        Connection conexion = conexionSql.getConexion();
        try{
            PreparedStatement orden = conexion.prepareStatement("select * from oferta where idOferta =?");
            orden.setInt(1, idOferta);
            ResultSet resultadoConsulta = orden.executeQuery();
            resultadoConsulta.first();
            idEmpleador = resultadoConsulta.getInt(7);
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexionSql.cerrarConexion();
        }return idEmpleador;
    }
}
