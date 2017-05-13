package LogicaNegocio;

public interface InterfazLoginDatosSql {
    public CuentaValidada validarCuenta(String usuario, String contrasena);
}
