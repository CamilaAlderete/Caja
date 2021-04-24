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

class datoDobleHash
{
    int estado;
    String dato;

    /* Constructor */
    public datoDobleHash(String x, int state)
    {
        dato = x;
        estado = state;
    }
}

class tablaDobleHash
{
	private datoDobleHash[] tabla;

	/* Constructor */
	public tablaDobleHash(int tamTabla)
    {
        tabla = new datoDobleHash[ sigPrimo(tamTabla) ];

    }

	public void crear(){
		datoDobleHash aux = new datoDobleHash("0",0);
		for(int i = 0; i< tabla.length; i++){
			tabla[i] = aux;
		}
	}
	public void insertar(String val){
		 int pos = myhash(val);
		 int i = 0;
		 int weish = weiss_hash(val);
		 int placed = 0;
		 while(placed == 0){
			if(pos > tabla.length - 1){
				pos = pos % tabla.length;
			}if(tabla[pos].dato.equals("0")){
				datoDobleHash aux = new datoDobleHash(val,1);
				tabla[pos] = aux;
				placed = 1;
			}
			i++;
			pos = pos + (weish*i);
		 }
	}
	public void buscar(String val){
		int encontrado = 0;
		int i = 1;
		int pos = myhash(val);
		int weish = weiss_hash(val);
		 while(encontrado == 0){
			 if(pos >tabla.length - 1){
				pos = pos%tabla.length;
			}
			if (tabla[pos].dato.equals(val)){
				encontrado = 1;
			}else if(tabla[pos].estado == 0){
				encontrado = 2;
			}
			pos = pos + (weish*i);
			i++;
		 }
	}

	public void eliminar(String val){
		int eliminado = 0;
		int i = 1;
		int pos = myhash(val);
		int weish = weiss_hash(val);
		 while(eliminado == 0){
			if(pos > tabla.length - 1){
				pos = pos%tabla.length;
			}
			if (tabla[pos].dato.equals(val)){
				datoDobleHash aux = new datoDobleHash("0",2);
				tabla[pos] = aux;
			}else if(tabla[pos].estado == 0){
				eliminado = 2;
			}
			pos = pos + (weish*i);
			i++;
		 }
	}
	//FUNCION SACADA DE LA FIGURA 20.3 SACADA DE LA 4TA EDICION DEL LIBRO DE WEISS
	private int weiss_hash(String key){
		int hash_value = 0;
		for(int k=1;k<key.length();k++){
			hash_value += key.charAt(k);
		}
		return(hash_value%tabla.length);
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
public class DobleHashing{
	public static void main(String[] args)  throws IOException
    {
		String s[];

        String cadena;
        String archivo="C:\\Users\\Ruben\\Desktop\\es_ES.dic";

        FileReader archivo_profesor = new FileReader(archivo);
        BufferedReader abierto = new BufferedReader(archivo_profesor);
        Scanner scan = new Scanner(System.in);
		tablaDobleHash dh = new tablaDobleHash(2000);

		dh.crear();
		int i = 0;
		long t1,t2;
		long t_cdh_ins, t_cdh_eli, t_cdh_busq;

		t1 = System.currentTimeMillis();
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			dh.insertar(s[0]);
			i++;
		}
		t2 = System.currentTimeMillis();
		t_cdh_ins = t2 - t1;

		abierto.close();
		abierto = new BufferedReader(new FileReader(archivo));

		/* Calculo de cuanto tiempo tarda en buscar*/
		t1 = System.currentTimeMillis();
		i = 0;
		while((cadena = abierto.readLine())!=null && i<1000) {
			s=cadena.split("/");
			dh.buscar(s[0]);
			i++;
		}
		t2 = System.currentTimeMillis();
        t_cdh_busq = t2 - t1;

		abierto.close();
		abierto = new BufferedReader(new FileReader(archivo));

		/* Calculo de cuanto tiempo tarda eliminar	*/
		t1 = System.currentTimeMillis();
		i = 0;
		while((cadena = abierto.readLine())!=null && i<400) {
			s=cadena.split("/");
			dh.eliminar(s[0]);
			i++;
		}
		t2 = System.currentTimeMillis();
		t_cdh_eli = t2 - t1;

		abierto.close();

		System.out.println("T(ms) Insercion\t\tT(ms) Eliminacion\tT(ms) Busqueda");
		System.out.println("\t" + t_cdh_ins + "\t\t\t" + t_cdh_eli + "\t\t\t"+t_cdh_busq);


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
                dh.insertar( scan.nextLine() );
                break;
            case 2 :
                System.out.println("Ingrese elemento a eliminar");
                dh.eliminar( scan.nextLine() );
                break;
			case 3:
				dh.printTabla();
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
