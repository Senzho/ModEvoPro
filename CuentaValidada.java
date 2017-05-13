package LogicaNegocio;

public class CuentaValidada {
    private CodigoAutenticacion codigoAutenticacion;
    private int idCuenta;
    
    public CuentaValidada(){
        idCuenta=0;
        codigoAutenticacion=CodigoAutenticacion.UsuarioNoEncontrado;
    }
    public void setIdCuenta(int idCuenta){
        this.idCuenta=idCuenta;
    }
    public void setCodigoAutenticacion(CodigoAutenticacion codigoAutenticacion){
        this.codigoAutenticacion=codigoAutenticacion;
    }
    public int getIdCuenta(){
        return idCuenta;
    }
    public CodigoAutenticacion getCodigoAutenticacion(){
        return codigoAutenticacion;
    }
}
