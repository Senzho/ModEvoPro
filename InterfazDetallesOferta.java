package LogicaNegocio;

import java.util.ArrayList;

public interface InterfazDetallesOferta{
    public Oferta getOferta(int idOferta);
    public ArrayList<Dia> getDias(int idOferta);
}