package LogicaNegocio;

import java.util.ArrayList;

public interface InterfazVentanaPrincipal {
    public int getTipoCuenta(int idCuenta);
    public Solicitante getSolicitante(int idCuenta);
    public ArrayList<Estudio> getListaEstudios(int idSolicitante);
    public ArrayList<Certificacion> getListaCertificaciones(int idSolicitante);
    public ArrayList<ExperienciaLaboral> getListaExperiencias(int idSolicitante);
    public ArrayList<Referencia> getListaReferencias(int idExperinciaLaboral);
    public ArrayList<Oferta> getOfertasPorDescripcion(String coincidencia);
    public ArrayList<Dia> getDiasOferta(int idOferta);
    public ArrayList<Oferta> getOfertasPorClave(String coincidencia);
    public Empleador getEmpleador(int idCuenta);
    public ArrayList<Oferta> getOfertasEmpleador(int idEmpleador);
}
