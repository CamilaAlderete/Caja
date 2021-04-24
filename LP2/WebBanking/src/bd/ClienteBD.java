/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;


import bd.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author alder
 */

public class ClienteBD {
    private String insertar;
    private String consultar;
    private  String actualizar;
    private PreparedStatement PS;
    private DefaultTableModel DT;
    private ResultSet RS;
    private final Conectar CN;
    
    public ClienteBD() {
        PS = null;
        CN = new Conectar();
    }
    //insercion de datos en tabla cliente
    public int insertar(String nombre, String apellido, int nroCedula){
        int res = 0;
        insertar = "INSERT INTO cliente (cedula, nombre, apellido) values("+nroCedula+",'"+nombre+"','"+apellido+"')";
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
        DT.addColumn("cedula");
        DT.addColumn("nombre");
        DT.addColumn("apellido");
        return DT;
    }
     
     //consulta en tabla cliente de acuerdo a la cedula
     public DefaultTableModel consultar(int cedula){
          try {
            setTitulos();
            consultar = "SELECT * FROM `cliente` WHERE `cedula` ="+cedula;
            PS = CN.getConnection().prepareStatement(consultar);
            RS = PS.executeQuery();
            Object[] fila = new Object[3];
            while(RS.next()){
                fila[0]=RS.getInt(1);
                fila[1]=RS.getString(2);
                fila[2]=RS.getString(3);
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
     
     
     
     //no es necesario
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
     }
}
