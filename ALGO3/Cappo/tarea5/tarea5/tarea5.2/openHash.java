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
/* Nodo para lista enlazada simple */
class nodoLista
{
    nodoLista sig;
    String dato;
 
    /* Constructor */
    public nodoLista(String x)
    {
        dato = x;
        sig = null;
    }
}
 
/* Class tablaHash */
class tablaHash
{
    private nodoLista[] tabla;
 
    /* Constructor */
    public tablaHash(int tamTabla)
    {
        tabla = new nodoLista[ sigPrimo(tamTabla) ];

    }

    /* Funcion to insertar un elemento */
    public void insertar(String val)
    {
        int pos = myhash(val);        
        nodoLista nptr = new nodoLista(val);                
        if (tabla[pos] == null)
            tabla[pos] = nptr;            
        else
        {
            nptr.sig = tabla[pos];
            tabla[pos] = nptr;
        }    
    }
	public void buscar(String val){
		int encontrado = 0;
		int pos = myhash(val); 
		nodoLista start = tabla[pos];
		nodoLista end = start;
		if (start!= null){
			if (start.dato.equals(val)){
				encontrado = 1;
			}else{
				while(end.sig!= null){
					end = end.sig;
					if(end.dato.equals(val)){
						encontrado = 1;
					}
				}
			}
		}
	}
    /* Funccion para eliminar un elemento */
    public void eliminar(String val)
    {
        int pos = myhash(val);    
        nodoLista start = tabla[pos];
        nodoLista end = start;
        if (start.dato.equals(val))
        {
            tabla[pos] = start.sig;
            return;
        }
        while (end.sig != null && !(end.sig.dato.equals(val)))
            end = end.sig;
        if (end.sig == null)
        {
            System.out.println("\nElemento no encontrado\n");
            return;
        }
        if (end.sig.sig == null)
        {
            end.sig = null;
            return;
        }
        end.sig = end.sig.sig;
        tabla[pos] = start;
    }
    /* Funcion myhash */
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
    public void printHashTable ()
    {
        System.out.println();
        for (int i = 0; i < tabla.length; i++)
        {
            System.out.print ("Cubeta " + i + ":  ");             
            nodoLista start = tabla[i];
            while(start != null)
            {
                System.out.print(start.dato +" ");
                start = start.sig;
            }
            System.out.println();
        }
    }   
}
 
/* Class openHash*/
public class openHash
{ 
    public static void main(String[] args)  throws IOException
    {
		
		String s[];              
            
        String cadena;
        String archivo="C:\\Users\\Ruben\\Desktop\\es_ES.dic";
            
        FileReader archivo_profesor = new FileReader(archivo);
        BufferedReader abierto = new BufferedReader(archivo_profesor);    
        Scanner scan = new Scanner(System.in);
	
		tablaHash th = new tablaHash(2000);
		int finish = 0;
        char ch;
		int i = 0;
		long t1,t2;
		long t_a_ins, t_a_eli, t_a_busq;
		
        /* Calculo de cuanto tiempo tarda insertar */
		t1 = System.currentTimeMillis();
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			th.insertar(s[0]);	
			i++;
		}		
		t2 = System.currentTimeMillis();
		t_a_ins = t2 - t1;
		
		abierto.close();
		abierto = new BufferedReader(new FileReader(archivo));    
		
		/* Calculo de cuanto tiempo tarda en buscar*/
		t1 = System.currentTimeMillis();
		i = 0;
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			th.buscar(s[0]);
			i++;
		}		
		t2 = System.currentTimeMillis();
        t_a_busq = t2 - t1;
		
		abierto.close();
		abierto = new BufferedReader(new FileReader(archivo));    
				
		/* Calculo de cuanto tiempo tarda eliminar*/
		t1 = System.currentTimeMillis();
		i = 0;
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			th.eliminar(s[0]);	
			i++;
		}		
		t2 = System.currentTimeMillis();
		t_a_eli = t2 - t1;
		abierto.close();
		abierto = new BufferedReader(new FileReader(archivo));   
		i = 0;
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			th.insertar(s[0]);	
			i++;
		}	
		abierto.close();		
		System.out.println("T(ms) Insercion\t\tT(ms) Eliminacion\tT(ms) Busqueda");
		System.out.println("\t" + t_a_ins + "\t\t\t" + t_a_eli + "\t\t\t"+t_a_busq);
		
        do    
        {
            System.out.println("\nOperaciones de la tabla Hash\n");
            System.out.println("1. insertar elemento ");
            System.out.println("2. eliminar elemento");
			System.out.println("3. imprimir en consola la tabla Hash");
			System.out.println("4. terminar programa");
 
            int choice = scan.nextInt(); 
			scan.nextLine();
            switch (choice)
            {
            case 1 : 
                System.out.println("Ingrese elemento a insertar");
                th.insertar( scan.nextLine() ); 
                break;                          
            case 2 :                 
                System.out.println("Ingrese elemento a eliminar");
                th.eliminar( scan.nextLine() ); 
                break;                        
			case 3:
				th.printHashTable();
				break;
			case 4:
				finish = 1;
				break;
            default : 
                System.out.println("Entrada erronea \n ");
                break;   
            }                          
        } while (finish == 0);  
    }
}