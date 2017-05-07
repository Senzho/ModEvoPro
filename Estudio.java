package LogicaNegocio;

import java.util.Date;

public class Estudio {
    private int idEstudio;
    private Date fechaInicio;
    private Date fechaFin;
    private String centroEstudio;
    private String titulo;
    private String nivel;

    public Estudio(int idEstuidio, Date fechaInicio, Date fechaFin, String centroEstudio, String titulo, String nivel) {
        this.idEstudio = idEstudio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.centroEstudio = centroEstudio;
        this.titulo = titulo;
        this.nivel = nivel;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getCentroEstudio() {
        return centroEstudio;
    }
    public void setCentroEstudio(String centroEstudio) {
        this.centroEstudio = centroEstudio;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getNivel() {
        return nivel;
    }
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    public int getIdEstudio() {
        return idEstudio;
    }
    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }
}
