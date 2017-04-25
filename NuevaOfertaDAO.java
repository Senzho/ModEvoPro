package LogicaNegocio;

import AccesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class NuevaOfertaDAO implements interfazNuevaOfertaSql{
    @Override
    public void guardarNuevaOferta(Oferta oferta, int idEmpleador){
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        int idOferta = oferta.getIdOferta();
        String vacante = oferta.getVacante();
        String empresa = oferta.getEmpresa();
        int numeroVacantes = oferta.getNumeroVacantes();
        String salario = oferta.getSalario();
        String descripcion = oferta.getDescripcion();        
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from oferta");
            orden = conexionInicio.prepareStatement("insert into oferta (idOferta, vacante, empresa, numeroVacantes, salario, descripcion, idEmpleador) values (?,?,?,?,?,?,?)");
            orden.setInt(1, idOferta);
            orden.setString(2, vacante);
            orden.setString(3, empresa);
            orden.setInt(4, numeroVacantes);
            orden.setString(5, salario);
            orden.setString(6, descripcion);
            orden.execute();
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
    }
    @Override
    public void guardarDiasOferta(Dia dia,int idOferta){
        ArrayList<Dia> listaDias = new ArrayList<Dia>();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        String nombre = dia.getNombre();
        String horaInicio = dia.getHoraInicio();
        String horaFin = dia.getHoraFin();
        try{
            PreparedStatement orden;
            for(int i = 0; i<listaDias.size(); i++){
                orden = conexionInicio.prepareStatement("insert into dia (nombre, horaInicio, horaFin, idOferta) values (?,?,?,?)");
                orden.setString(1, nombre);
                orden.setString(2, horaInicio);
                orden.setString(3, horaFin);
                orden.setInt(4, idOferta);
                orden.execute();
            }
        } catch (SQLException ex) {
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }             
    }
}
