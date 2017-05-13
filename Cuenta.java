package LogicaNegocio;

public class Cuenta{
    private String contrasena;
    private int idCuenta;
    private String nickName;
    private int tipo;

    public Cuenta(String contrasena, int idCuenta, String nickName, int tipo) {
        this.contrasena = contrasena;
        this.idCuenta = idCuenta;
        this.nickName = nickName;
        this.tipo = tipo;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public int getIdCuenta() {
        return idCuenta;
    }
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
