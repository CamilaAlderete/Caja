/*
	Eric Ruiz Diaz
	Ruben Izembrandt
	G09
*/
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class tablaHashLin
{
	private String[] tabla;
	
	/* Constructor */
	public tablaHashLin(int tamTabla)
    {
        tabla = new String[ sigPrimo(tamTabla) ];

    }

	public void crear(){
		for(int i = 0; i< tabla.length; i++){
			tabla[i] = "0";
		}
	}
	public void insertar(String val){
		 int pos = myhash(val);
		 int placed = 0;
		 while(pos < tabla.length && placed == 0){
			if (tabla[pos].equals("0")){
				tabla[pos] = val;
				placed = 1;
			}else if(pos == tabla.length - 1){
				pos = -1;
			}
			pos++; 
		 }
	}
	public void buscar(String val){
		int encontrado = 0;
		int pos = myhash(val); 
		 while(pos < tabla.length && encontrado == 0){
			if (tabla[pos].equals(val)){
				encontrado = 1;
			}else if(tabla[pos].equals("0")){
				encontrado = 2;
			}if(pos == tabla.length - 1){
				pos = -1;
			}
			pos++; 
		 }
	}
	
	public void eliminar(String val){
		int eliminado = 0;
		int pos = myhash(val); 
		 while(pos < tabla.length && eliminado == 0){
			if (tabla[pos].equals(val)){
				tabla[pos] = "0";
				if (pos == tabla.length - 1){
					pos = -1;
				}
				if(!(tabla[pos+1].equals("0"))){
					fix(pos+1,tabla[pos+1]);
				}
			}else if(tabla[pos].equals("0")){
				eliminado = 2;
			}else if(pos == tabla.length - 1){
				pos = -1;
			}
			pos++; 
		 }
	}
	
	private void fix(int pos, String val){
		tabla[pos] = "0";
		insertar(val);
		if (pos == tabla.length - 1){
			pos = -1;
		}
		if (!(tabla[pos+1].equals("0"))){
			fix(pos+1,tabla[pos+1]);
		}
	}
	 private int myhash(String x )
    {
        int hashVal = x.hashCode( );
        hashVal %= tabla.length;
        if (hashVal < 0) {
            hashVal += tabla.length;
        }
        return hashVal;
    }
    /* Funcion para generar un numero primo >= n */
    private static int sigPrimo( int n )
    {
        if (n % 2 == 0) {
            n++;
        }
        for ( ; !esPrimo( n ); n += 2);
 
        return n;
    }
    /* Funcion para ver si un numero es primo*/
    private static boolean esPrimo( int n )
    {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }
	public void printTabla(){
		for(int i = 0; i< tabla.length; i++){
		System.out.println("Cubeta " + i + " :  "+tabla[i]); 
		}
		System.out.println();
	}
}
public class LinearTable{
	public static void main(String[] args)  throws IOException
    {
		String s[];              
            
        String cadena;
        String archivo="C:\\Users\\Ruben\\Desktop\\es_ES.dic";
            
        FileReader archivo_profesor = new FileReader(archivo);
        BufferedReader abierto = new BufferedReader(archivo_profesor);    
		
		tablaHashLin lt = new tablaHashLin(2000);
		Scanner scan = new Scanner(System.in);
		
		int i = 0;
		long t1,t2;
		long t_cl_ins, t_cl_eli, t_cl_busq;		
		
		lt.crear();
		
		/*Calculo de cuanto tiempo tarda insertar */
		t1 = System.currentTimeMillis();
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			lt.insertar(s[0]);	
			i++;
		}		
		t2 = System.currentTimeMillis();
		t_cl_ins = t2 - t1;
		
		abierto.close();
		abierto = new BufferedReader(new FileReader(archivo)); 
		
		/* Calculo de cuanto tiempo tarda en buscar*/
		t1 = System.currentTimeMillis();
		i = 0;
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			lt.buscar(s[0]);
			i++;
		}		
		t2 = System.currentTimeMillis();
        t_cl_busq = t2 - t1;
		
		abierto.close();
		abierto = new BufferedReader(new FileReader(archivo));  
		
		/* Calculo de cuanto tiempo tarda eliminar*/
		t1 = System.currentTimeMillis();
		i = 0;
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			lt.eliminar(s[0]);	
			i++;
		}		
		t2 = System.currentTimeMillis();
		t_cl_eli = t2 - t1;
		
		abierto.close();
		abierto = new BufferedReader(new FileReader(archivo)); 
		i = 0;
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			lt.insertar(s[0]);
			i++;
		}
		abierto.close();

		System.out.println("T(ms) Insercion\t\tT(ms) Eliminacion\tT(ms) Busqueda");
		System.out.println("\t" + t_cl_ins + "\t\t\t" + t_cl_eli + "\t\t\t"+t_cl_busq);
		
		
		int opcion;
		do    
        {
            System.out.println("\nOperaciones de la tabla Hash\n");
            System.out.println("1. insertar elemento ");
            System.out.println("2. eliminar elemento");
			System.out.println("3. imprimir en consola la tabla Hash");
			System.out.println("4. terminar programa");
			
			opcion = scan.nextInt(); 
			scan.nextLine();
            
			switch (opcion)
            {
            case 1 : 
                System.out.println("Ingrese elemento a insertar");
                lt.insertar( scan.nextLine() ); 
                break;                          
            case 2 :                 
                System.out.println("Ingrese elemento a eliminar");
                lt.eliminar( scan.nextLine() ); 
                break;                        
			case 3:
				lt.printTabla();
				break;
			case 4:
				break;
            default : 
                System.out.println("Entrada erronea \n ");
                break;   
            }                
		
		}while(opcion!= 4);
	}
}