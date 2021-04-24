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
public class Cuenta {
    private int nroCuenta; //ej: 123 / 1
    private String tipoCuenta;//ej: Credito
    private double saldo;
    private int pinTransaccion;
    private int pinAcceso;
    
    public Cuenta(int nroCuenta, String tipo,double saldo, int pinAcceso,int pinTransaccion){
        //this.nroCuenta=nroCuenta+"/"+nro.toString();
        this.nroCuenta=nroCuenta;
        this.tipoCuenta=tipo;
        this.saldo=saldo;
        this.pinAcceso=pinAcceso;
        this.pinTransaccion=pinTransaccion;
    }

 
    
    public int getNroCuenta(){
        return nroCuenta;
    }
    
    public String getTipo(){
        return tipoCuenta;
    }
    
    public void setSaldo(double saldo){
        this.saldo=saldo;
    }
    public double getSaldo(){
        return saldo;
    }
    
    public int getPinAcceso(){
        return pinAcceso;
    }
    
    public int getPinTransaccion(){
        return pinTransaccion;
    }
}
