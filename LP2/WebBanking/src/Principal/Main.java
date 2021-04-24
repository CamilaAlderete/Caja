
package Principal;
import Clases.*;
import bd.*;
import java.sql.Date;
import javax.swing.JTable;
//import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/* Tiempo:

    long fecha = System.currentTimeMillis();
    java.sql.Timestamp date = new java.sql.Timestamp(fecha);

*/
public class Main {
 
    public static void main(String[] args) {
        
        /*Cliente c1 = new Cliente("Camila", "Alderete", 4975479, 1234, "credito", 1000000,123,123);
        Cliente e1 = new Cliente("Ande", "SA", 2223344,4321, "ahorro", 1000000, 123, 123);
        //System.out.println(c1.getSaldo());
        Operaciones p = new Operaciones();*/
        /*Prueba deposito--------------------------------------------------*/
        //Cuenta c;
        //System.out.println("Antes de deposito de 10.000: "+c1.getSaldo());
        
        /*try {
            //c = p.Depositar(c1.getCuenta(),1234, 10000, 123);
           p.Depositar(c1.getCuenta(),1234, 10000, 123);
        } catch (Exception e) {
            System.out.println(e);
        }*/
        
        //c1.setCuenta(c); no hace falta nose porque
        //System.out.println("Luego de deposito de 10.000: "+c1.getSaldo()); 
        /*------------------------------------------------------------------*/
        
        //System.out.println();
        
        /*Prueba transferencia----------------------------------------------*/
        //System.out.println("Antes de transferencia de 10.000 \nSaldo del emisor: "+c1.getSaldo()+"\nSaldo del destinatario: "+e1.getSaldo());
        
        /*try {
            p.Transferir(c1.getCuenta(),e1.getCuenta(),10000,123);
        } catch (Exception e) {
            System.out.println(e);
        }*/
        
        //System.out.println("Luego de transferencia de 10.000\nSaldo del emisor: "+c1.getSaldo()+"\nSaldo del destinatario: "+e1.getSaldo());
        
        //funciona no se porque
        /*-------------------------------------------------------------------*/
    
        
        //System.out.println();
        
        /*Prueba pago servicio----------------------------------------------*/
        //System.out.println("Antes pago servicio electricidad de 100.000\nSaldo del emisor: "+c1.getSaldo()+"\nSaldo del destinatario: "+e1.getSaldo());
        /*try {
            p.PagarServicio(c1.getCuenta(),e1.getCuenta(), 100000, 123, "electricidad");
        } catch (Exception e) {
            System.out.println(e);
        }*/
        //System.out.println("Despues pago servicio electricidad de 100.000\nSaldo del emisor: "+c1.getSaldo()+"\nSaldo del destinatario: "+e1.getSaldo());
        
        //funciona no se porque
        
        
        
        /*-------------------------------------------------------------------------*/
        
            //prueba  base de datos
        
        //ClienteBD conexion = new ClienteBD();
        
        //insertar datos cliente
        //conexion.insertar(c1.getNombre(), c1.getApellido(), c1.getCedula());
        
        //consultar datos en tabla cliente
        /*DefaultTableModel datos = new DefaultTableModel();
        datos=conexion.consultar(1);
        System.out.println("Datos:");
        for (int i = 0; i < 3; i++) {
            
            System.out.println(datos.getValueAt(0,i)); //poner en un for pa consultar
            
        }*/
        
       
            
        
        //Actualizar datos cliente
        //conexion.actualizarCedula(c1.getCedula(), 123);
        
        //Actualizar en funcion a un dato
       
        
         
        
        CuentaBD conexion1 = new CuentaBD();
        //insertar datos en tabla cuenta
        //conexion1.insertar(888, 1000,"ahorro", 123, 123, 4975479);
        
        //consulta de datos de tabla cuenta
        DefaultTableModel datos1 = new DefaultTableModel();
        datos1=conexion1.consultarPorCedula(4975479);
        
        for(int row = 0; row < datos1.getRowCount();row++) {
            for(int col = 0;col < datos1.getColumnCount();col++) {
		System.out.println(datos1.getValueAt(row, col));
            }
            System.out.println("\n");
	}
        

        /*JTable tabla = new JTable ();
        tabla.setModel(datos1);
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setViewportView(tabla);
       */
        /*TransferenciaBD t = new TransferenciaBD();
        long fecha = System.currentTimeMillis();
        //java.sql.Date date = new java.sql.Date(fecha);
        java.sql.Timestamp date = new java.sql.Timestamp(fecha); 
        t.insertar(22222, 123, 234, 1,date);
        */
        /*DefaultTableModel datos1 = new DefaultTableModel();
        datos1=t.consultarCuentaDestino(234);
        for (int i = 0; i < 5; i++) {
            System.out.println(datos1.getValueAt(0,i));
        }
        
        datos1= t.consultarCuentaEmisora(123);
        for (int i = 0; i < 5; i++) {
            System.out.println(datos1.getValueAt(0,i));
        }*/
       long fecha = System.currentTimeMillis();
       java.sql.Timestamp date = new java.sql.Timestamp(fecha);
       /*DepositoBD d = new DepositoBD();
       d.insertar(33333, 1000, 123,date);*/
       
       /*PagoServiciosBD p = new PagoServiciosBD();
       p.insertar(44441, "agua", 123, 234, 11000, date);
       */
        
        
        
        
    }
    
   
   
}


