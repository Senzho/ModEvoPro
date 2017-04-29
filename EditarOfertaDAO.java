package LogicaNegocio;

import AccesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditarOfertaDAO implements InterfazEditarOferta{
    @Override
    public void editarOferta(Oferta oferta, int idEmpleador, int idOferta) {
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden = conexionInicio.prepareStatement("update oferta set vacante = ?, empresa = ?, numeroVacantes = ?, salario = ?, descripcion = ?, idEmpleador = ? where idOferta = ?");
            orden.setString(1, oferta.getVacante());
            orden.setString(2, oferta.getEmpresa());
            orden.setInt(3, oferta.getNumeroVacantes());
            orden.setString(4, oferta.getSalario());
            orden.setString(5, oferta.getDescripcion());
            orden.setInt(6, idEmpleador);
            orden.setInt(7, idOferta);
            orden.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            System.out.println(excepcion.getMessage());
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
    }
    @Override
    public void editarDias(ArrayList<Dia> listaDiasVentana, int idOferta) {
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden = conexionInicio.prepareStatement("delete from dias where idOferta = ?");
            orden.setInt(1, idOferta);
            orden.execute();
            NuevaOfertaDAO nuevaOferta = new NuevaOfertaDAO();
            nuevaOferta.guardarDiasOferta(listaDiasVentana, idOferta);
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
    }
}
