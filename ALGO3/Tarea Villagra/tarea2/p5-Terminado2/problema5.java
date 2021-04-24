import java.util.*;
class problema5{

	int[][] cargaMatriz(int mat[][],int matAux[][]){
		int v = mat.length;
		matAux = new int[v][v];

		for (int f=0; f<v; f++) {

			for (int c=0; c<v; c++) {
				matAux[f][c]=mat[f][c];
			}
			
		}

		return matAux;
	}

	void procesarGrafo(int mat[][]){
		int matAux[][] = new int[mat.length][mat.length];
		int min = Integer.MAX_VALUE;
		int minParcial = min;


		for (int f=0; f<mat.length; f++) {
			
			//inicializa matriz con datos originales, ya que al buscar la solucion se modifica la matriz
			matAux = cargaMatriz(mat,matAux);

			//busqueda de solucion desde la fila f
			minParcial = buscaSolucion(matAux,f);

			//comprueba si el menor de todos
			if (minParcial>0) {

				if (minParcial < min) {
					min = minParcial;
				}
			}
						
		}


		//si hay un minimo
		if (min!=Integer.MAX_VALUE) { 
			System.out.println(min);	
		}else{
			System.out.println("-1");
		}
		

	}

	void colocaGuardia(int mat[][],int f){
		//como es grafo no dirigido se marca mat[f][c] y mat[c][f]
		for (int c=0; c< mat.length; c++) {
			if (mat[f][c]==1 || mat[c][f]==1) {//si hay arista 
				//marcar como custodiado
				mat[f][c]=2; 
				mat[c][f]=2;
			}
		}

	}


	
	int buscaSolucion(int mat[][], int f_ini){
		
		boolean custodiado = false;
		int f = f_ini,num_guardias=0;

		//se tratara de colocar guadias
		while(f<mat.length){

			//verifica aristas incidentes a el vertice
			custodiado = verificarIncidentes(mat,f);

			//si no esta custodiado
			if ( custodiado==false ) {
				colocaGuardia(mat,f);
				num_guardias++;
				/*print(mat);
				System.out.println();*/
			}

			f++;

		}

		//si todas las aristas estan vigiladas
		/*if( vigilado(mat,num_guardias)==true ){
			return num_guardias;
		}

		//si hay una arista no vigilada
		return -1;*/
		//se comprueba que todas las aristas esten vigiladas
		return vigilado(mat,num_guardias);

	}

	int vigilado(int mat[][],int num_guardias){
		boolean filaVacia;
		//se comprueba que todas las aristas esten vigiladas
		for (int f=0; f<mat.length; f++) {
			
			filaVacia=true;

			for (int c=0; c<mat.length; c++) {
				
				if (mat[f][c]==1) {
					//si hay alguna arista no vigilada
					return -1;
				}

				if (mat[f][c]==2) {//no es arista aislada xq esta vigilada
					filaVacia=false;
				}
			}

			if (filaVacia==true) {//existe un vertice aislado
				num_guardias++;
			}

		}

		/*for (int f=0; f< mat.length; f++) {
			
			for(int c=0;c<mat.length;c++){
				System.out.print(mat[f][c]);
			}

			System.out.println();

		}*/

		return num_guardias;
	}


	boolean verificarIncidentes(int mat[][],int f){
		boolean filaVacia=true;

		//se verifica si arista ya es custodiada, y se tiene cuidado con los vertices aislados
		for (int c=0 ; c< mat.length; c++) {
			
			//si una arista ya siendo custodiada 
			if (mat[f][c]==2 || mat[c][f]==2) {
				return true;
			}

			//vertice f tiene adyacente, por lo tanto no esta aislado
			if (mat[f][c]==1) {
				filaVacia=false;
			}

		}

		if (filaVacia==true) { //vertice aislado
			return true; //por el momento, no colocar guardia
		}

		return false;

	}


	

	void inicializar(int mat[][]){

		for (int f=0; f<mat.length; f++) {
			for (int c=0; c<mat.length; c++) {
				mat[f][c]=0;
			}
		}

	}

	void print(int mat[][]){

		for (int f=0; f<mat.length; f++) {
			for (int c=0; c<mat.length; c++) {
				System.out.print(mat[f][c]+" ");
			}

			System.out.println();
		}

	}


	public static void main(String[] args) {

		problema5 p = new problema5();
		
		int n_grafos,V,E,u,v;
		Scanner scan = new Scanner(System.in);
		n_grafos = scan.nextInt();

		int k=1;

		while(n_grafos!=0){

			//System.out.print(k+": ");
			//k++;

			V=scan.nextInt(); E=scan.nextInt();

			int mat[][]= new int[V][V];
			p.inicializar(mat);
			
			if (E!=0) {//si hay aristas, cargar
						
				while(E!=0){

					u = scan.nextInt();
					v = scan.nextInt(); 

					mat[u][v]=1;
					mat[v][u]=1;
					E--;
				}

				p.procesarGrafo(mat);

			}else{
				//si no hay aristas, imprimir numero de vertices.
				System.out.println(V);
			}

			n_grafos--;
		}


	}
}