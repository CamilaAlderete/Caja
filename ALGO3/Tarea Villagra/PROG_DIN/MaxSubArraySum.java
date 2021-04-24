class MaxSubArraySum{

	//maximum subarray sum
	
	void mss(int v[]){
		
		int aux[]= new int[v.length];
		int suma;
		int posIni=0,posFin=0;
		int maxValue=Integer.MIN_VALUE;
		aux[0]=v[0]; //tabla de memoizacion

		for (int i=1; i<v.length; i++) {
			//es mejor el elemento actual solo o en conjunto?
			suma = max(v[i],v[i]+aux[i-1]);
			aux[i]=suma;

			//si existe subarray con mayor suma, guardar posicion del elemento que aumento la suma
			if (suma>maxValue) {
				maxValue=suma;
				posFin=i;
			}

			//si solo es posible mejorar la suma comenzando de nuevo desde el elemento i
			if (suma==v[i]) {
				posIni=i;

			}

			

		}

		System.out.println("Pos ini: "+posIni+"\nPos fin: "+posFin);

		imprimir(v);
		imprimir(aux);

		System.out.println("Maxima suma: "+maxValue);
	}

	void imprimir(int arr[]){
		for (int i=0; i<arr.length; i++ ) {
			System.out.print(arr[i]+" ");
		}

		System.out.println();
	}

	int max(int a,int b){
		return (a>b?a:b);
	}

	public static void main(String[] args) {

		System.out.println("Longest SubArray Sum");
		int v[]={-2,1,3,-4};
		MaxSubArraySum obj = new MaxSubArraySum();
		obj.mss(v);
		
	}
}