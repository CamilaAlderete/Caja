import java.util.Arrays;
import java.util.Scanner;
/*

	G16
	Luis Pereira
	Eric Ruiz Diaz
	
	Trabajo hecho con la participacion y cooperacion de G03

*/
public class quicksort_rc {
    public static void sort(int[] vec) {
        elQuicksort_rc(vec, 1, vec.length);
    }

    private static void elQuicksort_rc(int[] vec, int p, int r) {

        while (p < r) {
            int q = particion(vec, p, r);
			elQuicksort_rc(vec, p, q - 1);
			p = q + 1;		
		}
    }

    private static int particion(int[] vec, int p, int r) {
        
        int i = p - 1;
        int j = p - 1;
        while (j < r-1) {
            if (vec[j] <= vec[r-1]) {
                swap(vec, i++, j);
            }
            j++;
        }

        swap(vec, i++, j);
        return i;
    }

    private static void swap(int[] vec, int i, int j) {
        int temp = vec[i];
        vec[i] = vec[j];
        vec[j] = temp;
    }
	
	public static void main(String[] args) {
	/*	
		int n;
		System.out.println("Inserte el tamanho del vector ha ser ordenado");
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		int[] vec = new int[n];
		for (int i = 0;i<n;i++){
			double num = Math.random()*5*n;
			vec[i] = (int)num; 
		}
		*/
        int[] vec = {26, 16, 25, 50, 62, 75, 89, 37, 79, 26, 21, 62, 23, 10, 5, 53, 98, 50, 71, 16};
        
		System.out.println("Vector antes del quicksort_rc: " + Arrays.toString(vec));
        quicksort_rc test = new quicksort_rc();
		test.sort(vec);
        System.out.println("Vector luego del quicksort_rc: " + Arrays.toString(vec));
    }
}
