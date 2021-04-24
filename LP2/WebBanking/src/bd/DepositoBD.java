
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
public class DepositoBD {
    
    private String insertar;
    private String consultar;
    //private  String actualizar;
    private PreparedStatement PS;
    private DefaultTableModel DT;
    private ResultSet RS;
    private final Conectar CN;

    public DepositoBD() {
        PS = null;
        CN = new Conectar();
 
    }
    
    public int insertar(int nroOperacion, double monto, int nroCta,java.sql.Timestamp fecha){
        int res = 0;
        insertar = "INSERT INTO deposito (nroOperacion, monto,Cuenta_nroCuenta,fecha) values("+nroOperacion+",'"+monto+"','"+nroCta+"','"+fecha+"')";
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
            
            //actualizacion del saldo de la cuenta
            CuentaBD cta = new CuentaBD();
            DefaultTableModel datos = new DefaultTableModel();

            //consulta y actualizacion del saldo
            datos = cta.consultarPorNroCuenta(nroCta);
            double saldo = (double) datos.getValueAt(0,1);
            saldo = saldo + monto;
            cta.actualizarSaldo(nroCta, saldo);
        }
        
        return res;
    }

    private DefaultTableModel setTitulos(){
        DT = new DefaultTableModel();
        DT.addColumn("nroOperacion");
        DT.addColumn("monto");
        DT.addColumn("Cuenta_nroCuenta");
        DT.addColumn("fecha");
        return DT;
    }
    
     public DefaultTableModel consultar(int nroCta){
         //consulta de transferencia de acuerdo a nro de cuenta de origen
          try {
            setTitulos();
            consultar = "SELECT * FROM `deposito` WHERE `Cuenta_nroCuenta` ="+nroCta;
            System.out.println(insertar);
            PS = CN.getConnection().prepareStatement(consultar);
            RS = PS.executeQuery();
            Object[] fila = new Object[4];
            while(RS.next()){
                //en este si es desde 1
                fila[0]=RS.getInt(1);
                fila[1]=RS.getDouble(2);
                fila[2]=RS.getInt(3);
                fila[3]=RS.getDate(4);
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
    
}
