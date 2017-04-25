package LogicaNegocio;

import java.util.ArrayList;

public interface InterfazNuevaOfertaSql {
    public void guardarNuevaOferta(Oferta oferta, int idEmpleador);
    public void guardarDiasOferta(ArrayList<Dia> listaDias, int idOferta);
}
