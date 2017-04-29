package LogicaNegocio;

import java.util.ArrayList;

public interface InterfazEditarOferta {
    public void editarOferta(Oferta oferta, int idEmpleador, int idOferta);
    public void editarDias(ArrayList<Dia> listaDiasVentana, int idOferta);
}
