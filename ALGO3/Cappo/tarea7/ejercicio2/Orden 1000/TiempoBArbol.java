/*
	Integrantes:
	G16 - Luis Pereira y Eric Ruiz Diaz

	Con la participacion y creditos respectivos a G03 Camila Alderete

*/

/* 
  Algoritmos y ED III

  Clase de prueba para BArbol
  
*/

public class TiempoBArbol
{
   public static void main (String argv[])
   {
       int g = argv.length;
       BArbol t = new BArbol (10);
       //int nelem = new Integer (argv [0]).intValue();
       int nelem = 1000;
       long t1, t2;


       Entero A [] =  new Entero [nelem];

       for (int k=0; k < nelem; k++)
           A [k] = new Entero ((int) (Math.random()*10000));
          
       t1 = System.currentTimeMillis();
       for (int k=0; k < nelem; k++)
           t.agregar (A [k]);
       t2 = System.currentTimeMillis();

System.out.println ("B-Arbol N (agregar)=" + nelem + " -> " + (t2-t1) / (double) nelem);

       t1 = System.currentTimeMillis();

       for (int k=0; k < nelem; k++)
           /* Descartamos valor de retorno */
           t.buscar (A [k]);

       t2 = System.currentTimeMillis();

System.out.println ("B-Arbol N (buscar)=" + nelem + " -> " + (t2-t1) / (double) nelem);

       
   }
}
