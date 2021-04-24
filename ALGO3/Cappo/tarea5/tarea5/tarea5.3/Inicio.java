/*
	Eric Ruiz Diaz
	Ruben Izembrandt
	G09
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Inicio{
	public static void main(String args[] )throws IOException{
		int key,i=0,hash_value=0;
		String cadena,s[];
		
		HashLineal tabla_disp=new HashLineal();
		
		String archivo="es_ES.dic";
            
        FileReader archivo_profesor = new FileReader(archivo);
        BufferedReader abierto = new BufferedReader(archivo_profesor);                                 
            
			while((cadena = abierto.readLine())!=null) {
				s=cadena.split("/");
				for(int k=1;k<s.length;k*=2){
					hash_value=hash_value * 32 +(s[0].charAt(k-1));                                        
				}
                key=Math.abs(hash_value%1000);
				
				tabla_disp.insertar(key,s[0]);
				//System.out.println(s[0]);
			}
		abierto.close();
		int factor=HashLineal.elementos/1000;
		float prom=(HashLineal.entradas_exitosa + HashLineal.entradas_no_exitosa)/2;
		System.out.println("Para N=1000 el factor de carga es 0."+factor+" en "+prom/1000);
	}
}