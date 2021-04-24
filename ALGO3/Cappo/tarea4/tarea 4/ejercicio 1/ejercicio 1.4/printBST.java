/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS III - Seccion TQ - Prof. Cristian Cappo 
 * Ing. Informatica
 *
 * Implementa la clase BST (Arbol Binario de Busqueda)
 * El dato cuya referencia se almacena en cada nodo
 * es un Comparable o derivado.
 *
 * ---------------------------------------
 */
 
 /*
    Eric Ruiz Diaz
    Luis Pereira
    G16
*/

public class printBST
{

   private class NodoBST {
      Comparable <Integer> dato = null;
	  NodoBST izq = null;
	  NodoBST der = null;

	  public NodoBST (Comparable <Integer> dato)
     {
        this.dato = dato;
     }
   }
   
	private NodoBST raiz = null;
	private int prof = 0; 
	private int elTamano = 0;


   public Comparable <Integer> buscar (Comparable <Integer> dato)
   {
      NodoBST nodo = priv_buscar(raiz,dato);
	  if ( nodo != null ) 
	     return nodo.dato;
	  else { 
	     System.out.println("No existe en el arbol!!! " + dato);
         return null;	  
	  }	 
   }
   private NodoBST priv_buscar (NodoBST n_actual, Comparable <Integer> dato)
   {
      if ( n_actual == null )      // dato no se encuentra en el arbol
         return null;

     int comparacion = dato.compareTo ((Integer) n_actual.dato);
	 if ( comparacion == 0 )      // dato == n_actual.dato 
	   return n_actual;
	 else if ( comparacion < 0 )  // dato < n_actual.dato, puede estar a la izquierda
	   return priv_buscar(n_actual.izq,dato);
     else	                      // dato > n_actual.dato, puede estar a la derecha
	   return priv_buscar(n_actual.der,dato);
	      
   }

   
   public void agregar (Comparable <Integer>  dato)
   {
      raiz = priv_agregar (raiz, dato);
   }

   private NodoBST priv_agregar (NodoBST n_actual, Comparable <Integer> dato)
   {
      if ( n_actual == null )
         return ( new NodoBST(dato) );

      int comparacion = dato.compareTo ((Integer) n_actual.dato);
	 
	 if ( comparacion < 0 ) 
	 	n_actual.izq = priv_agregar(n_actual.izq,dato);
	 else
		n_actual.der = priv_agregar(n_actual.der,dato);
		 
	 return n_actual;
	 
   }

    public void imprimir(){
        System.out.printf("PreOrden es: ");
        imprimir_preOrden(raiz);
        System.out.println();
        System.out.printf("InOrden: ");
        imprimir_inOrden(raiz);
        System.out.println();
        System.out.printf("PosOrden es: ");
        imprimir_posOrden(raiz);
        System.out.println();
   }

   
   private void imprimir_preOrden (NodoBST n_actual)
   {
      if ( n_actual != null )
      {
         System.out.printf(n_actual.dato + " "); 
         imprimir_preOrden (n_actual.izq);
         imprimir_preOrden(n_actual.der);
      }
   }
  
   private void imprimir_inOrden (NodoBST n_actual)
   {
      if ( n_actual != null )
      { 
         imprimir_inOrden(n_actual.izq);
         System.out.printf(n_actual.dato + " ");
         imprimir_inOrden(n_actual.der);
      }
   }
   
   private void imprimir_posOrden (NodoBST n_actual)
   {
      if ( n_actual != null )
      { 
         imprimir_posOrden(n_actual.izq);
         imprimir_posOrden(n_actual.der);
         System.out.printf(n_actual.dato + " ");
      }
   }
	
	public void profundidad_nodo(Comparable<Integer> key){
		prof = 0;
		priv_profundidad_nodo(key,this.raiz);
		System.out.println("La profundidad del nodo de valor "+ key + " es " + prof);
	}
	
	
    private void priv_profundidad_nodo (Comparable<Integer> key,NodoBST n_actual){
		if(n_actual.dato != key){
			prof = prof + 1;
			int comparacion = key.compareTo ((Integer) n_actual.dato);
			
			if (comparacion > 0){
				priv_profundidad_nodo(key,n_actual.der);
			}else{
				priv_profundidad_nodo(key,n_actual.izq);
			}
		}
   }

    public void tamano(Comparable <Integer> key)
    {
		NodoBST nodo = priv_buscar(raiz,key);
        System.out.println("El tamano de el nodo "+ key + " es: " + (priv_tamano(nodo.izq) + 1 + priv_tamano(nodo.der)));
    }
 
    /* computes number of nodes in tree */
    private int priv_tamano(NodoBST n_actual)
    {
		if(n_actual == null){
			return 0;
		}else{
            return(priv_tamano(n_actual.izq) + 1 + priv_tamano(n_actual.der));
		}
	}
	
   /*
     Un ejemplo de uso sencillo de uso de la clase BST
   */   
   public static void main ( String [] args ) {
      printBST t = new printBST();
	  Integer [] A = { 10, 15, 7, 8, 6, 2, 11, 12 };
	  for ( int k=0; k < A.length-1; k++ )
	    t.agregar( A[k]);
	
	  t.imprimir();
	  t.profundidad_nodo(2);
	  t.profundidad_nodo(8);
	  t.tamano(10);
	  t.tamano(7);
	  
	  Integer k = (Integer) t.buscar(81);
	  
	  if ( k != null ) 
	     System.out.println("Si existe!!" + k);
	  
   }
}