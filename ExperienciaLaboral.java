package LogicaNegocio;

import java.util.ArrayList;

public class ExperienciaLaboral{
    private int idExperiencia;
    private String anioInicio;
    private String anioFin;
    private String empresa;
    private String puesto;
    private String descripcion;
    private ArrayList<Referencia> listaReferencias;

    public ExperienciaLaboral(int idExperiencia, String anioInicio, String anioFin, String empresa, String puesto, String descripcion, ArrayList<Referencia> listaReferencias){
        this.idExperiencia = idExperiencia;
        this.anioInicio = anioInicio;
        this.anioFin = anioFin;
        this.empresa = empresa;
        this.puesto = puesto;
        this.descripcion = descripcion;
        this.listaReferencias = listaReferencias;
    }

    public int getIdExperiencia() {
        return idExperiencia;
    }
    public void setIdExperiencia(int idExperiencia) {
        this.idExperiencia = idExperiencia;
    }
    public String getAnioInicio() {
        return anioInicio;
    }
    public void setAnioInicio(String anioInicio) {
        this.anioInicio = anioInicio;
    }
    public String getAnioFin() {
        return anioFin;
    }
    public void setAnioFin(String anioFin) {
        this.anioFin = anioFin;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public ArrayList<Referencia> getListaReferencias() {
        return listaReferencias;
    }
    public void setListaReferencias(ArrayList<Referencia> listaReferencias) {
        this.listaReferencias = listaReferencias;
    }
}