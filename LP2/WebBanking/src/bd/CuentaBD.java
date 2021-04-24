/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alder
 */
public class CuentaBD {
    
    private String insertar;
    private String consultar;
    private  String actualizar;
    private PreparedStatement PS;
    private DefaultTableModel DT;
    private ResultSet RS;
    private final Conectar CN;
    
    public CuentaBD() {
        PS = null;
        CN = new Conectar();
    }
    
    //insercion de datos en tabla cuenta
    public int insertar(int nroCuenta, double saldo, String tipoCuenta,int pinAcceso, int pinTransaccion, int nroCedula){
        int res = 0;
        insertar = "INSERT INTO cuenta (nroCuenta, saldo, tipoCuenta,pinAcceso,pinTransaccion,Cliente_cedula) values("+nroCuenta+",'"+saldo+"','"+tipoCuenta+"','"+pinAcceso+"','"+pinTransaccion+"','"+nroCedula+"')";
        //System.err.println(insertar);
        try {
           PS= CN.getConnection().prepareStatement(insertar);
           res = PS.executeUpdate();
           if(res > 0){
               JOptionPane.showMessageDialog(null, "Registro guardado");
           }
        } catch (SQLException e){
            System.err.println("Error al guardar los datos: " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
    }
    
     private DefaultTableModel setTitulos(){
        DT = new DefaultTableModel();
        DT.addColumn("nroCuenta");
        DT.addColumn("saldo");
        DT.addColumn("tipoCuenta");
        DT.addColumn("pinAcceso");
        DT.addColumn("pinTransaccion");
        DT.addColumn("Cliente_cedula");
        return DT;
    }
     
     //consulta de datos en tabla cuneta de acuerdo al numero de cedula
     public DefaultTableModel consultarPorCedula(int cedula){
          try {
            setTitulos();
            consultar = "SELECT * FROM `cuenta` WHERE `Cliente_cedula` ="+cedula;
            //System.out.println(insertar);
            PS = CN.getConnection().prepareStatement(consultar);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while(RS.next()){
                //en este si es desde 1
                fila[0]=RS.getInt(1);
                fila[1]=RS.getDouble(2);
                fila[2]=RS.getString(3);
                fila[3]=RS.getInt(4);
                fila[4]=RS.getInt(5);
                fila[5]=RS.getInt(6);  
                DT.addRow(fila);
                
            }
        }catch(SQLException e){
            System.out.println("Error al listar los datos: "+ e.getMessage());
        } finally{
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
     }
     
     
     public DefaultTableModel consultarPorNroCuenta(int nroCuenta){
          try {
            setTitulos();
            consultar = "SELECT * FROM `cuenta` WHERE `nroCuenta` ="+nroCuenta;
            
            PS = CN.getConnection().prepareStatement(consultar);
            RS = PS.executeQuery();
            Object[] fila = new Object[6];
            while(RS.next()){
                //en este si es desde 1
                fila[0]=RS.getInt(1);
                fila[1]=RS.getDouble(2);
                fila[2]=RS.getString(3);
                fila[3]=RS.getInt(4);
                fila[4]=RS.getInt(5);
                fila[5]=RS.getInt(6);  
                DT.addRow(fila);
                
            }
        }catch(SQLException e){
            System.out.println("Error al listar los datos: "+ e.getMessage());
        } finally{
            PS = null;
            RS = null;
            CN.desconectar();
        }
        return DT;
     }
     
     
     
     
    /* //funciona
     public int actualizarCedula(int cedulaOriginal,int cedula){
        int res = 0;
        actualizar = "UPDATE `cliente` SET `cedula` = '"+cedula+"' WHERE `cliente`.`cedula` ="+cedulaOriginal;
        try {
           PS= CN.getConnection().prepareStatement(actualizar);

           res = PS.executeUpdate();
           if(res > 0){
               JOptionPane.showMessageDialog(null, "Registro modificado");
           }
        } catch (SQLException e){
            System.err.println("Error al modificar los datos: " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
     }*/
     
     //actualizacion del saldo de acuerdo al nro de cuenta
     public int actualizarSaldo(int nroCuenta,double saldo){
        int res = 0;
        
        actualizar = "UPDATE `cuenta` SET `saldo` = '"+saldo+"' WHERE `cuenta`.`nroCuenta` ="+nroCuenta;
        try {
           PS= CN.getConnection().prepareStatement(actualizar);

           res = PS.executeUpdate();
           if(res > 0){
               JOptionPane.showMessageDialog(null, "Registro modificado");
           }
        } catch (SQLException e){
            System.err.println("Error al modificar los datos: " + e.getMessage());
        } finally {
            PS = null;
            CN.desconectar();
        }
        return res;
        //funciona
     }
     
     
    
}
