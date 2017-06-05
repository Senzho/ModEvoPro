package LogicaNegocio;

public class DetallesOfertaDAO implements InterfazDetallesOferta{
	public Oferta idOferta(int idOferta){
		ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
	  try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from Ofertas where idOferta = ?");
            orden.setInt(1, idOferta);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                int idOferta = resultadoConsulta.getInt(1);
                String vacante = resultadoConsulta.getString(2);
                String empresa = resultadoConsulta.getString(3);
                int numeroVacantes = resultadoConsulta.getInt(4);
                String salario = resultadoConsulta.getString(5);
                String descripcion = resultadoConsulta.getString(6);
                Oferta oferta = new Oferta(idOferta, vacante, empresa, numeroVacantes, salario, descripcion, Arraylist<oferta> getDias(int idOferta));
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        return oferta;      
    }
    public Arraylist<Oferta> getDias(int idOferta){
    	ConexionSQL conexion = new ConexionSQL();
        Connection conexionInicio=conexion.getConexion();
        Arraylist<Oferta> listaDias;
        try{
            PreparedStatement orden=conexionInicio.prepareStatement("select * from Dias where idOferta = ?");
            orden.setInt(1, idOferta);
            ResultSet resultadoConsulta = orden.executeQuery();
            while (resultadoConsulta.next()){
                String nombre = resultadoConsulta.getString(1);
                String horaInicio = resultadoConsulta.getString(2);
                String horafin = resultadoConsulta.getString(3);
                listaDias.add(new Dia(nombre, horaInicio, horafin));
            }
        }catch(SQLException | NullPointerException excepcion){
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.WARNING, "La conexión podría ser nula | la sentencia SQL esta mal");
        }finally{
            conexion.cerrarConexion();
        }
        listaDias;
    }
}