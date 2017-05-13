package LogicaNegocio;

import AccesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetallesOfertaDAO implements InterfazDetallesOferta{
    @Override
    public Oferta getOferta(int idOferta){
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        Oferta oferta = null;
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from oferta where idOferta = ?");
            orden.setInt(1, idOferta);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                int id = resultadoConsulta.getInt(1);
                String vacante = resultadoConsulta.getString(2);
                String empresa = resultadoConsulta.getString(3);
                int numeroVacantes = resultadoConsulta.getInt(4);
                String salario = resultadoConsulta.getString(5);
                String descripcion = resultadoConsulta.getString(6);
                oferta = new Oferta(id, vacante, empresa, numeroVacantes, salario, descripcion, this.getDias(idOferta));
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return oferta;      
    }
    @Override
    public ArrayList<Dia> getDias(int idOferta){
    	ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        ArrayList<Dia> listaDias = new ArrayList<Dia>();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from dias where idOferta = ?");
            orden.setInt(1, idOferta);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                String nombre = resultadoConsulta.getString(1);
                String horaInicio = resultadoConsulta.getString(2);
                String horafin = resultadoConsulta.getString(3);
                Dia dia = new Dia(nombre, horaInicio, horafin);
                listaDias.add(dia);
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return listaDias;
    }
}