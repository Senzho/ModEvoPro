package LogicaNegocio;

import java.util.Date;

public class Certificacion{
    private int idCertificacion;
    private String nombre;
    private Date fecha;
    private String empresa;
    private String matricula;

    public Certificacion(int idCertificacion, String nombre, Date fecha, String empresa, String matricula){
        this.idCertificacion = idCertificacion;
        this.nombre = nombre;
        this.fecha = fecha;
        this.empresa = empresa;
        this.matricula = matricula;
    }

    public int getIdCertificacion() {
        return idCertificacion;
    }
    public void setIdCertificacion(int idCertificacion) {
        this.idCertificacion = idCertificacion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}