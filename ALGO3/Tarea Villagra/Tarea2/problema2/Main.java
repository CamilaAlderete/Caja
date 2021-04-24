import java.io.*;
import java.util.*;

class Main{
	int n=0;

	void suma(){
		n++;
	}

	void nroComponentes(int mat[][]){
		//se hacen varios dfs y se cuentan la cantidad de componentes que se tiene	
		boolean visitado[]=new boolean[mat.length];

		for (int i=0; i<mat.length; i++) {
			if (visitado[i]==false) {
				dfs(mat,visitado,i);
				suma();

			}
		}
		
		System.out.println(n+"\n");
	}

	void dfs(int mat[][],boolean visitado[],int vertice){

		visitado[vertice]=true;
		for (int c=0; c<visitado.length; c++) {
			if (mat[vertice][c]==1 && visitado[c]==false) {
				dfs(mat,visitado,c);
			}
		}


	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String e = new String();

		int num_casos= scan.nextInt();
		scan.nextLine();
		scan.nextLine();
		

		while(num_casos!=0){

			num_casos--;
			Character E_MaxGrado = scan.nextLine().charAt(0);

			//numero de vertices que se tendra, desde A hasta E_MaxGrado
			int dim = Character.getNumericValue(E_MaxGrado)-Character.getNumericValue('A') + 1;
			//System.out.println("GRADO: "+dim);
			int mat[][] = new int[dim][dim];

			for (int k=0; k<dim; k++) {
				mat[k][k]=1;
			}

			boolean sinAristas=true;
			do{
				//mientras haya aristas que ingresar

				e = scan.nextLine();
				if (e.equals("")==false) {
					
					sinAristas=false;
					//cargando matriz de adyacencia					
					int u = Character.getNumericValue( e.charAt(0) ) % dim;
					int v = Character.getNumericValue( e.charAt(1) ) % dim;

					if (v<dim && u<dim) {
						mat[u][v]=1;
						mat[v][u]=1;

					}
						

				}


			}while(e.equals("")==false);


			if (sinAristas==true){
				System.out.println(dim+"\n");
				
			}else{
				Main p = new Main();
	    		p.nroComponentes(mat);	
			}
			


		}


		
     }

}