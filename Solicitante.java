package LogicaNegocio;

import java.util.ArrayList;
import java.util.Date;

public class Solicitante {
    private int idSolicitante;
    private String areaProfesional;
    private String gradoEstudio;
    private String nombre;
    private Date fechaNacimiento;
    private ArrayList<Estudio> listaEstudios;
    private ArrayList<Certificacion> listaCertificaciones;
    private ArrayList<ExperienciaLaboral> listaExperiencias;

    public Solicitante(int idSolicitante, String areaProfesional, String gradoEstudio, String nombre, Date fechaNacimiento, ArrayList<Estudio> listaEstudios, ArrayList<Certificacion> listaCertificaciones, ArrayList<ExperienciaLaboral> listaExperiencias) {
        this.idSolicitante = idSolicitante;
        this.areaProfesional = areaProfesional;
        this.gradoEstudio = gradoEstudio;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.listaEstudios = listaEstudios;
        this.listaCertificaciones = listaCertificaciones;
        this.listaExperiencias = listaExperiencias;
    }
    
    public String getAreaProfesional() {
        return areaProfesional;
    }
    public void setAreaProfesional(String areaProfesional) {
        this.areaProfesional = areaProfesional;
    }
    public String getGradoEstudio() {
        return gradoEstudio;
    }
    public void setGradoEstudio(String gradoEstudio) {
        this.gradoEstudio = gradoEstudio;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public ArrayList<Estudio> getListaEstudios() {
        return listaEstudios;
    }
    public void setListaEstudios(ArrayList<Estudio> listaEstudios) {
        this.listaEstudios = listaEstudios;
    }
    public ArrayList<Certificacion> getListaCertificaciones() {
        return listaCertificaciones;
    }
    public void setListaCertificaciones(ArrayList<Certificacion> listaCertificaciones) {
        this.listaCertificaciones = listaCertificaciones;
    }
    public ArrayList<ExperienciaLaboral> getListaExperiencias() {
        return listaExperiencias;
    }
    public void setListaExperiencias(ArrayList<ExperienciaLaboral> listaExperiencias) {
        this.listaExperiencias = listaExperiencias;
    }
    public int getIdSolicitante() {
        return idSolicitante;
    }
    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }
}
