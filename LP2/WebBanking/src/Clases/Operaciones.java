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
public class Operaciones{
    

    //metodos para gestionar las operaciones de transferencia, deposito y pago de servicios
    
    public Cuenta Depositar(Cuenta cta,int nroCuenta, double monto, int pinTransaccion) throws Exception{
        
        Deposito d = new Deposito(cta,nroCuenta,monto,pinTransaccion);//instancia de la clase deposito

        try {
            cta = d.validarDeposito();//realizando el deposito
        } catch (Exception e) {
            //System.out.println(e);
             throw e;//retorna el tipo error ocurrido
        }
        
        return cta;//cta con saldo actualizado
            
    }
    
    
    public Cuenta[] Transferir(Cuenta ctaEmisora,Cuenta ctaDestino,double monto,int pinTransaccion) throws Exception{
        Transferencia t = new Transferencia(ctaEmisora, ctaDestino, monto, pinTransaccion); //instanciacion de transferencia
        Cuenta[] ctas = new Cuenta[2];//vector donde se guardaran las cuentas
        
        try {
            t.validarOperacion(); //validando transferencia
        } catch (Exception e) {
            //System.out.println(e);
            throw e;//retorna el tipo error ocurrido
        }
        
        ctas[0]=t.getCuentaEmisora();
        ctas[1]=t.getCuentaDestino();
        
        return ctas;//se retornan las cuentas para poder actualizar los datos
    }
    
    public Cuenta[] PagarServicio(Cuenta ctaEmisora,Cuenta ctaDestino,double monto,int pinTransaccion,String servicio) throws Exception {
               
        PagoServicios p = new PagoServicios(ctaEmisora, ctaDestino, monto, pinTransaccion, servicio);
        Cuenta[] ctas = new Cuenta[2];
        
        try {
            p.validarOperacion();//validando pago de servicios
        } catch (Exception e) {
            throw e;//retorna el tipo error ocurrido
        }
        
        ctas[0]=p.getCuentaEmisora();
        ctas[1]=p.getCuentaDestino();
        
        return ctas;//se retornan las cuentas para poder actualizar los datos
    }
    
    
}
