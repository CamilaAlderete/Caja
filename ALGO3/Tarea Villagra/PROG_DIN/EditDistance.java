class EditDistance{

	//nro de operaciones para transformar una cadena a otra
	/*

		formula
		-------------------------------------------------------------------
		if str1[i]==str2[j]
			mat[i][j]=mat[i-1][j-1]
		else

			mat[i][f]= min( mat[f-1][c], mat[f][c-1], mat[f-1][c-1] ) +1;
		-------------------------------------------------------------------
	*/

	void nro_operaciones(String str, String objetivo){
		int C=objetivo.length()+1;
		int F=str.length()+1;
		int mat[][] = new int[F][C];

		//de cadena vacia a una cadena no vacia con i caracteres, toma i operaciones convertir de vacio a cadena
		for (int i=0; i<F; i++) {

			mat[i][0]=i;


		}

		for (int i=0; i<C; i++) {

			mat[0][i]=i;
			
		}


		int min;

		for (int f=1; f<F; f++) {

			for (int c=1; c<C; c++) {
				
				//si los caracteres son iguales
				if ( objetivo.charAt(c-1) == str.charAt(f-1)) {
						
					mat[f][c] = mat[f-1][c-1];


				}else{ //buscar el menor valor

					min = buscaMin(mat,f,c);
					mat[f][c]=min + 1;


				}

			}
		}

		print(mat,F,C);

		System.out.println("Numero de operaciones: "+mat[F-1][C-1]);
	}

	void print(int mat[][],int F,int C){

		for (int f=0; f<F; f++) {
			
			for (int c=0; c<C; c++) {
				System.out.print( mat[f][c]);
			}

			System.out.println();

		}

	}


	int buscaMin(int mat[][],int f, int c){

		return min( min( mat[f][c-1], mat[f-1][c] ), mat[f-1][c-1] );

	}

	int min(int a,int b){
		return a<b?a:b;
	}

	public static void main(String[] args) {
		String str1 = "camila";
		String str2 = "cmi" ;

		System.out.println("cadena de partida: "+str2);
		System.out.println("cadena objetivo: "+str1);

		EditDistance obj = new EditDistance();
		obj.nro_operaciones(str2,str1);
	}
}