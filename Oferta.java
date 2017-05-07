package LogicaNegocio;

import java.util.ArrayList;

public class Oferta {
    private int idOferta;
    private String vacante;
    private String empresa;
    private int numeroVacantes;
    private String salario;
    private String descripcion;
    private ArrayList<Dia> listaDias;

    public Oferta(int idOferta, String vacante, String empresa, int numeroVacantes, String salario, String descripcion, ArrayList<Dia> listaDias) {
        this.idOferta = idOferta;
        this.vacante = vacante;
        this.empresa = empresa;
        this.numeroVacantes = numeroVacantes;
        this.salario = salario;
        this.descripcion = descripcion;
        this.listaDias = listaDias;
    }

    public int getIdOferta() {
        return idOferta;
    }
    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }
    public String getVacante() {
        return vacante;
    }
    public void setVacante(String vacante) {
        this.vacante = vacante;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public int getNumeroVacantes() {
        return numeroVacantes;
    }
    public void setNumeroVacantes(int numeroVacantes) {
        this.numeroVacantes = numeroVacantes;
    }
    public String getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        this.salario = salario;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public ArrayList<Dia> getListaDias() {
        return listaDias;
    }
    public void setListaDias(ArrayList<Dia> listaDias) {
        this.listaDias = listaDias;
    }
}
