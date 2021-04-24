/*
	G16
	Luis Pereira
	Eric Ruiz Diaz
	
	Trabajo hecho con la participacion y cooperacion de G03
*/
import java.util.Scanner;
import java.io.*;

public class Main3 {

  public static void main(String args[])throws IOException{
  		
  		System.out.println("Prueba con distintos conjuntos de 8 puntos");
  		Scanner leer=new Scanner(System.in);
  		leer.nextLine();
		
		//varios conjuntos de puntos
		String []variosArchivos = new String[3];
		variosArchivos[0] = "puntos.txt";
		variosArchivos[1] = "puntos1.txt";
		variosArchivos[2] = "puntos2.txt";

      	//prueba de lista con ocho puntos
  		Puntos1 [] lista =new Puntos1[8];
  		
	  	
	  	int k=0,t=k+1; 
	  	//lectura de puntos desde archivo
	  	while(k< variosArchivos.length){	

	      System.out.println("Conjunto de puntos "+t);

	      String cadena, s[];
	      //String archivo = "puntos.txt";
	      String archivo = variosArchivos[k];
	      BufferedReader abiertoLectura = new BufferedReader(new FileReader(archivo));
	      int i=0;
	      int x,y;
	      
	      while((cadena = abiertoLectura.readLine())!=null) {
	          s=cadena.split(" ");

	          //paso de puntos a lista
	          x= (int ) Integer.parseInt(s[0]);
	          y= (int ) Integer.parseInt(s[1]);
	          lista[i] = new Puntos1(x,y);  
	         
	          i++;
	      }

	      abiertoLectura.close();

	      //contiene metodos para busqueda, visualizacion...
	      Contenedor m = new Contenedor();

	      System.out.println("Original");
	      m.print(lista);

	      //ordenacion ascendente con respecto a x y descendente con respecto a y
	  		GenericQuicksortComparable.<Puntos1>qsort(lista, 0, lista.length-1);
	      
	      System.out.println("Ordenado");
	      m.print(lista);
	      

	      //lista de puntos dominantes "parcial"
	      Puntos1 [] pParcial = new Puntos1[lista.length];
	      m.inicializar(pParcial);

	      //busqueda de puntos dominantes
	      m.busqueda(lista,pParcial);

	      //visualizacion de puntos dominantes  
	      System.out.println("Puntos maximos");
	      for (Puntos1 obj: pParcial ) {
	        if (obj!=null) {
	          if((obj.x!=0) && (obj.y!=0)){ 
	            System.out.println(" "+obj.x+"\t"+obj.y);  
	          }
	        }
	        
	      }

	      k++; t++;
	    } 
  }

}

class Contenedor{
  //metodo para busqueda de punto dominante
  public static void busqueda(Puntos1 arr[],Puntos1 pParcial[]){
    
    int pos=0;//posicion en pParcial
    for ( int i=0; i< arr.length; i++) {
      
      //punto en arr[i] es dominante (x mayor, y mayor)
      if ( (arr[i].x > pParcial[pos].x) && (arr[i].y > pParcial[pos].y)) {
        pParcial[pos]= arr[i];

        //puede que al guardar un nuevo punto, este sea dominante a todos los anteriormente insertados
        int k=0;
        while(pos > 0){

	        if ( (pParcial[pos].x > pParcial[pos-1].x) && (pParcial[pos].y > pParcial[pos-1].y )) {
	          
	          pParcial[pos-1]=pParcial[pos];
	          pParcial[pos] = new Puntos1(0,0);
	          pos--;
	          
	        }else{
	          k=1;
	          break;
	        }
        }

        if (k==1) {
        	pos++;
        }

      //punto arr[i] no dominante, ni dominado (x mayor, y menor) 
      }else if((arr[i].x > pParcial[pos].x) && (arr[i].y < pParcial[pos].y)){
        pos++;//avanzo una posicion
        pParcial[pos]= arr[i];//guardo punto
      }    

    }    
   
  }

  //metodo para inicializar lista
  public static void inicializar(Puntos1 arr[]){
    for (int i=0 ;i<arr.length;i++) {
      arr[i] = new Puntos1(0,0);
    }
  }

  //metodo para visualizar lista
  public static void print(Puntos1 arr[]){
    for (Puntos1 obj : arr ) {
      System.out.println(" "+obj.x+"\t "+obj.y);
    }
    System.out.println();
  }

}