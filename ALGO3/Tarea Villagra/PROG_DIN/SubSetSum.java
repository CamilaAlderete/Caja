class SubSetSum{
	/* - Sub set sum: sumatoria de elementos de un subconjunto S, tal que la sumatoria sea igual a un valor t
	   - Similar a knapsack
		
		matriz:
		* f: a1, a1, ... , ak (elementos)
		* c: s1, s2, ... , st (suma)
	   	
	*/	

	void subSetSum(int s[], int suma){
		//suponiendo que se recibe s ordenado
		int F=s.length+1;
		int C=suma+1;
		boolean mat[][]=new boolean[F][C];

		for (int f=0; f<F;f++) {
			
			mat[f][0]=true; // si a-b==0, entonces true - > "1",  es exacto
			
		}

		for (int f=1; f<F; f++) { //iterando sobre los elementos

			for (int c=1; c< C; c++) {
				
				if ( s[f-1] > c) { //si el elemento es menor a mi "suma parcial"

					mat[f][c]=mat[f-1][c];//copiar lo ya calculado
					
				}else if(s[f-1] <= c){
					//si se puede agragar elemento o existe elemento ya calculado que oporta
					mat[f][c]= ( mat[f-1][c] || mat[f-1][c - s[f-1]] );

				}

			}
			
		}

		print(mat,F,C,suma,s);

	}

	void print(boolean mat[][],int F,int C,int suma, int s[]){
		System.out.println("Suma: "+suma);
		System.out.print("Conjunto: ");
		for (int i=0; i<s.length; i++) {
			System.out.print(s[i]+" ");
			
		}

		System.out.println();

				
		System.out.println("Tabla:");

		for (int f=0; f<F; f++) {
			for (int c=0; c<C; c++) {
				if (mat[f][c]==true) {
					System.out.print(1+" ");
										
				}else{
					System.out.print(0+" ");

				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		int set[] = {3, 34, 4, 12, 5, 2};
				//{2,3,4,5,12,34};
		//
		int sum = 9;

		SubSetSum obj = new SubSetSum();
		obj.subSetSum(set,sum);

	}
}