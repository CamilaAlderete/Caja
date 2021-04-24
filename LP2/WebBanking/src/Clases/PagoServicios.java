/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author alder
 */
public class PagoServicios extends Transferencia{
    private String servicio;
    
    public PagoServicios(Cuenta ctaEmisora, Cuenta ctaDestino, double monto, int pinTransaccion,String servicio) {
        super(ctaEmisora, ctaDestino, monto, pinTransaccion);
        this.servicio=servicio;
        
    }
    
    @Override
    public void validarOperacion() throws Exception{
        try {
            super.validarOperacion();
        } catch (Exception e) {
            throw e;
        }
            
        //emitir factura
    }
    
    public String getServicio(){
        return servicio;
    }
    
    @Override
    public Cuenta getCuentaEmisora(){
        return super.getCuentaEmisora();
    }
    
    @Override
    public Cuenta getCuentaDestino(){
        return super.getCuentaDestino();
    }
    
    /*@Override emitirFactura()*/

    
    
    
    
}
