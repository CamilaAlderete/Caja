
package bd;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alder
 */
public class TransferenciaBD {
    
    
    private String insertar;
    private String consultar;
    //private  String actualizar;
    private PreparedStatement PS;
    private DefaultTableModel DT;
    private ResultSet RS;
    private final Conectar CN;
    
    public TransferenciaBD(){
        
        PS = null;
        CN = new Conectar();
 
    }
    
    //insercion de datos en tabla transferencia
    public int insertar(int nroOperacion, int ctaEmisora, int ctaDestino, double monto, java.sql.Timestamp fecha){
        int res = 0;
        insertar = "INSERT INTO transferencia (nroOperacion, ctaDestino,monto,fecha,Cuenta_nroCuenta) values("+nroOperacion+",'"+ctaDestino+"','"+monto+"','"+fecha+"','"+ctaEmisora+"')";
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
        
        if (res==1) {
        //actualizacion de los saldos de las cuentas involucradas
        
            CuentaBD emisor = new CuentaBD();
            DefaultTableModel datos1 = new DefaultTableModel();

            //consulta y actualizacion del saldo de la cuenta emisora
            datos1 = emisor.consultarPorNroCuenta(ctaEmisora);
            double saldoEmisor = (double) datos1.getValueAt(0,1);
            saldoEmisor = saldoEmisor - monto;
            emisor.actualizarSaldo(ctaEmisora, saldoEmisor);

            CuentaBD destino = new CuentaBD();
            DefaultTableModel datos2 = new DefaultTableModel();

            //consulta y actualizacion del saldo de la cuenta destino
            datos2 = destino.consultarPorNroCuenta(ctaDestino);
            double saldoDestino = (double) datos2.getValueAt(0,1);
            saldoDestino = saldoDestino + monto;
            emisor.actualizarSaldo(ctaDestino, saldoDestino);
        }
       
        return res;
        
    }
    
    
    private DefaultTableModel setTitulos(){
        DT = new DefaultTableModel();
        DT.addColumn("nroOperacion");
        DT.addColumn("ctaDestino");
        DT.addColumn("monto");
        DT.addColumn("fecha");
        DT.addColumn("Cuenta_nroCuenta");
        return DT;
    }
    
      //consulta de acuerdo al nro de cuenta de origen
     public DefaultTableModel consultarCuentaEmisora(int ctaOrigen){
         //consulta de transferencia de acuerdo a nro de cuenta de origen
          try {
            setTitulos();
            consultar = "SELECT * FROM `transferencia` WHERE `Cuenta_nroCuenta` ="+ctaOrigen;
            //System.out.println(insertar);
            PS = CN.getConnection().prepareStatement(consultar);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while(RS.next()){
                //en este si es desde 1
                fila[0]=RS.getInt(1);
                fila[1]=RS.getInt(2);
                fila[2]=RS.getDouble(3);
                fila[3]=RS.getDate(4);
                fila[4]=RS.getInt(5);
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
     
    
     //consulta de acuerdo a la cuenta de destino
     public DefaultTableModel consultarCuentaDestino(int ctaDestino){
         //consulta de transferencia de acuerdo a nro de cuenta de origen
          try {
            setTitulos();
            consultar = "SELECT * FROM `transferencia` WHERE `ctaDestino` ="+ctaDestino;
            System.out.println(insertar);
            PS = CN.getConnection().prepareStatement(consultar);
            RS = PS.executeQuery();
            Object[] fila = new Object[5];
            while(RS.next()){
                //en este si es desde 1
                fila[0]=RS.getInt(1);
                fila[1]=RS.getInt(2);
                fila[2]=RS.getDouble(3);
                fila[3]=RS.getDate(4);
                fila[4]=RS.getInt(5);
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
     
     //actualizar datos de transferencia no es necesario
    
    
    
}


