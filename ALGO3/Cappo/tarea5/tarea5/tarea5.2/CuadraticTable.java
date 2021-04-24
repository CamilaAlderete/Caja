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

class datoCuadratico
{
    int estado;
    String dato;
 
    /* Constructor */
    public datoCuadratico(String x, int state)
    {
        dato = x;
        estado = state;
    }
}
 
class tablaHashCuad{
	private datoCuadratico[] tabla;
	
	/* Constructor */
	public tablaHashCuad(int tamTabla)
    {
        tabla = new datoCuadratico[ sigPrimo(tamTabla) ];

    }

	public void crear(){
		datoCuadratico aux = new datoCuadratico("0",0);
		for(int i = 0; i< tabla.length; i++){
			tabla[i] = aux;
		}
	}
	public void insertar(String val){
		 int pos = myhash(val);
		 int i = 0;
		 int placed = 0;
		 while(placed == 0){
			if(pos >tabla.length - 1){
				pos = pos%tabla.length;
			}
			if(tabla[pos].dato.equals("0")){
				datoCuadratico aux = new datoCuadratico(val,1);
				tabla[pos] = aux;
				placed = 1;
			}

			i++;
			pos = pos + (i*i);

		 }
	}
	public void buscar(String val){
		int encontrado = 0;
		int i = 1;
		int pos = myhash(val); 
		 while(encontrado == 0){
			if(pos >tabla.length - 1){
				pos = pos%tabla.length;
			}
			if (tabla[pos].dato.equals(val)){
				encontrado = 1;
			}else if(tabla[pos].estado == 0){
				encontrado = 2;
			}
			pos = pos + (i*i);
			i++;
		 }
	}
	
	public void eliminar(String val){
		int eliminado = 0;
		int i = 1;
		int pos = myhash(val); 
		 while(eliminado == 0){
			if(pos > tabla.length - 1){
				pos = pos%tabla.length;
			}
			if (tabla[pos].dato.equals(val)){
			datoCuadratico aux = new datoCuadratico("0",2);
			tabla[pos] = aux;
			}else if(tabla[pos].estado == 0){
				eliminado = 2;
			}
			pos = pos + (i*i);
			i++;
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
		System.out.println("Cubeta " + i + " :  "+tabla[i].dato); 
		}
		System.out.println();
	}
}
public class CuadraticTable{
	public static void main(String[] args)  throws IOException
    {
		String s[];              
            
        String cadena;
        String archivo="C:\\Users\\Ruben\\Desktop\\es_ES.dic";
            
        FileReader archivo_profesor = new FileReader(archivo);
        BufferedReader abierto = new BufferedReader(archivo_profesor);    
        Scanner scan = new Scanner(System.in);
		tablaHashCuad lt = new tablaHashCuad(2000);

		int i = 0;
		long t1,t2;
		long t_cc_ins, t_cc_eli, t_cc_busq;
		lt.crear();

		/*Calculo de cuanto tiempo tarda insertar */
		t1 = System.currentTimeMillis();
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			lt.insertar(s[0]);	
			i++;
		}		
		t2 = System.currentTimeMillis();
		t_cc_ins = t2 - t1;
		
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
        t_cc_busq = t2 - t1;
		
		abierto.close();
		abierto = new BufferedReader(new FileReader(archivo));  
		
		/* Calculo de cuanto tiempo tarda eliminar	*/
		t1 = System.currentTimeMillis();
		i = 0;
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			lt.eliminar(s[0]);	
			i++;
		}		
		t2 = System.currentTimeMillis();
		t_cc_eli = t2 - t1;
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
		System.out.println("\t" + t_cc_ins + "\t\t\t" + t_cc_eli + "\t\t\t"+t_cc_busq);
		

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