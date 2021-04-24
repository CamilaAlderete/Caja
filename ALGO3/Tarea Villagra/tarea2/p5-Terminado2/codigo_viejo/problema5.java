import java.util.*;

public class problema5{

	void procesarGrafo(ArrayList adyacentes[]){

		int n = adyacentes.length;
		int vertices[] = new int[n];
		int grado[] = new int[n];
		boolean custodiado[]= new boolean[n];

		//vertice y su grado
		for (int i=0; i< n; i++) {
			vertices[i] = i;
			grado[i] = adyacentes[i].size();
			custodiado[i] = false;
		}

		//ordenado descendentemente de acuerdo al grado de los vertices
		sort(grado,vertices,0,n-1);
		//printArray(grado);
		//printArray(vertices);

		int nroGuardias = colocarGuardias(adyacentes,vertices,custodiado);

		System.out.println(nroGuardias);

		//imprimirLista(adyacentes);
	
	}

	/*void imprimirLista(ArrayList adyacentes[]){
		int j;
		for ( int i=0; i<adyacentes.length;i++ ) {
			Iterator<Integer> it = adyacentes[i].iterator();
			System.out.print(i+":");
			while(it.hasNext()){
				j = it.next();
				System.out.print(" "+ j);
			} 

			System.out.println();
		}
	}*/



	int colocarGuardias(ArrayList adyacentes[],int vertices[], boolean custodiado[]){

		int i = 0, nroGuardias = 0;
		boolean ocupado;

		while ( i < adyacentes.length ){

			ocupado = false;
			int u = vertices[i];

			if (custodiado[u]==false) {
				//si u no esta custodiado
				Iterator<Integer> it1 = adyacentes[u].iterator();	
				Iterator<Integer> it2 = adyacentes[u].iterator();
				
				//verificar que vertices adyacentes no esten custodiados
				while(it1.hasNext()){
					
					int v = it1.next();
					
					if (custodiado[v]==true) {
						ocupado = true;
						break;	
					}
				}

				if (ocupado==false) {
					//u y adyacentes no estan custudiados, entonces agregar un guardia
					nroGuardias++;
					custodiado[u]=true;

					//marcar adyacentes como custodiados
					while(it2.hasNext()){
					
						int v = it2.next();
						custodiado[v]=true;
						
					}
				}
			}

			i++;
		}


		for (int j =0 ; j < adyacentes.length ; j++) {
				
				//System.out.print(j+":"+custodiado[j]+"  ");

			if (custodiado[j]==false) {
				return -1;
			}
		}

		return nroGuardias;


	}

	void agregaArista(ArrayList <Integer> adyacentes[] , int u,int v){

		adyacentes[u].add(v);
		adyacentes[v].add(u);

	}


	public static void main(String[] args) {

		int casoPrueba,V,E,u,v;

		Scanner scan = new Scanner(System.in);
		casoPrueba = scan.nextInt();
		scan.nextLine();

		while(casoPrueba!=0){

			V = scan.nextInt();
			E = scan.nextInt();

			ArrayList <Integer> adyacentes[] = new ArrayList[V];
			for (int i=0; i<V ; ++i){ 
            	adyacentes[i] = new ArrayList();
			}

			problema5 p =new problema5();

			while(E!=0){

				u = scan.nextInt();
				v= scan.nextInt();

				p.agregaArista(adyacentes,u,v);

				E--;
			}

			p.procesarGrafo(adyacentes);		
			casoPrueba--;

		}

















	}













/*---------------------------------------------------------------------------------------------*/
	//class MergeSort 
	//{ 
	    // Merges two subarrays of arr1[]. 
	    // First subarray is arr1[l..m] 
	    // Second subarray is arr1[m+1..r] 
	    void merge(int arr1[], int arr2[], int l, int m, int r) 
	    { 
	        // Find sizes of two subarrays to be merged 
	        int n1 = m - l + 1; 
	        int n2 = r - m; 
	  
	        /* Create temp arrays */
	        int L1[] = new int [n1]; 
	        int R1[] = new int [n2]; 
	  		
	  		int L2[] = new int [n1]; 
	        int R2[] = new int [n2]; 
	  
	        /*Copy data to temp arrays*/
	        for (int i=0; i<n1; ++i){ 
	            L1[i] = arr1[l + i];
	            L2[i] = arr2[l + i]; 
	        }
	        
	        for (int j=0; j<n2; ++j){ 
	            R1[j] = arr1[m + 1+ j]; 
	            R2[j] = arr2[m + 1+ j]; 
	  		}
	  
	        /* Merge the temp arrays */
	  
	        // Initial indexes of first and second subarrays 
	        int i = 0, j = 0; 
	  
	        // Initial index of merged subarry array 
	        int k = l; 
	        while (i < n1 && j < n2) 
	        { 
	            if (L1[i] >= R1[j]) 
	            { 
	                arr1[k] = L1[i];
	                arr2[k] = L2[i];                
	                i++; 
	            } 
	            else
	            { 
	                arr1[k] = R1[j]; 
	                arr2[k] = R2[j]; 

	                j++; 
	            } 
	            k++; 
	        } 
	  
	        /* Copy remaining elements of L[] if any */
	        while (i < n1) 
	        { 
	            arr1[k] = L1[i]; 
	            arr2[k] = L2[i]; 
	            i++; 
	            k++; 
	        } 
	  
	        /* Copy remaining elements of R[] if any */
	        while (j < n2) 
	        { 
	            arr1[k] = R1[j]; 
	            arr2[k] = R2[j]; 

	            j++; 
	            k++; 
	        } 
	    } 
	  
	    // Main function that sorts arr1[l..r] using 
	    // merge() 
	    void sort(int arr1[],int arr2[], int l, int r) 
	    { 
	        if (l < r) 
	        { 
	            // Find the middle point 
	            int m = (l+r)/2; 
	  
	            // Sort first and second halves 
	            sort(arr1,arr2, l, m); 
	            sort(arr1,arr2 , m+1, r); 
	  
	            // Merge the sorted halves 
	            merge(arr1,arr2, l, m, r); 
	        } 
	    } 
	  
	    /* A utility function to print array of size n */
	     void printArray(int arr1[]) 
	    { 
	        int n = arr1.length; 
	        for (int i=0; i<n; ++i) 
	            System.out.print(arr1[i] + " "); 
	        System.out.println(); 
	    } 
	  
/*-----------------------------------------------------------------------------------*/    
	
}

