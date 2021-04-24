/*
	G16
	Luis Pereira
	Eric Ruiz Diaz
	
	Trabajo hecho con la participacion y cooperacion de G03
*/
import java.util.Comparator;


public class GenericQuicksortComparable {

    public static <T extends Comparable<T>> void qsort (T[] arr, int ini, int fin) {
       
        if (ini < fin) {
            int i = ini, j = fin;
            T pivote = arr[ (i + j)/2 ];

            do {
                //si el elemento izq es menor a mi pivote, sigo buscando
                while (arr[i].compareTo(pivote) < 0) i++;
                //si el elemento der es mayor a mi pivote, sigo buscando
                while (pivote.compareTo(arr[j]) < 0) j--;

                if ( i <= j) {
                    //intercambio
                    T tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i++;
                    j--;
                }

            } while (i <= j);

            //ordena sublista izq y der
            qsort(arr, ini, j);
            qsort(arr, i, fin);
        }
    }

   /*public static void main(String[] args) {
        Integer[] ia = {30, 20, 10, 5, 6, 99};
        GenericQuicksortComparable.<Integer>qsort(ia, 0, ia.length-1);
        for(Integer i: ia) {
            System.out.println(i);
        }

    }*/
}