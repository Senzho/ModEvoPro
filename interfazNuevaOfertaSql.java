package LogicaNegocio;

public interface interfazNuevaOfertaSql {
    public void guardarNuevaOferta(Oferta oferta, int idEmpleador);
    public void guardarDiasOferta(Dia dia, int idOferta);
}
