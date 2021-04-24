/*
	Eric Ruiz Diaz
	Ruben Izembrandt
	G09
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Scanner;





public class Ejercicio1{
    
    
	public static void main(String args[]) throws IOException{
		
            String s[];
            
            int R = 64;
            int hash_value=0;
            
            System.out.println("------R="+R+" ");                
            
            String cadena;
            String archivo="es_ES.dic";
            
            FileReader archivo_profesor = new FileReader(archivo);
            BufferedReader abierto = new BufferedReader(archivo_profesor);
            
            /*FileWriter archivo_conclusion=new FileWriter("salida.txt");
            PrintWriter salida=new PrintWriter(archivo_conclusion);*/
            int i=0;
            
            while((cadena = abierto.readLine())!=null && i<1000) {
                s=cadena.split("/");
                //System.out.println(s[0]);
			for(int k=1;k<s.length;k*=2){
				hash_value=hash_value * R +(s[0].charAt(k-1));
                        /*for(int a=0;a<hash_value%1000;a++){
                            salida.print();
                        }*/
                        
		}
                System.out.println(Math.abs(hash_value%1000));
                //salida.println("\n");
                //Scanner leer=new Scanner(System.in);
                i++;
                //System.out.println("Valor:"+i);
            }
            
            abierto.close();
            //alida.close();

        }       
}
