/*
    Eric Ruiz Diaz
    Luis Pereira
    G16
*/
public class BST
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
   
   private static int encontrado = 1; 

   /* Agregar un dato al arbol */ 
   public void agregar (Comparable <Integer> dato)
   {
      raiz = priv_agregar (raiz, dato);
   }

   private NodoBST priv_agregar (NodoBST n_actual, Comparable <Integer> dato)
   {
        if ( n_actual == null ) {
            return ( new NodoBST(dato) );
        }

        int comparacion = dato.compareTo ((Integer) n_actual.dato);
	 
	if ( comparacion < 0 ) { 
            n_actual.izq = priv_agregar(n_actual.izq,dato);
        } else {
            n_actual.der = priv_agregar(n_actual.der,dato);
        }
		 
	return n_actual;
	 
   }
   
   
    private static int arboles_iguales(NodoBST raiz1,NodoBST raiz2){
        int ans = 0;
        if(raiz1!=raiz2){
            encontrado = 1;
            valores_iguales(raiz1,raiz2,1);
            if(encontrado == 1){
              valores_iguales(raiz2,raiz1,1);
            }
            if (encontrado == 1){
                estructuras_iguales(raiz1,raiz2);
                if (encontrado == 1){
                    ans = 1;
                }else{
                    ans = 2;
                }
            }else{
                ans = 3;
            }
        }
        return ans;
    }
   
    public static Comparable <Integer> buscar (Comparable <Integer> dato, NodoBST raiz_aux){
   
      NodoBST nodo = priv_buscar(dato,raiz_aux);
	  if ( nodo != null ) {
              return nodo.dato;
      } else { /* Reemplazar por manejo de excepcion!! */
	     //System.out.println("No existe en el arbol!!! " + dato);
         return null;	  
	  }	 
   }

    private static NodoBST priv_buscar(Comparable <Integer> dato, NodoBST n_actual){
        
        if ( n_actual == null ) {      // dato no se encuentra en el arbol
            return null;
        }

        int comparacion = dato.compareTo ((Integer) n_actual.dato);

	if ( comparacion == 0 ) {      // dato == n_actual.dato
            return n_actual;
        } else if ( comparacion < 0 ) {  // dato < n_actual.dato, puede estar a la izquierda
            return priv_buscar(dato,n_actual.izq);
        } else {
          // dato > n_actual.dato, puede estar a la derecha
            return priv_buscar(dato,n_actual.der);
        }
	      
   }
   
   private static void valores_iguales (NodoBST n_actual,NodoBST raiz_arbol,int control){
        if (control != 0){
            if ( n_actual != null ){
                control = control * priv_valores_iguales (n_actual,raiz_arbol);
                valores_iguales(n_actual.izq,raiz_arbol,control);
                valores_iguales(n_actual.der,raiz_arbol,control);
            }       
        }else{
            encontrado = 0;
        }
   }
   private static int priv_valores_iguales(NodoBST nodo1,NodoBST raiz_arbol){
        Integer k = (Integer) buscar(nodo1.dato, raiz_arbol);
        if(k != null){
            return 1;
        }else{
            return 0;
        }
   }

   public static void estructuras_iguales(NodoBST nodo1, NodoBST nodo2) {
       if((nodo1 != null) || (nodo2 != null)){
            if(nodo1.dato.equals(nodo2.dato)){
                estructuras_iguales(nodo1.izq,nodo2.izq);
                estructuras_iguales(nodo1.der,nodo2.der);     
            }else{
                encontrado = 0;
            }
       }    
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

   /* Imprime en in-orden */
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
   
   
   public static void respuesta(int caso){
       switch(caso){
              case 1:
                  System.out.println("Los dos arboles son iguales en valores y forma");
                  break;
              case 2:
                  System.out.println("Los valores de los arboles son iguales pero su forma es diferente");
                  break;
              case 3:
                  System.out.println("Los dos arboles son distintos en forma y en valores");
                  break;
              default:
                   System.out.println("Error: se paso la misma referencia");
                  break;
          }
   }
   
   
   public static void main ( String [] args ) {
      BST t = new BST();
      BST t2 = new BST();
      BST t3 = new BST();
      BST t4 = new BST();
      
	  Integer [] A = { 10, 15, 7, 8, 6, 2, 11, 12 };
	  
          for ( int k = 0; k < A.length; k++ ){
	    t.agregar( A[k]);
            t2.agregar( A[k]);
          }
          for ( int k = A.length-1; k > -1; k-- ){
	    t3.agregar( A[k]);
          }
          for ( int k = 1; k < A.length - 2; k++ ){
	    t4.agregar( A[k]);
          }
         
         System.out.println("El arbol 1:");
	 t.imprimir();
         System.out.println();
         System.out.println("El arbol 2:");
         t2.imprimir();
         System.out.println();
         System.out.println("El arbol 3:");
         t3.imprimir();
         System.out.println();
         System.out.println("El arbol 4:");
         t4.imprimir();
         System.out.println();
         
         
          
          int caso1 = arboles_iguales(t.raiz,t2.raiz);
          System.out.println("Arbol 1 y 2");
          respuesta(caso1);
          System.out.println();
          
          int caso2 = arboles_iguales(t.raiz,t3.raiz);
          System.out.println("Arbol 1 y 3");
          respuesta(caso2);
          System.out.println();
          
          int caso3 = arboles_iguales(t.raiz,t4.raiz);
          System.out.println("Arbol 1 y 4");
          respuesta(caso3);
          System.out.println();
          
          int caso4 = arboles_iguales(t.raiz,t.raiz);
          System.out.println("Arbol 1 y 1");
          respuesta(caso4);
          System.out.println();
          
   }
}