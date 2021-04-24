
/*
	Integrantes:
	G16 - Luis Pereira y Eric Ruiz Diaz

	Con la participacion y creditos respectivos a G03 Camila Alderete

*/
/* 
  Algoritmos y ED III
  
 Clase que simula un tipo entero implementando la interface
 creada Cmp...

 */
 
public class Entero implements Cmp {

   private int e;		// Componente de la clase...
   
   public Entero(int n) // Constructor...
   {
      this.e = n;
   }
   
   public int cmp( Cmp b )	// Metodo de comparacion...
   {
      int c = ((Entero)b).e;
      if( this.e > c )
         return 1;
      else
         if( this.e < c )
	    return -1;
	 else
	    return 0;
   }
   
   public void imprimir()	// Metodo para impresion...
   {
      System.out.print( this.e + " " );
   }
   
   public int get()
   {
   	  return this.e;
   }
   
   public void put( int x )
   {
   	  this.e = x;
   }
}