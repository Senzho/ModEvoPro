package LogicaNegocio;

public interface InterfazOfertaRespondida {
    public boolean guardarOfertaRespondida(int idEmpleador, int idSolicitante, int idOferta);
    public int getIdEmpleador(int idOferta);
    public void disminuirVacantes(int idOferta, int numeroVacante);
}
