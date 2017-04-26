package LogicaNegocio;

import AccesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class NuevaOfertaDAO implements InterfazNuevaOfertaSql{
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
            PreparedStatement orden;
            orden = conexionInicio.prepareStatement("insert into oferta (idOferta, vacante, empresa, numeroVacantes, salario, descripcion, idEmpleador) values (?,?,?,?,?,?,?)");
            orden.setInt(1, idOferta);
            orden.setString(2, vacante);
            orden.setString(3, empresa);
            orden.setInt(4, numeroVacantes);
            orden.setString(5, salario);
            orden.setString(6, descripcion);
            orden.setInt(7, idEmpleador);
            orden.execute();
        } catch (SQLException | NullPointerException excepcion) {
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
    }
    @Override
    public void guardarDiasOferta(ArrayList<Dia> listaDias,int idOferta){
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden;
            for(int i = 0; i<listaDias.size(); i++){
                Dia dia = listaDias.get(i);
                String nombre = dia.getNombre();
                String horaInicio = dia.getHoraInicio();
                String horaFin = dia.getHoraFin();
                orden = conexionInicio.prepareStatement("insert into dias (nombre, horaInicio, horaFin, idOferta) values (?,?,?,?)");
                orden.setString(1, nombre);
                orden.setString(2, horaInicio);
                orden.setString(3, horaFin);
                orden.setInt(4, idOferta);
                orden.execute();
            }
        } catch (SQLException | NullPointerException excepcion) {
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }             
    }
    @Override
    public int getUltimoIdOferta() {
        int id = 0;
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            Statement orden=conexionInicio.createStatement();
            ResultSet resultadoConsulta;
            resultadoConsulta=orden.executeQuery("select * from oferta");
            if (resultadoConsulta.last()){
                id=resultadoConsulta.getInt(1);
            }
        } catch (SQLException | NullPointerException excepcion) {
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }   
        return id;
    }
}
