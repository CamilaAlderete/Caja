//Este trabajo fue realizado por Eric Ruiz Diaz y Luis Pereira G16
//Con colaboracion de G03 Camila Alderete

 import java.util.Arrays;
class DYV_MERGE <T extends Comparable<Integer>>{
     
   
   static DYV_MERGE<Integer> gen;
   //Metodo que llama a la funcion mergesort que devuelve el vector
   //ordenado y la cantidad de inversiones
    int mergeSort(Integer[] lista , int tam){
		
        Integer[] temp = new Integer[tam];
        return this.privMergeSort(lista, temp, 0, tam - 1);
    }
      
    //Funcion que realiza el mergesort y retorna el conteo de inversiones
    int privMergeSort(Integer[] lista,Integer[] temp, int izq, int der){
     
	 int medio, inv_cont = 0;
      if (der > izq) {
        //divide
        medio = (der + izq)/2;
      
        // inv_cont sera la suma de inversiones de la parte derecha, izquierda 
		//y de las inversiones cuando se realiza el merge
        inv_cont  = privMergeSort(lista, temp, izq, medio);
        inv_cont += privMergeSort(lista, temp, medio+1, der);
      
        //Vencer
        inv_cont += this.merge(lista, temp, izq, medio+1, der);
      }
      return inv_cont;
    }
      
  //Funcion para hacer merge de dos partes y retorna la cantidad de inversiones ocurridas durante el merge
    int merge(Integer[] lista, Integer[] temp, int izq, int medio, int der) {
      
	  int i, j, k;
      int inv_cont = 0;
      
      i = izq; //i es el indice del subarray izquierdo
      j = medio; //j es el indice del subarray derecho
      k = izq; // k es el indice del array luego del merge
      while ((i <= medio - 1) && (j <= der))
      {
        if (lista[i].compareTo(lista[j]) <= 0)
        {
          temp[k++] = lista[i++];
        }
        else
        {
          temp[k++] = lista[j++];

          inv_cont = inv_cont + (medio - i);
        }
      }
      

      while (i <= medio - 1)
        temp[k++] = lista[i++];

      while (j <= der)
        temp[k++] = lista[j++];
      
      for (i=izq; i <= der; i++)
        lista[i] = temp[i];
      
      return inv_cont;
    }
  
    public static void main(String[] args) {
		
		gen = new DYV_MERGE<Integer>();
		Integer[] lista = new Integer[]{3,2,1};
        System.out.println("El numero de inversiones para el vector: " +Arrays.toString(lista)+" es: " + gen.mergeSort(lista, lista.length));
		lista = new Integer[]{6,5,4,3,2,1};
		System.out.println("El numero de inversiones para el vector: " +Arrays.toString(lista)+" es: " + gen.mergeSort(lista, lista.length));
		
    }
}
