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

//simulacion de un deposito en caja
public class Deposito {
    
    private Cuenta cta;
    private double monto;
    private int pinTransaccion;
    private int nroCuenta;
    
    public Deposito(Cuenta cta,int nroCuenta, double monto, int pinTransaccion) {
        this.cta = cta;
        this.nroCuenta=nroCuenta;
        this.monto = monto;
        this.pinTransaccion = pinTransaccion;
    }
    
    public Cuenta validarDeposito() throws Exception{ 
    
        if (getNroCuenta()!=cta.getNroCuenta()) {
            throw new Exception("Nro de cuenta incorrecto");
        }
        
        if (getPinTransaccion()!=cta.getPinTransaccion()) {
            throw new Exception("Pin transaccional incorrecto");
        }
        
        if (getMonto()<=0) {
            throw new Exception("Monto incorrecto");
        }
        
        cta.setSaldo(cta.getSaldo() + getMonto());
        
        return cta;
    }
    
    public int getPinTransaccion(){
        return pinTransaccion;
    }
    
    public double getMonto(){
        return monto;
    }
    
    public int getNroCuenta(){
        return  nroCuenta;
    }
}
