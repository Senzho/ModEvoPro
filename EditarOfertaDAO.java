package LogicaNegocio;

import AccesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditarOfertaDAO implements InterfazEditarOferta{
    @Override
    public boolean diaExiste(String dia, int idOferta) {
        boolean existe = false;
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden = conexionInicio.prepareStatement("select * from dias where nombre = ? and idOferta = ?");
            orden.setString(1, dia);
            orden.setInt(2, idOferta);
            ResultSet resultadoConsulta = orden.executeQuery();
            if (resultadoConsulta.first()){
                existe=true;
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return existe;
    }
    @Override
    public void editarOferta(Oferta oferta, int idEmpleador) {
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden = conexionInicio.prepareStatement("update oferta set vacante= ?, empresa = ?, numeroVacantes = ?, salario = ?, descripcion = ?, idEmpleador = ? where idOferta = ?");
            NuevaOfertaDAO nuevaOferta = new NuevaOfertaDAO();
            orden.setString(1, oferta.getVacante());
            orden.setString(2, oferta.getEmpresa());
            orden.setInt(3, oferta.getNumeroVacantes());
            orden.setString(4, oferta.getSalario());
            orden.setString(5, oferta.getDescripcion());
            orden.setInt(6, idEmpleador);
            orden.setInt(7, oferta.getIdOferta());
            orden.executeQuery();
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
    }
    @Override
    public void editarDias(ArrayList<Dia> listaDias, int idOferta) {
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            for(int c = 0; c < listaDias.size(); c++){
                Dia dia = listaDias.get(c);
                PreparedStatement orden;
                if (diaExiste(dia.getNombre(), idOferta)){
                    orden = conexionInicio.prepareStatement("update dias set horaInicio = ?, horaFin = ?");
                    orden.setString(1, dia.getHoraInicio());
                    orden.setString(2, dia.getHoraFin());
                    orden.executeQuery();
                }else{
                    orden = conexionInicio.prepareStatement("insert into dias values(?, ?, ?, ?)");
                    orden.setString(1, dia.getNombre());
                    orden.setString(2, dia.getHoraInicio());
                    orden.setString(3, dia.getHoraFin());
                    orden.setInt(4, idOferta);
                    orden.executeQuery();
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
    }
}
