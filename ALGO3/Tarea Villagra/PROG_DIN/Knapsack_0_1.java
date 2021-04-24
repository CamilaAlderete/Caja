class Knapsack_0_1{

	/*Obs.: solo esta disponible una unidad de cada item */

	void knapsack(int w, int peso[],int beneficio[]){
		/*se supone que los arreglos peso y beneficio estan ordenados de forma creciente de acuerdo al peso*/

		int mat[][]=new int[peso.length][w+1];//[n_items][capacidad]

		//al principio solo se puede agregar un item
		for (int c=1; c<w+1; c++) {
			
			if (c>= peso[0]) { //si el elemento cabe en la mochila de capacidad c, agregar
				mat[0][c]= beneficio[0];
			}

		}

		for (int f=1; f<peso.length; f++) { //se itera atraves de los items
			
			for (int c=1; c<w+1; c++) { // se itera atraves de la capacidad de la mochila. Ej c=1,c=2, ...

				//si la capacidad de mi mochila es menor al peso de item
				if (c < peso[f]) {
					
					//guardar lo calculado anteriormente
					mat[f][c]= mat[f-1][c];

				}else{ //capacidad suficiente para ingresar mas items

					//es mas beneficioso lo que tengo o agregando un nuevo item
					mat[f][c]=max( mat[f-1][c] , beneficio[f] + mat[f-1][c-peso[f]] );

				}
					
						
					

			}
		}

		printInfo(mat,peso.length,w+1,peso,beneficio);



	}

	int max(int a,int b){
		return a>b?a:b;
	}

	void printInfo(int mat[][],int F,int C,int peso[],int beneficio[]){
		System.out.println("Beneficio obtenido: "+mat[F-1][C-1]);
		

		int c=C-1;
		int f=F-1;

		System.out.println("Capacidad mochila: "+c);
		System.out.println("Peso | Beneficio ");
		//visualizar items seleccionados 
		while(mat[f][c]!=0 && f>0 && c>0 ){

			if (mat[f][c]==mat[f-1][c]) {	
				f--;
			}else{
				System.out.println("   "+peso[f]+" | "+beneficio[f]);
				c=c-peso[f];

			}

		}

		System.out.println("Tabla: ");
		
		for (f=0; f<F; f++) {
			for (c=0; c<C; c++) {
				System.out.print(mat[f][c]+" ");
			}

			System.out.println();
		}
	}

	public static void main(String[] args) {
		int w=7;
		int peso[]={1,3,4,5};
		int beneficio[]={1,4,5,7};

		Knapsack_0_1 obj = new Knapsack_0_1();
		obj.knapsack(w,peso,beneficio);

	}
	
}