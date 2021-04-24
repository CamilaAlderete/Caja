package Clases;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author alder
 */
public class Transferencia {
    
    private Cuenta ctaEmisora;
    private Cuenta ctaDestino;
    private double monto;
    private int pinTransaccion;
    private Date fecha;

    public Transferencia(Cuenta ctaEmisora,Cuenta ctaDestino,double monto,int pinTransaccion) {
        this.ctaEmisora=ctaEmisora;
        this.ctaDestino=ctaDestino;
        this.monto = monto;
        this.pinTransaccion=pinTransaccion;
    }
    
    public void validarOperacion() throws Exception{
        
        if (getPinTransaccion()!=ctaEmisora.getPinTransaccion()) {
            throw new Exception("Pin transaccional incorrecto");
        }
        
        if (getMonto()>ctaEmisora.getSaldo()) {
            throw new Exception("Saldo insuficiente");
        }
        
        ctaEmisora.setSaldo(ctaEmisora.getSaldo() - getMonto());
        ctaDestino.setSaldo(ctaDestino.getSaldo() + getMonto());
        setFecha();
        //al finalizar, retornar cuentas para actualizar
        //emitirFactura()
        
        
        
    }
    
    //una vez realizada la transferencia, se deberan retornar los objetos cuenta para actualizar la base de datos 
    public Cuenta getCuentaEmisora(){
        return ctaEmisora;
    }
    
    public Cuenta getCuentaDestino(){
        return ctaDestino;
    }
    
    public void setFecha(){
        fecha = new Date();
    }
    
    public  Date getFecha(){
        return fecha;
    }
    
    public int getPinTransaccion(){
        return pinTransaccion;
    }
    
    public double getMonto(){
        return monto;
    }
    
    /*emitirFactura()
    
        //fecha actual 
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       Date date = new Date();
       System.out.println(dateFormat.format(date)); //2013/10/15 16:16:39
       
    */
    
    
}
