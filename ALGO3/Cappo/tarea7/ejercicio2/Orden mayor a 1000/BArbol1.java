/*
	Integrantes:
	G16 - Luis Pereira y Eric Ruiz Diaz

	Con la participacion y creditos respectivos a G03 Camila Alderete
	!No esta implementado el Sleep()
*/

import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class BArbol1 <T extends Comparable<T>>
{
   /*
    * Por defecto, almacenamos 10 items en cada pagina. (Este numero
    * es completamente arbitrario y su eleccion no tiene ningun 
    * proposito especifico).
    */

   private int orden;

   public PaginaBArbol raiz = null;

   /*
    * item_a_subir_dato:
    *   Es una referencia al dato que debe "flotar
    *   hacia arriba en el arbol (por ejemplo cuando se esta agregando
    *   un elemento).
    *
    */

  private T item_a_subir_dato;
   /*
    * item_a_subir_ptr_may:
    *   Es una referencia a la pagina inferior (o "derecha") que contiene
    *   los elementos mayores al dato referenciado por "item_a_subir_dato".
    *   
    *   Esta variable es complemento (trabaja con) item_a_subir_dato
    *   y ayudan a implementar el parametro "v" utilizado en el codigo
    *   de Wirth (donde se recibe por referencia).
    */
   private PaginaBArbol item_a_subir_ptr_may;

   public BArbol1 (int orden)
   {
      item_a_subir_dato = null;
      item_a_subir_ptr_may = null;

      this.orden = orden;
   }

   public PaginaBArbol obtenerRaiz(){return raiz;}
   
   public void agregar(T d)
   {
      item_a_subir_dato = null;
      item_a_subir_ptr_may = null;

      priv_agregar (raiz, d);

      /*
       * Si un item "floto" hacia la raiz y no se resolvio su
       * insercion antes, quiere decir que la raiz se partio en
       * dos, por lo que debemos crear una nueva raiz.
       *
       * Tambien se ejecuta esta parte cuando se agrega el primer
       * elemento del arbol, por lo que se crea la pagina raiz.
       */

      if ( item_a_subir_dato != null )
      {
         PaginaBArbol copia_raiz = raiz;
    
         raiz = new PaginaBArbol (this.orden);
         raiz.m = 1;
         raiz.may.set(0, copia_raiz);
         raiz.asignarItem (1, item_a_subir_dato, item_a_subir_ptr_may);
      }
   }

   /*
    * item_a_subir() se utiliza unicamente para escribir menos
    * y hacer mas legible el codigo.
    */
   private void item_a_subir (PaginaBArbol pg, int pos)
   {
      item_a_subir_dato = pg.dato.get(pos);
      item_a_subir_ptr_may = pg.ptrMayor (pos);
   }


   /*
    * Agrega un dato al arbol.
    *
    * Si un item (es decir, un dato + su puntero ¨derecho¨ deben subir,
    * las variables item_a_subir_dato e item_a_subir_ptr_may contienen
    * las referencias correspondientes (es decir, apuntan al item).
    */
    void proct_agregar (PaginaBArbol pg, T d)
   {
   		priv_agregar(pg, d);
   }
   
   
   private void priv_agregar (PaginaBArbol pg, T d)
   {
      int R;

      T u_dato;
      PaginaBArbol u_ptr_may;

      /*
       * Si pg == null, significa que el dato no se encuentra
       * en el arbol, por lo que indicamos que un nuevo item
       * (formado por el dato + un puntero derecho null) debe
       * ¨flotar¨ hacia la raiz.
       */

      if ( pg == null )
      {
         item_a_subir_dato = d;
         item_a_subir_ptr_may  = null;
         return;
      }

      R = pg.buscar (d);

      /*
       * NO admitimos duplicados en esta implementacion.
       */

      if ( R > 0 )
         return;

      /*
       * Si no le encontramos, sabemos que el menor esta
       * en |R|
       */
      if ( R < 0 )
         R = -R;

      priv_agregar (pg.ptrMayor (R), d);

      /* 
       * Si el arbol no necesita crecer, simplemente retornar, pues
       * la operacion de agregar ya se completo.
       */

      if ( item_a_subir_dato == null )
         return;

      /* 
       * Si hay lugar en la pagina actual, agregar.
       */

      if ( pg.m < this.orden )
         agregar_sin_crecer (pg, R);
      else
         partir_pagina_y_agregar (pg, R);
   }


   /*
    * Rutina auxiliar de priv_agregar(), llamado cuando
    * hay lugar en la pagina actual para agregar un item.
    */

   private void agregar_sin_crecer (PaginaBArbol pg, int R)
   {
      pg.m++; 

      /*
       * Hacemos un lugar para el nuevo dato, en la posicion
       * correcta, basandonos en R (su primer menor).
       */
      for (int k=pg.m; k >= R+2; k--)
          pg.copiarItem (k, pg, k-1);

      pg.asignarItem (R+1, item_a_subir_dato, item_a_subir_ptr_may);

      /*
       * Indiquemos a quien nos haya llamado que el arbor no necesita
       * crecer.
       */

      item_a_subir_dato = null;
   }


   /*
    * Rutina auxiliar de priv_agregar(), llamado cuando
    * la pagina actual debe partirse.
    */

   private void partir_pagina_y_agregar (PaginaBArbol pg, int R)
   {
      /*
       * Tendremos que partir pg en 2, es decir, crearemos una
       * nueva pagina.
       */

      PaginaBArbol b = new PaginaBArbol (this.orden);
      int n = orden / 2;

      /*
       * Necesitamos hacer una copia de item_a_subir_xxx porque
       * insetaremos en una de estas dos paginas al item que esta
       * ¨flotando¨ pero (posiblemente) a consecuencia otro
       * item de la pagina actual dejaremos que flote hacia la raiz.
       */
      T          copia_item_a_subir_dato    = item_a_subir_dato;
      PaginaBArbol copia_item_a_subir_ptr_may = item_a_subir_ptr_may;

      if ( R <= n )
      {
         /*
          * Cuando R == n, quiere decir que todos los items a la
          * mitad izquierda son menores que el dato que esta
          * flotando, asi que no debemos correr nada; simplemente
          * copiar la mitad derecha en la nueva pagina y dejar
          * que siga flotando un nivel mas.
          */

         if ( R != n )
         {
            /*
             * El item que debe flotar es pg [n].
             * Notese que estamos sobreescribiendo el contenido
             * de item_a_subir_xxx, pero no nos importa porque
                * ya tenemos una copia en copia_item_a_subir_xxx.
             */
            item_a_subir (pg, n);

            /*
             * Hacemos lugar para el item que ¨floto¨ hasta nosotros.
             */
            for (int k=n; k >= R+2; k--)
                pg.copiarItem (k, pg, k-1);

            pg.asignarItem (R+1, copia_item_a_subir_dato, copia_item_a_subir_ptr_may);
         }
         /*
          * Copiamos la mitad derecha de pg en la mitad izquierda
          * de la nueva pagina.
          */
         for (int k=1; k <= n; k++)
             b.copiarItem (k, pg, k+n);
      }
      else
      {
         /*
          * En la nueva pagina habran n items menos que ahora, asi
          * que la posicion de insercion se desplaza a la izquierda
          * en igual cantidad.
          */
         R -= n;

         /*
          * Como consecuencia de la division, el item que debe flotar
          * es el primero de la mitad derecha.
          */
         item_a_subir (pg, n+1);

         /*
          * Copiamos a la nueva pagina todos los item que estan
          * antes de R. NO copiamos pg [n+1].
          */
         for (int k=1; k < R; k++)
             b.copiarItem (k, pg, k+n+1);
            
         b.asignarItem (R, copia_item_a_subir_dato,
                           copia_item_a_subir_ptr_may);

         /*
          * Copiamos a la nueva pagina los elementos que estaban
          * a la derecha de R en la vieja pagina (pg).
          */
         for (int k=R+1; k <= n; k++)
             b.copiarItem (k, pg, k+n);
      }
      /*
       * Tanto la vieja como la nueva pagina quedaron con n
       * items.
       */

      pg.m = n;
      b.m = n;

      /*
       * La nueva pagina que hemos creado contiene elementos que
       * son todos mayores que pg [n+1] (dato original), por lo tanto
       * su apuntador a menores (may [0]) es el que correspondia
       * a pg [n+1].
       */

      b.may.set(0, item_a_subir_ptr_may);

      /*
       * Ya hemos registrado cual es el item que debe flotar hacia
       * la raiz. El puntero ¨derecho¨ de este item 
       * debe ser actualizado: su ptr_may debe apuntar ahora a la
       * pagina que acabamos de crear.
       */

      item_a_subir_ptr_may = b;
   }


   public void imprimir()
   {
      priv_imprimir (raiz, 0);
   }


   void priv_imprimir (PaginaBArbol pg, int nivel)
   {
      int k;

      if ( pg == null )
         return;

      for (k=0; k < nivel; k++)
          System.out.print (" ");

      for (k=1; k <= pg.m; k++)
      {
          System.out.print( pg.dato.get(k) + " " );
          System.out.print (" ");
      }
      System.out.print("  ");

      for (k=0; k <= pg.m; k++)
         priv_imprimir (pg.ptrMayor (k), nivel + 1);
   }

   public void imprimirAsc(){
        priv_imprimir_asc(raiz);
   }


   private void priv_imprimir_asc (PaginaBArbol pg)
   {
      if ( pg == null )
         return;

      priv_imprimir_asc (pg.ptrMayor (0));
      for (int k=1; k <= pg.m; k++) {
          System.out.print( pg.dato.get(k) + " " );
          System.out.print ("-");
          priv_imprimir_asc (pg.ptrMayor (k));
      }
      System.out.println();
   }
   public int eliminar(ArrayList<T>A ){
	   
		int i,j,k;
		int match = 0;
  
		Scanner scan = new Scanner(System.in);
			
		System.out.println("Inserte el elemento a eliminar");
		String aux = scan.nextLine();
		System.out.println("El caracter a borrar es = "+aux);
		for (j = 0; j< A.size();j++){
			if(aux.compareTo(String.valueOf(A.get(j))) == 0){
				System.out.println("El caracter encontrado es = "+A.get(j));
				match = 1;
				for(k = j; k < A.size() - 1; k++)
					A.set(k,A.get(k+1));
					A.remove(A.size()-1);
				break;
			}
		}
		return match;
   }
    

   public T buscar (T d)
   {
       return ( priv_buscar (raiz, d) );
   }


   private T priv_buscar (PaginaBArbol pg, T d)
   {
       int R;

       if ( pg == null )
          return ( null );

       R = pg.buscar (d);
       if ( R > 0 )
          return ( pg.dato.get(R) );

       return ( priv_buscar (pg.ptrMayor (-R), d) );
   }

 
   
   /*
     * PaginaBArbol.java
     * 
     * Implementa una pagina de un B-Arbol, con cantidad configurable
     * de items por pagina.
     *
     * 
     */
    
    /*
     * Entero se importa solo porque main() lo utiliza para probar
     * esta clase.
    */
    
    /**
     * Implementa una pagina de B-Arbol.
     * Cada pagina contiene:
     * 1. Un arreglo de referencias al dato (dato[]), que debe ser derivado de
     *    la interfaz Cmp, directa o indirectamente.
     *    Este arreglo tiene orden+1 elementos, aunque solo se usan los
     *    elementos de 1..orden. (El elemento 0 se desperdicia para
     *    mantener coherencia con may[], que si usa el elemento 0).
     * 2. Un arreglo de referencias a la pagina que contiene datos mayores
     *    que la pagina actual (may[]).
     * 3. Un contador de elementos en uso (m).
     *
     * La implementacion tradicional es combinar dato y may en una
     * estructura y utilizar un solo arreglo. Sin embargo, esta
     * estructura es simple y muy facil de entender.
     *
     * 
    */
    
    public  class PaginaBArbol
    {
       public int m;
       public ArrayList<T> dato;
       public ArrayList<PaginaBArbol> may;

		
       public PaginaBArbol(int orden)
       {
          dato = new ArrayList<T>(orden + 1);
          may = new ArrayList<>(orden + 1);
          m = 0;
		  for (int i = 0; i < orden + 1; i++){
			dato.add(null);
			may.add(null);
		  }
          for (int k=0; k <= m; k++)
              asignarItem (k, null, null); 
       }
    
       public PaginaBArbol ptrMayor (int p)
       {
          return ( may.get(p) );
       }
    
    
       public  void insertar (T d)
       /**
          Se parte de la premisa de que venimos aqui solo
          si la pagina no esta llena.
       */
       {
          //int cmp;
          int k;
          
          /*
          if ( m >= dato.length )
             throw new Exception ("PaginaBArbol llena");
          */
    
          ++m;
         
          for (k=m; k > 1; k--)
          {
              if (d.compareTo(dato.get(k-1)) >= 0)
                 break;
    
              dato.set(k, dato.get(k-1));
              may.set(k,may.get(k-1));
          }
          dato.set(k,d);
          may.set(k,null);
       }
    
    
       /**
          Retorna posicion de un elemento, indicado por retorno > 0.
    
          Si d no se encuentra, retorna posicion del elemento
          menor, cambiado de signo (por ejemplo, -3 para indicar
          que en la posicion 3 esta un elemento menor a d).
       */
       public int buscar (T d)
       {
          int R, L;
          int i = 0;
		 
          L = 1;
          R = m+1;
          
		  while ( L < R )
          {
             i = (L+R) / 2;
   
             if (dato.get(i).compareTo(d) == 0 )
                break;
    
             if ( dato.get(i).compareTo(d) < 0 )
                L = i + 1;
             else
                R = i;
          }
          if ( dato.get(i).compareTo(d) == 0 )
             return ( i );
          else
            return ( - (R-1) );
       }
		

       public void eliminar (int p)
       {
          if ( m == 0 )
             return;
    
          for (int k=p; k < m; k++) 
          {
              dato.set(k,dato.get(k+1));
              may.set(k,may.get(k+1));
          }
          dato.set(m,null);
          --m;
       }
       
       public void imprimir()
       {
          System.out.println();
    
          for (int k=1; k <= m; k++)
          {
              System.out.print( dato.get(k) + " " );
              System.out.print ("(" + k + ") ");
          }
       }
    
       public void asignarItem(int pos_dest, T d,PaginaBArbol ptr_may)
		{
          this.dato.set(pos_dest,d) ;
          this.may.set(pos_dest, ptr_may);
       }
       
    
       public void copiarItem (int pos_dest, PaginaBArbol b, int pos_orig)
       {
          this.dato.set(pos_dest, b.dato.get(pos_orig));
          this.may.set(pos_dest,b.may.get(pos_orig));
       }
    
    }
	
	







   public static void main(String [] argv) {
     
		ArrayList<Integer> A = new ArrayList<Integer>();	
    ArrayList<Integer> B = new ArrayList<Integer>();  
		B.add(13);
		B.add(42);
		B.add(4);
		B.add(6);
		B.add(20);
		B.add(15);
		B.add(45);
		B.add(44);
		B.add(56);
		B.add(36);
		B.add(55);
		B.add(78);
		

    Random aleatorio = new Random( System.currentTimeMillis() );

    for (int j=0 ; j<1000000 ; j++) {

      A.add( aleatorio.nextInt(1000000) );
    }

	  Scanner scan = new Scanner(System.in);
	   
	   int opcion = 1;
     int i;
     int item = 0;
     int start = 1;
     int elOrden;

	   long t1 ,t2, t_barb_ins,t_barb_bus;

	   System.out.println("Inserte un numero entero positivo para el orden del arbol");
	   elOrden = scan.nextInt();
 
	   BArbol1 <Integer> b_Arbol = new BArbol1<Integer>(elOrden);
	  
		for (i=0;i<B.size();i++){
			b_Arbol.agregar(B.get(i));
		}
			
		b_Arbol.imprimirAsc();
	   
	   while(opcion <=2 ){
			
			System.out.println("\n1)Eliminar un elemento del BArbol\n2)Cuadro de Comparacion BST - AVL - B Arbol\n3)Salir");
				
			opcion = scan.nextInt();
			scan.nextLine();
					
			if(opcion == 1){
				item = b_Arbol.eliminar(B);
				System.out.println("El archivo fue encontrado: "+item);

				if(item == 1){
					b_Arbol = new BArbol1<Integer>(elOrden);
					for (i=0;i<B.size();i++){
						b_Arbol.agregar(B.get(i));
					}
					b_Arbol.imprimirAsc();
				}

			}else if(opcion == 2){

        System.out.println("1.000.000 elementos, esperar :)...");
				

         /*----------------------------------------------------------------------------*/
        long t1_bst, t2_bst, t1_avl, t2_avl;
        // t1_arbolB,t2_arbolb,
        long tTotal;

        // BArbol1 <Integer> b_tree = new BArbol1<Integer>(elOrden);
         AVLTree avl_tree = new AVLTree();
         BST bst_tree = new BST(); 
    
        /*--------------------------------------------------------------------------*/
        //tiempo de insercion en avl

        System.out.println("\nAVl");
         
        t1_avl= System.currentTimeMillis();
        
        for (int j=0; j<A.size(); j++){
          avl_tree.raiz = avl_tree.insertar(avl_tree.raiz, A.get(j));
        }

        /*for (int j=1; j<=1000000; j++){
          avl_tree.raiz = avl_tree.insertar(avl_tree.raiz,j);
        }*/

        t2_avl= System.currentTimeMillis();

        tTotal= t2_avl - t1_avl;
        System.out.println("Insercion: "+tTotal);


        //tiempo busqueda
        t1_avl= System.currentTimeMillis();

        for (int j=0; j<A.size(); j++){
          avl_tree.busqueda(avl_tree.raiz, A.get(j));

        }

        t2_avl= System.currentTimeMillis();

        tTotal= t2_avl - t1_avl;

        System.out.println("Busqueda: "+tTotal);     



        /*----------------------------------------------------------------------------*/
        //tiempo de insercion en bst
        System.out.println("\nBst");
        t1_bst= System.currentTimeMillis();        
        for (int j=0; j<A.size(); j++){
          bst_tree.raiz = bst_tree.insertar(bst_tree.raiz, A.get(j));
        }

        /*for (int j=1; j<=1000000; j++){
          bst_tree.raiz = bst_tree.insertar(bst_tree.raiz,  aleatorio.nextInt(1000000));
        }*/

        t2_bst= System.currentTimeMillis();

        tTotal=t2_bst - t1_bst;
        System.out.println("Insercion: "+tTotal);

        //tiempo busqueda
        t1_bst= System.currentTimeMillis();        
        for (int j=0; j<A.size(); j++){
          bst_tree.busqueda(bst_tree.raiz, A.get(j));
        }

        t2_bst= System.currentTimeMillis();

        tTotal=t2_bst - t1_bst;
        System.out.println("Insercion: "+tTotal);


        /*--------------------------------------------------------------------------------*/
				int orden =1000;
        System.out.println("B-Arbol");
        do{

           System.out.println("orden: "+orden);
  					b_Arbol = new BArbol1<Integer>(orden);
  					/*for (int g = 1000000;g>0;g--){
  						Integer hello = g;
  						b_Arbol.agregar(hello);
  					}*/
          t1 =  System.currentTimeMillis();
          for (int j=0; j<A.size(); j++){
           
            b_Arbol.agregar(A.get(j));
          }

  		
  				t2 =  System.currentTimeMillis();
  				
  				t_barb_ins = t2 - t1; 
  				
  				t1 = System.currentTimeMillis();
  				
  				//	b_Arbol = new BArbol1<Integer>(100);
  				/*	for (int g = 1000000;g>0;g--){
  						Integer hello = g;
  						b_Arbol.buscar(hello);
  					}*/

          for (int j=0; j<A.size(); j++){
            b_Arbol.agregar(A.get(j));
          }
  		
  				t2 =  System.currentTimeMillis();
  				
  				t_barb_bus = t2 - t1; 
  			
  				
  				System.out.println("Insercion:"+t_barb_ins+"\tBusqueda:"+t_barb_bus);

          orden+=500;

        }while(orden <=2000);


       
        //timepo de insercion en BArbol...

        /*System.out.println("B- Arbol");
        t1_arbolB = System.currentTimeMillis();
        for (i=0;i<A.size();i++){
           b_Arbol.agregar(A.get(i));
        }
        t2_arbolb = System.currentTimeMillis();
        tTotal = t2_arbolb - t1_arbolB;
        System.out.println("Insercion: "+tTotal);*/
        

      }
		}
	}
}	