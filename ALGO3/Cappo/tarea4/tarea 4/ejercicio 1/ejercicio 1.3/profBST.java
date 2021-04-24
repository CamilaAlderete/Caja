import java.util.concurrent.ThreadLocalRandom;
/*
    Eric Ruiz Diaz
    Luis Pereira
    G16
*/
public class profBST {
    
    private class NodoBST {
      	int dato;
		NodoBST izq = null;
		NodoBST der = null;

	public NodoBST (int dato){
            this.dato = dato;
        }
    }
   
    private NodoBST raiz = null;
     
    public void cargarArbol (int dato){
        raiz = priv_agregar (raiz, dato);
    }

	  public void agregar (int dato){
      raiz = priv_agregar (raiz, dato);
   }
   private NodoBST priv_agregar (NodoBST n_actual, int dato){
        if ( n_actual == null ) {
            return ( new NodoBST(dato) );
        }
            
        Comparable <Integer> valor = dato;
        int comparacion = valor.compareTo ( n_actual.dato);
	 
		if ( comparacion < 0 ) { 
            n_actual.izq = priv_agregar(n_actual.izq,dato);
        } else {
            n_actual.der = priv_agregar(n_actual.der,dato);
        }
		 
	return n_actual;
	 
   }
      private void recorrerDisOrden (NodoBST n_actual, NodoBST raiz){
      if ( n_actual != null ) { 
         recorrerDisOrden(n_actual.der,  raiz);
         imprimir_nodo(n_actual.dato,raiz);
         recorrerDisOrden(n_actual.izq, raiz);
      }
   }
   
    private void imprimir_nodo (int key,NodoBST n_actual){
		if(n_actual.dato != key){
			System.out.printf("    ");
			if (key > n_actual.dato){
				imprimir_nodo(key,n_actual.der);
			}else{
				imprimir_nodo(key,n_actual.izq);
			}
		}else{
			System.out.println(key);
		}
   }
   
    public static void main ( String [] args ) {
        
            profBST objeto = new profBST();
			
			Integer [] A = { 10, 15, 7, 8, 6, 2, 11, 12 };
	  
			for ( int k = 0; k < A.length; k++ ){
				objeto.agregar( A[k]);
			}
			objeto.recorrerDisOrden(objeto.raiz,objeto.raiz);
          
        }
    
    }
