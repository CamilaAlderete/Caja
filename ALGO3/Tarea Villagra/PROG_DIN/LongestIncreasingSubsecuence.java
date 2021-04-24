class LongestIncreasingSubsecuence{


	void imprimir(int arr[]){

		for (int i=0; i<arr.length; i++ ) {
			System.out.print(arr[i]+" ");
		}

		System.out.println();
	}


	void lis1(int v[]){	

		//O(n^2)
		int aux[]=new int[v.length];
		//incializacion aux
		
		for (int i=0; i<v.length; i++) {
			aux[i]=1;
		}

		for (int i=1; i<v.length; i++) {
			
			for (int j=0; j<i; j++) {
				
				//si elemento en i es mayor a elemento en j y ademas la longitud en i es menor a la longitud en j mas una unidad
				//agregar a la subsecuencia ya formada en j el elemento en i
				if ( ( v[i]>v[j] ) && ( aux[i]<aux[j]+1) ) {
					aux[i]=aux[j]+1;
				}

			}

		}

		imprimir(aux);

	}

	public static void main(String[] args) {

		System.out.println("Longest Increasing Subsecuence");

		int v[]={3,4,-1,0,6,2,3};

		LongestIncreasingSubsecuence obj = new LongestIncreasingSubsecuence();
		obj.lis1(v);
	}
}