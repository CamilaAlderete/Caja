/*
	Integrantes:
	G16 - Luis Pereira y Eric Ruiz Diaz

	Con la participacion y creditos respectivos a G03 Camila Alderete

*/
public class BST { 
  
   
    class Nodo { 
        int dato; 
        Nodo izq, der; 
  
        public Nodo(int item) { 
            dato = item; 
            izq = der = null; 
        } 
    } 
  
   
    Nodo raiz; 
  
  
    public BST() {  
        raiz = null;  
    } 
  
   
    void insertar(int dato) { 
       raiz = insertar(raiz, dato); 
    } 
      
   
    Nodo insertar(Nodo raiz, int dato) { 
  
       
        if (raiz == null) { 
            raiz = new Nodo(dato); 
            return raiz; 
        } 
  
       
        if (dato < raiz.dato) 
            raiz.izq = insertar(raiz.izq, dato); 
        else if (dato > raiz.dato) 
            raiz.der = insertar(raiz.der, dato); 
  
      
        return raiz; 
    } 

    void busqueda(int dato){
    	busqueda(raiz,dato);
    }

     void busqueda(Nodo raiz, int dato) { 
  
       
        if (raiz != null && raiz.dato == dato) { 
           //System.out.println("Encontrado");
        }else if (raiz ==null) {
        	//System.out.println("No encontrado");
        }else{

	        if (dato < raiz.dato) 
	            busqueda(raiz.izq, dato); 
	        else if (dato > raiz.dato) 
	            busqueda(raiz.der, dato); 
	  

        } 

    } 


  
   
    void inorden()  { 
       inorden(raiz); 
    } 

    void inorden(Nodo raiz) { 
        if (raiz != null) { 
            inorden(raiz.izq); 
            System.out.println(raiz.dato); 
            inorden(raiz.der); 
        } 
    } 
  

    /*public static void main(String[] args) { 
        BST tree = new BST(); 
        tree.insertar(50); 
        tree.insertar(30); 
        tree.insertar(20); 
        tree.insertar(40); 
        tree.insertar(70); 
        tree.insertar(60); 
        tree.insertar(80); 
 		//tree.busqueda(1); 
        // print inorden traversal of the BST 
        tree.inorden(); 
    } */

} 