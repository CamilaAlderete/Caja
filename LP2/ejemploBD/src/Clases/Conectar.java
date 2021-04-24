



package Clases;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 
import javax.swing.JOptionPane;

/**
 *
 * @author pc420
 */
public class Conectar {
    
    private static Connection CON;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/tabla1";

    public Conectar() {
        CON = null;
    }
    
    //retorno de conexion
    public Connection getConnection(){
        try {
            Class.forName(DRIVER);
            CON = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            if (CON != null){
                System.out.println("Conexion Establecida...");
            }
        } catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al conectar con la base de datos", JOptionPane.ERROR_MESSAGE);
            //System.out.println("error al conectar" + e);  
            System.exit(0);
        } 
        return CON;
    }
    
    //desconectar de la bd
    public void desconectar(){
      CON = null;
      System.out.println("Conexion terminada...");
    } 
}
