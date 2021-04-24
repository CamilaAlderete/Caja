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
public class Cliente {
    
    private String nombre;
    private String apellido;
    private int cedula;
    private Cuenta cta;
    //private Cuenta []cta;//para varias cuentas
    
    public Cliente(String nombre, String apellido, int cedula,int nroCuenta,String tipoCuenta,double saldo, int pinAcceso,int pinTransaccion){
        this.nombre= nombre;
        this.apellido=apellido;
        this.cedula=cedula;
        this.cta= new Cuenta(nroCuenta,tipoCuenta,saldo,pinAcceso,pinTransaccion);
         //this.cta= new Cuenta[totalCuentas];
    }
    
    //datos cliente
    public int getCedula(){
        return cedula;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
        
    //datos cuenta
   public int getNroCuenta(){
        return cta.getNroCuenta();
    }
    
    public double getSaldo(){
        return cta.getSaldo();
    }
    
    
    public void setSaldo(double saldo){
        cta.setSaldo(saldo);
    }
    
    public String getTipo(){
        return cta.getTipo();
    }
    
    public Cuenta getCuenta(){
        return cta;
    }
    
    public void setCuenta(Cuenta cta){
        this.cta=cta;
    }
    
    public void getDatos(){
        System.out.println(getNombre()+"\n"+getApellido()+"\n"+getCedula()+"\n"+getCuenta().getNroCuenta());
    }
}
