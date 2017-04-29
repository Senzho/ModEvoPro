package LogicaNegocio;

import java.util.ArrayList;

public interface InterfazEditarOferta {
    public boolean diaExiste(String dia, int idOferta);
    public void editarOferta(Oferta oferta, int idEmpleador);
    public void editarDias(ArrayList<Dia> listaDias, int idOferta);
}
