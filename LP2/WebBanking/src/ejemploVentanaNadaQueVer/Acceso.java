/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploVentana;

/**
 *
 * @author alder
 */
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Acceso{

	public static void main(String[] args) {
	  Ventana textFieldFrame = new Ventana(); 
            textFieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            textFieldFrame.setSize(200, 200);//tamanio de la ventana 
            textFieldFrame.setVisible(true); 
            textFieldFrame.setLocation(450,200);
	}


}

class Ventana extends JFrame 
{
    private JLabel etiquetaNrocuenta,etiquetaContrasena;
    private JTextField campoNroCuenta;
    private JPasswordField campoContrasena;
    /*
   private JTextField campoTexto1; //1er sumando
   private JTextField campoTexto2; // 2do sumando
   private JTextField campoTexto3; // resultado
    */
   public Ventana(){
      super("Acceso");
      setLayout(new FlowLayout());
      etiquetaNrocuenta = new JLabel();
      etiquetaNrocuenta.setText("Nro de cuenta");
       add(etiquetaNrocuenta);
      campoNroCuenta = new JTextField("", WIDTH);
       add(campoNroCuenta);
     
      /*campoTexto1 = new JTextField("0.0",5); //crea campo 1
      add(campoTexto1);//agrega campo en ventana 

      String valor1 = campoTexto1.getText();
      //no se puede pasar directo de JTextfield a float, debe pasarse primero a cadena 
      
     
      campoTexto2 = new JTextField("0.0",5);//crea campo 2
      add(campoTexto2); // agrega campo a la ventanita

      String valor2 = campoTexto2.getText();
      //no se puede pasar directo de JTextfield a float, debe pasarse primero a cadena 
      
      
      //suma de los valores
      Float suma = Float.parseFloat(valor1) + Float.parseFloat(valor2);
      
     
      campoTexto3 = new JTextField(suma.toString(),5);//crea campo 3 con resultado de la suma;

      campoTexto3.setEditable(false); // deshabilita edicion
      add(campoTexto3); // agrega el campo en la ventana

      ManejadorCampoTexto handler = new ManejadorCampoTexto();
      campoTexto1.addActionListener(handler);
      campoTexto2.addActionListener(handler);
      campoTexto3.addActionListener(handler);*/
    
   } 


  /*private class ManejadorCampoTexto implements ActionListener {
   
    @Override
    public void actionPerformed(ActionEvent evento){
       
       Float suma;      String cadena = "";
       
        // el usuario oprimió Intro en el objeto JTextField campoTexto2
        if(evento.getSource() == campoTexto2){
        	//se realiza suma
	        suma = Float.parseFloat( campoTexto1.getText() ) + Float.parseFloat( campoTexto2.getText() );
      		campoTexto3.setText(suma.toString());//se actualiza el campo con el resultado de la suma

	    
    	}     
    }

   } */
} 
