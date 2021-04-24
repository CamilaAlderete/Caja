/*
	Eric Ruiz Diaz
	Luis Pereira
	G16
*/

class Nodo<K extends Comparable,D extends Comparable> {
	public K key;
	public D dato;
	public Nodo izq, der;
}

public class ArbolBinario<K extends Comparable,D extends Comparable> {
	private Nodo<K,D> raiz;

	ArbolBinario() {
	 	raiz = null;
	}


	private boolean vacio(){
	    boolean resp = false;
	    if(this.raiz == null){
	      resp = true;
	    }
	    return resp;
	  }
	public void agregar(K clave, D dato){  
	    Nodo<K,D> mov = this.raiz;        //1
	    Nodo<K,D> nuevo = new Nodo<K,D>();
	    nuevo.key=clave;
	    nuevo.dato=dato;
	    int comparacion;

	    if(!this.vacio()){
	      while(mov.izq!=null || mov.der!=null){

	        comparacion = dato.compareTo (mov.dato);
	        if( comparacion > 0 && mov.der!=null){       
	          mov=mov.der;
	        }else if (comparacion < 0 && mov.izq!=null){
	          mov=mov.izq;
	        }else{
	          break;
	        }
	      }  
	      
	      comparacion = dato.compareTo (mov.dato);
	      //nuevo.padre = mov;
	      if(comparacion > 0){
	          mov.der=nuevo;
	      }else{
	          mov.izq=nuevo;
	      }  
	    }else{
	      this.raiz = nuevo;
	    }
	  
	}

	/*public void imprimir(){
		imprimir_priv(this.raiz);
	}

	private void imprimir_priv(Nodo nod){
		if(nod!=null){
			this.imprimir_priv(nod.izq);
			System.out.println("La key es:"+nod.key+" y el dato es "+nod.dato);
			this.imprimir_priv(nod.der);	
		}
	}*/
	
	D buscar(K clave) {
    	Nodo<K,D> t = this.raiz;
	    while( t != null ) {
	      if( clave.compareTo( t.key ) < 0 ){
	      	//System.out.println("Izquierda de "+t.key);
	        t = t.izq;
	      }else if( clave.compareTo( t.key ) > 0 ){
	      	//System.out.println("Derecha de "+t.key);
	        //System.out.println("Su izquierda tiene a "+t.izq.key);
	        t = t.der;
	      }else{
	      	//System.out.println("Te encontre cabron "+t.key);
	        break;
	      }
	    }
	    if(t!=null){
	    	return t.dato;
	    }else{
	    	return null;
	    }
	    
	}
	public static void main (String [] args) {
		ArbolBinario<Integer,Integer> arbol = new ArbolBinario<Integer,Integer>();
		arbol.agregar(1,1);
		arbol.agregar(2,3);
		arbol.agregar(3,2);
		arbol.agregar(4,8);
		arbol.agregar(5,4);
		arbol.agregar(6,6);
		arbol.agregar(7,0);
		//arbol.imprimir();
		Integer encontrado = arbol.buscar(4);
		System.out.println(encontrado);
	}
}
