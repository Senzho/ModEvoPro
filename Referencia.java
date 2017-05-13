package LogicaNegocio;

public class Referencia{
    private int idReferencia;
    private String nombre;
    private String mensaje;
    private String numeroTelefono;

    public Referencia(int idReferencia, String nombre, String mensaje, String numeroTelefono){
        this.idReferencia = idReferencia;
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.numeroTelefono = numeroTelefono;
    }

    public int getIdReferencia() {
        return idReferencia;
    }
    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getNumeroTelefono() {
        return numeroTelefono;
    }
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }   
}