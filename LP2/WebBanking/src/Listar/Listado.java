/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listar;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Listado extends JFrame {
  
  public Listado(DefaultTableModel model) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTable table = new JTable(model);
    getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    pack();
  }
}

/* forma de llamar : 
    new Listado(tablaDatos).setVisible(true);
*/

/* mostrar en terminal:
for(int row = 0; row < datos1.getRowCount();row++) {
            for(int col = 0;col < datos1.getColumnCount();col++) {
		System.out.println(datos1.getValueAt(row, col));
            }
            System.out.println("\n");
 }
*/

