/*
Grupo G16:
Luis Pereira
Eric RuizDiaz
2019
*/

import java.util.*;

class NodoBST<T> { 
  T dato = null;
  NodoBST<T> izq = null;
  NodoBST<T> der = null;
  NodoBST<T> padre = null;

  public NodoBST (T dato)
  {
    this.dato = dato;
  }
}

class BST <T extends Comparable<T>>
{  
  private NodoBST<T> raiz = null;

  // -------------- a)agregar
  // Agregar un dato al arbol 
  // O(n)
  //Costo espacial: 1
  public void agregar(T dato){  
    NodoBST<T> mov = this.raiz;        //1
    NodoBST<T> nuevo = new NodoBST<T>(dato);

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
      nuevo.padre = mov;
      if(comparacion > 0){
          mov.der=nuevo;
      }else{
          mov.izq=nuevo;
      }  
    }else{
      this.raiz = nuevo;
    }
  
  }

  // -------------- b)eliminar
  //O(n)
  //Costo espacial: 1
  public boolean eliminar(T dato){

    //n
    NodoBST<T> resp = this.buscar(dato);

    NodoBST<T> aux = null;

    boolean borrado = false;

    NodoBST<T> padre = resp.padre;
    if(resp != null){
      /*Caso 1 (Borrar un Nodo sin hijos) :
      El caso más sencillo, lo único que hay que hacer es 
      borrar el nodo y establecer el apuntador de su padre a nulo.*/
      if(resp.izq == null && resp.der == null){
        if(padre == null){
          this.raiz = null;
        }else{
          if(padre.izq == resp){
            padre.izq = null;
            borrado = true;
          }else if(padre.der == resp){
            padre.der = null;
            borrado = true;
          }
        }
          
      /*Caso 2 (Borrar un Nodo con un subárbol hijo) :
      Este caso tampoco es muy complicado, únicamente tenemos 
      que borrar el Nodo y el subárbol que tenía pasa a ocupar su lugar.*/
      }else if(resp.izq == null && resp.der != null){
        if(padre == null){
          this.raiz = resp.der;
        }else{
          if(padre.izq == resp){
            padre.izq = resp.der;
          }else{
            padre.der = resp.der;
          }
        }
        
      }else if(resp.izq != null && resp.der == null){
        if(padre == null){
          this.raiz = resp.izq;
        }else{
          if(padre.izq == resp){
            padre.izq = resp.izq;
          }else{
            padre.der = resp.izq;
          }
        }
      /*Caso 3 (Borrar un Nodo con dos subárboles hijos) :
      Este es un caso algo complejo, tenemos que tomar el 
      hijo derecho del Nodo que queremos eliminar y 
      recorrer hasta el hijo más a la izquierda 
      ( hijo izquierdo y si este tiene hijo izquierdo 
      repetir hasta llegar al último nodo a la izquierda), 
      reemplazamos el valor del nodo que queremos eliminar 
      por el nodo que encontramos ( el hijo más a la izquierda ),
       el nodo que encontramos por ser el más a la izquierda es 
       imposible que tenga nodos a su izquierda pero si que es posible 
       que tenga un subárbol a la derecha, para terminar solo nos queda
        proceder a eliminar este nodo de las formas que conocemos ( caso 1, caso 2 )
         y tendremos la eliminación completa.*/
      }else{

        Stack<NodoBST<T>> pila = new Stack<NodoBST<T>>(); 
        pila.push(resp.der);

        NodoBST<T> temp = resp.der;

        while(!pila.isEmpty()){
          temp = pila.pop(); 
          if(temp.izq != null){
            pila.push(temp.izq);
          }
        }

        resp.dato = temp.dato;      

        if(temp.padre.izq == temp){
          if(temp.der != null){
            temp.padre.izq = temp.der;
          }else{
            temp.padre.izq  = null;
          }
        }else{
          if(temp.der != null){
            temp.padre.der = temp.der;
          }else{
            temp.padre.der = null;
          }
        }

      }
    }

    return borrado;
  }

  // -------------- c)esLleno
  //O(n)
  //Costo espacial: n
  public boolean esLleno(){
    boolean resp = true;

    Stack<NodoBST<T>> s2 = this.postorden();

    while (!s2.isEmpty() && resp) { 
      NodoBST<T> temp = s2.pop();
      if( !(temp.der != null && temp.izq != null || temp.izq == null && temp.der == null) ){
        resp = false;
      }
    }

    return resp;
  }

  // -------------- d)esCompleto
  //O(log(n))
  //Costo espacial: n
  public boolean esCompleto(){
    Queue<NodoBST<T>> colaAuxiliar = new LinkedList<NodoBST<T>>();
    colaAuxiliar.add(this.raiz);

    boolean detectarnull = false;

    //Recorrido en amplitud
    boolean completo = true;
    while(colaAuxiliar.size() != 0 && completo){
      NodoBST<T> nodo = colaAuxiliar.poll();

      //Si el nodo no tiene hijo izquierdo o derecho y ya se detecto uno nulo
      if(detectarnull && (nodo.izq != null || nodo.der != null)){
        completo = false;
      }

      //Si el nodo no tiene hijo izquierdo o derecho
      if((nodo.izq == null || nodo.der == null) && !detectarnull){
        detectarnull = true;
      }

      if(nodo.izq != null){
        colaAuxiliar.add(nodo.izq);
      }
      if(nodo.der != null){
        colaAuxiliar.add(nodo.der);
      }
      
    }
    return completo;
  }

  // -------------- e)cntHojas
  //O(n)
  //Costo espacial:n
  public int cntHojas(){
    int cant = 0;
    Stack<NodoBST<T>> nodos = this.postorden();

    while (!nodos.isEmpty()) { 
      NodoBST<T> temp = nodos.pop(); 
      if(temp.izq == null && temp.der == null){
        cant++;
      }
    }

    return cant;
  }

  // -------------- f)Sucesor
  //O(n)
  //Costo espacial: n
  public T sucesor(T dato){
    //Inorden trae ordenado ascendentemente
    Queue<NodoBST<T>> cola = this.inorden();

    boolean encontrado = false;
    boolean terminado = false;

    T resp = dato;

    while (!cola.isEmpty() && !terminado) { 
      NodoBST<T> temp = cola.poll();

      if(encontrado){
        terminado = true;
        resp = temp.dato;
      }

      if(temp.dato.compareTo(dato) == 0){
        encontrado = true;
      }
    }
    return resp;
  }

  // -------------- g)Antecesor
  //O(n)
  //Costo espacial: n
  public T predecesor(T dato){
    //Inorden trae ordenado ascendentemente
    Queue<NodoBST<T>> cola = this.inorden();
    boolean terminado = false;
    NodoBST<T> temp = null;
    NodoBST<T> ant = new NodoBST<T>(dato);

    T resp = dato;

    while (!cola.isEmpty() && !terminado) { 
      ant = temp;
      temp = cola.poll();
      if(temp.dato.compareTo(dato) == 0){
        if(ant == null){
          resp = dato;
        }else{
          resp = ant.dato;
        }
        terminado = true;
      }
    }

    return resp;
  }

  //Métodos secundarios
  private boolean vacio(){
    boolean resp = false;
    if(this.raiz == null){
      resp = true;
    }
    return resp;
  }

  //O(n)
  public NodoBST<T> buscar(T dato) 
  {
    NodoBST<T> t = this.raiz;

    while( t != null ) {
      if( dato.compareTo( t.dato ) < 0 )
        t = t.izq;
      else if( dato.compareTo( t.dato ) > 0 )
        t = t.der;
      else
        break;
    }

    return t;
  }
  //RECORRIDO POSTORDEN
  private Stack<NodoBST<T>> postorden(){
    //Crear dos pilas
    Stack<NodoBST<T>> s1 = new Stack<NodoBST<T>>(); 
    Stack<NodoBST<T>> s2 = new Stack<NodoBST<T>>(); 

    //Agregar raiz para la primera pila
    s1.push(this.raiz); 

    //Hacer mientras la primera pila no este vacia
    while (!s1.isEmpty()) { 
      // Pop un elemento de s1 y agregarlo a s2
      NodoBST<T> temp = s1.pop(); 
      s2.push(temp); 


      // Agregue los hijos de izquierda y derechas
      if (temp.izq != null) 
        s1.push(temp.izq); 
      if (temp.der != null) 
        s1.push(temp.der); 
    }
    return s2;
  }
  //RECORRIDO INORDEN
  private Queue<NodoBST<T>> inorden(){

    Stack<NodoBST<T>> s = new Stack<NodoBST<T>>();
    Queue<NodoBST<T>> resp = new LinkedList<NodoBST<T>>();

    if (this.raiz == null) 
      return resp;   

    NodoBST<T> curr = this.raiz; 

    //Atravesar el arbol
    while (curr != null || s.size() > 0) 
    { 

        /* Alcanza el nodo más a la izquierda del
        nodo de curr */
        while (curr !=  null) 
        { 
            /* colocar el puntero a un nodo de árbol en
            la pila antes de atravesar
            subárbol izquierdo del nodo */
            s.push(curr); 
            curr = curr.izq; 
        }

        /* curr debe ser NULL en este punto */
        curr = s.pop(); 

        resp.add(curr);

        /* Hemos visitado el nodo y su
        subárbol izquierdo. Ahora es el turno del subarbol */
        curr = curr.der; 
    }

    return resp;
  }

  public void imprimir()
  {
    Queue<NodoBST<T>> cola = this.inorden();
    while (!cola.isEmpty()) { 
      NodoBST<T> temp = cola.poll();
      System.out.print(temp.dato+" ");
    }
    System.out.println();
  }
   
}