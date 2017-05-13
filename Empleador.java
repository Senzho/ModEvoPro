package LogicaNegocio;

import java.util.ArrayList;

public class Empleador {
    private int idEmpleador;
    private String nombreEmpresa;
    private String descripcion;
    private String direccion;
    private ArrayList<Oferta> listaOfertas;
    private String numero;

    public Empleador(int idEmpleador, String nombreEmpresa, String descripcion, String direccion, ArrayList<Oferta> listaOfertas, String numero) {
        this.idEmpleador = idEmpleador;
        this.nombreEmpresa = nombreEmpresa;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.listaOfertas = listaOfertas;
        this.numero = numero;
    }
    public int getIdEmpleador() {
        return idEmpleador;
    }
    public void setIdEmpleador(int idEmpleador) {
        this.idEmpleador = idEmpleador;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public ArrayList<Oferta> getListaOfertas() {
        return listaOfertas;
    }
    public void setListaOfertas(ArrayList<Oferta> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }
    public String getNumero(){
        return this.numero;
    }
    public void setNumero(String numero){
        this.numero = numero;
    }
}
