
package Codigos;

import Clases.Conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc420
 */
public class mercaderia {

    private final String SQL_INSERT = "INSERT INTO mercaderia (producto, costo, cantidad) values(?, ?, ?)";
    private final String SQL_SELECT = "SELECT * FROM mercaderia WHERE id < 3";
    private PreparedStatement PS;
    private DefaultTableModel DT;
    private ResultSet RS;
    private final Conectar CN;

    public mercaderia() {
        PS = null;
        CN = new Conectar();
    }
    
    private DefaultTableModel setTitulos(){
        DT = new DefaultTableModel();
        DT.addColumn("id");
        DT.addColumn("producto");
        DT.addColumn("costo");
        DT.addColumn("cantidad");
        return DT;
    }
    
    public int insertDatos(String pro, int costo, int Cantidad){
        int res = 0;
        try {
           PS= CN.getConnection().prepareStatement(SQL_INSERT);
           PS.setString(1, pro);
           PS.setInt(2, costo);
           PS.setInt(3, Cantidad);
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
    
    public DefaultTableModel getDatos() {
        try {
            setTitulos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[4];
            while(RS.next()){
                fila[0]=RS.getInt(1);
                fila[1]=RS.getString(2);
                fila[2]=RS.getInt(3);
                fila[3]=RS.getInt(4);
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
