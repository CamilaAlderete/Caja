/*

	Eric Ruiz Diaz
	Luis Pereira
    G16

	Trabajo hecho con la participacion y cooperacion de G03

*/
import java.util.Arrays;
 
public class arregloCircular {
 
  public static int pasos = 0;
  
  public static <T extends Comparable<T>> int busqBin( T[] items, T item )
  {
      pasos = 0;
	  return  busqBin( items, item, 0, items.length-1 );
	  
 }
   
  public static <T extends Comparable<T>> int busqBin( T[] items, T key, int low, int high )
  {
      if ( key == null ){
          return -1;
	  }
      if( low > high  ){
          return -1;
	  }
	  pasos++;
	 
      int mid = low+(high-low)/2;
		
      if( key.compareTo( items[mid] ) > 0 ){//si es mayot
       if((items[mid].compareTo(items[high]) <= 0) && key.compareTo(items[high]) > 0){//si el medio es menor al igual y la llave mayor al alto
          return busqBin( items, key, low, mid-1 );//busqueda binaria del inicio al medio
	   }else{
		  return busqBin(items, key, mid+1, high);//sino busqueda binaria del medio al fin
	   }
	  }
	  else if( key.compareTo( items[mid] ) < 0 ){//si es menor
		if((items[low].compareTo(items[mid]) <= 0) && items[low].compareTo(key) > 0){//si el inicio es menor o igual al medeio y el bajo es mayor a la llave
			return busqBin(items, key, mid+1, high);//busqueda bianria del medio al fin
		}else{
			return busqBin( items, key, low, mid-1 );//si no busqueda binaria del inicio al medio
		}
	  }else{
		return mid;
	  }
	  
  }  
 
	public static void main(String[] args) {
 
		Integer[] items = {24, 30, 35, 42, 56, 58, 60, 67, 70, 75, 87, 94, 99, 1, 2, 5, 10, 12, 14, 17, 20};
		Integer[] prueba = {250, -276, 23 , 35, 60 , 87 , 95, 12, 17, 18 };
		
		System.out.println("\nEl vector circular es :\n" + Arrays.toString(items));
		
		int indice = 0;
		
		for(int i = 0; i< prueba.length; i++ ){
			indice = busqBin(items, Integer.valueOf(prueba[i]));
			System.out.println("\nLa clave " + prueba[i] + " se encontro en el indice " + indice + " en " + pasos + " pasos");
		}
	}
}


