package LogicaNegocio;

import AccesoDatos.ConexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentanaPrincipalDAO implements InterfazVentanaPrincipal{
    @Override
    public int getTipoCuenta(int idCuenta) {
        int tipoCuenta = 0;
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select tipoCuenta from cuenta where idCuenta = ?");
            orden.setInt(1, idCuenta);
            ResultSet resultadoConsulta;
            resultadoConsulta=orden.executeQuery();
            resultadoConsulta.first();
            tipoCuenta= resultadoConsulta.getInt(1);
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return tipoCuenta;
    }
    @Override
    public Solicitante getSolicitante(int idCuenta) {
        Solicitante solicitante = null;
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from solicitante where idCuenta = ?");
            orden.setInt(1, idCuenta);
            ResultSet resultadoConsulta = orden.executeQuery();
            resultadoConsulta.first();
            int idSolicitante = resultadoConsulta.getInt(1);
            String areaProfesional = resultadoConsulta.getString(2);
            String gradoEstudio = resultadoConsulta.getString(3);
            String nombre = resultadoConsulta.getString(4);
            Date fechaNacimiento = resultadoConsulta.getDate(5);
            solicitante = new Solicitante(idSolicitante, areaProfesional, gradoEstudio, nombre, fechaNacimiento, this.getListaEstudios(idSolicitante), this.getListaCertificaciones(idSolicitante), this.getListaExperiencias(idSolicitante));
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return solicitante;
    }
    @Override
    public Empleador getEmpleador(int idCuenta){
        Empleador empleador = null;
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from empleador where idCuenta = ?");
            orden.setInt(1, idCuenta);
            ResultSet resultadoConsulta = orden.executeQuery();
            resultadoConsulta.first();
            int idEmpleador = resultadoConsulta.getInt(1);
            String nombreEmpresa = resultadoConsulta.getString(2);
            String descripcion = resultadoConsulta.getString(3);
            String direccion = resultadoConsulta.getString(4);
            String numero = resultadoConsulta.getString(6);
            empleador = new Empleador(idEmpleador, nombreEmpresa, descripcion, direccion, this.getOfertasEmpleador(idEmpleador), numero);
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return empleador;
    }
    @Override
    public ArrayList<Oferta> getOfertasEmpleador(int idEmpleador){
        ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from oferta where idEmpleador = ?");
            orden.setInt(1, idEmpleador);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                int idOferta = resultadoConsulta.getInt(1);
                String vacante = resultadoConsulta.getString(2);
                String empresa = resultadoConsulta.getString(3);
                int numeroVacantes = resultadoConsulta.getInt(4);
                String salario = resultadoConsulta.getString(5);
                String descripcion = resultadoConsulta.getString(6);
                Oferta oferta = new Oferta(idOferta, vacante, empresa, numeroVacantes, salario, descripcion, this.getDiasOferta(idOferta));
                listaOfertas.add(oferta);
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return listaOfertas; 
    }
    @Override
    public ArrayList<Estudio> getListaEstudios(int idSolicitante) {
        ArrayList<Estudio> listaEstudios = new ArrayList<Estudio>();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from estudios where idSolicitante = ?");
            orden.setInt(1, idSolicitante);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                int idEstudio = resultadoConsulta.getInt(1);
                Date fechaInicio = resultadoConsulta.getDate(2);
                Date fechaFin = resultadoConsulta.getDate(3);
                String centroEstudio = resultadoConsulta.getString(4);
                String titulo = resultadoConsulta.getString(5);
                String nivel = resultadoConsulta.getString(6);
                listaEstudios.add(new Estudio(idEstudio, fechaInicio, fechaFin, centroEstudio, titulo, nivel));
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return listaEstudios;
    }
    @Override
    public ArrayList<Certificacion> getListaCertificaciones(int idSolicitante) {
        ArrayList<Certificacion> listaCertificaciones = new ArrayList<Certificacion>();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from certificaciones where idSolicitante = ?");
            orden.setInt(1, idSolicitante);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                int idCertificacion = resultadoConsulta.getInt(1);
                String nombre = resultadoConsulta.getString(2);
                Date fecha = resultadoConsulta.getDate(3);
                String empresa = resultadoConsulta.getString(4);
                String matricula = resultadoConsulta.getString(5);
                listaCertificaciones.add(new Certificacion(idCertificacion, nombre, fecha, empresa, matricula));  
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return listaCertificaciones;      
    }
    @Override
    public ArrayList<ExperienciaLaboral> getListaExperiencias(int idSolicitante) {
        ArrayList<ExperienciaLaboral> listaExperiencias = new ArrayList<ExperienciaLaboral>();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from experienciaLaboral where idSolicitante = ?");
            orden.setInt(1, idSolicitante);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                int idExperiencia = resultadoConsulta.getInt(1);
                String anioInicio = resultadoConsulta.getString(3);
                String anioFin = resultadoConsulta.getString(2);
                String empresa = resultadoConsulta.getString(4);
                String puesto = resultadoConsulta.getString(5);
                String descripcion = resultadoConsulta.getString(6);
                listaExperiencias.add(new ExperienciaLaboral(idExperiencia, anioInicio, anioFin, empresa, puesto, descripcion, this.getListaReferencias(idExperiencia)));
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return listaExperiencias;      
    }
    @Override
    public ArrayList<Referencia> getListaReferencias(int idExperinciaLaboral) {
        ArrayList<Referencia> listaReferencias = new ArrayList<Referencia>();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from referencias where idExperiencia = ?");
            orden.setInt(1, idExperinciaLaboral);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                int idReferencia = resultadoConsulta.getInt(1);
                String nombre = resultadoConsulta.getString(2);
                String mensaje = resultadoConsulta.getString(3);
                String numeroTelefono = resultadoConsulta.getString(4);
                listaReferencias.add(new Referencia(idReferencia,nombre, mensaje, numeroTelefono));
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return listaReferencias; 
    }
    @Override
    public ArrayList<Oferta> getOfertasPorDescripcion(String coincidencia) {
        ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from oferta");
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                int idOferta = resultadoConsulta.getInt(1);
                String vacante = resultadoConsulta.getString(2);
                String empresa = resultadoConsulta.getString(3);
                int numeroVacantes = resultadoConsulta.getInt(4);
                String salario = resultadoConsulta.getString(5);
                String descripcion = resultadoConsulta.getString(6);
                Oferta oferta = new Oferta(idOferta, vacante, empresa, numeroVacantes, salario, descripcion, this.getDiasOferta(idOferta));
                if (coincidencia(descripcion, coincidencia)){
                    listaOfertas.add(oferta);
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return listaOfertas; 
    }
    @Override
    public ArrayList<Dia> getDiasOferta(int idOferta) {
        ArrayList<Dia> listaDias = new ArrayList<Dia>();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from dias where idOferta = ?");
            orden.setInt(1, idOferta);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                String nombre = resultadoConsulta.getString(1);
                String horaInicio = resultadoConsulta.getString(2);
                String horaFin = resultadoConsulta.getString(3);
                listaDias.add(new Dia(nombre, horaInicio, horaFin));
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return listaDias; 
    }
    @Override
    public ArrayList<Oferta> getOfertasPorClave(String coincidencia) {
        ArrayList<Oferta> listaOfertas = new ArrayList<Oferta>();
        ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from oferta");
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                int idOferta = resultadoConsulta.getInt(1);
                String vacante = resultadoConsulta.getString(2);
                String empresa = resultadoConsulta.getString(3);
                int numeroVacantes = resultadoConsulta.getInt(4);
                String salario = resultadoConsulta.getString(5);
                String descripcion = resultadoConsulta.getString(6);
                Oferta oferta = new Oferta(idOferta, vacante, empresa, numeroVacantes, salario, descripcion, this.getDiasOferta(idOferta));
                if (coincidencia(descripcion, coincidencia) || coincidencia(vacante, coincidencia) || coincidencia(empresa, coincidencia) || coincidencia(salario, coincidencia)){
                    listaOfertas.add(oferta);
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return listaOfertas; 
    }
    
    public boolean coincidencia(String texto, String subCadena){
        texto = Recursos.sinAcentosMinusculas(texto);
        subCadena = Recursos.sinAcentosMinusculas(subCadena);
        int contadorCiclo;
        boolean siHay =false;
        String cadena="";
        for(contadorCiclo=0; contadorCiclo<texto.length()- subCadena.length()+1; contadorCiclo++){
            cadena=texto.substring(contadorCiclo, contadorCiclo+subCadena.length());
            if (cadena.compareTo(subCadena)==0){
                siHay=true;
                contadorCiclo=texto.length();
            }
        }
        return siHay;
    }
}
