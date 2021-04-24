/*
Grupo G16:
Luis Pereira
Eric RuizDiaz
2019
*/
public class ejecutor{
    public static void main ( String [] args ) {
        BST<Integer> t = new BST<Integer>();
        Integer [] A = { 10, 15, 7, 8, 6, 2, 11, 12 };
        //Integer [] A = { 10, 7, 8, 6, 2 };
            for ( int k=0; k < A.length; k++ )
                t.agregar( A[k]);

        t.imprimir();

        /*
        t.eliminar(12);
        t.eliminar(2);
        t.eliminar(6);
        t.eliminar(8);
        t.eliminar(11);
        t.eliminar(10);
        t.imprimir()
        */

        System.out.println("Sucesor de 8: "+t.sucesor(8));
        System.out.println("Predecesor de 8: "+t.predecesor(8));

        //System.out.println("Cantidad de hojas "+t.cntHojas());

        //System.out.println("Es lleno "+t.esLleno());
        //System.out.println("Es completo "+t.esCompleto());

        //System.out.println("Buscar 12 : "+t.postorden(12));

        //t.imprimir();

    }
}
