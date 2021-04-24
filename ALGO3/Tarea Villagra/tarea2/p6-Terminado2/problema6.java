import java.util.*;

class problema6{

	static int soluciones;//variable de clase
	int caso;

	public problema6(int caso){
	 	soluciones =0; //al principio no hay ninguna solucion
	 	this.caso = caso;
	}

	void colocarReina(char mat[][], int f, int c, int reinas[]){

		mat[f][c] = '@';
		reinas[f] = c;
		
	}

	boolean amenazado(char mat[][], int f, int c, int reinas[]){
		//si f o c estan fuera de los limites de la matriz
		if (f<0 ||  f>= mat.length || c>=mat.length || c<0) {
			return true;
		}

		//si esa casilla no esta disponible o hay una reina
		if (mat[f][c]=='@' || mat[f][c]=='*') {
			return true;
		}

		
		//si alguna columna esta siendo amenzada 
		for (i=0; i<mat.length; i++) {
			//System.out.print(reinas[i]);
			if(c == reinas[i]) {
				return true;			
			}		
		}
		
		//si las diagonales estan amenazadas por alguna dama
		int i,j;
		//4to cuadrante
		for ( i=f+1, j=c+1 ; i<mat.length &&j<mat.length ; i++ ,j++) {
		
			if (mat[i][j]=='@' ) {
				return true;
			}

		}
		//2do cuadrante
		for ( i=f-1, j=c-1 ; i>=0 && j>=0 ; i-- ,j--) {

			if (mat[i][j]=='@' ) {
				return true;
			}

		}

		//1er cuadrante
		for ( i=f-1, j=c+1 ; i>=0 && j<mat.length ; i-- ,j++) {
		
			if (mat[i][j]=='@' ) {
				return true;
			}

		}

		//3er cuadrante
		for ( i=f+1, j=c-1 ; i<mat.length && j>=0 ; i++ ,j--) {
		
			if (mat[i][j]=='@' ) {
				return true;
			}

		}

		
		
		return false;
		
	}


	void retroceder(char mat[][], int f, int reinas[]){
		f--;
		if (f>=0) {
			mat[f][reinas[f]]='.';	//borra dama		
			int c= reinas[f]+1;		//probar con la sgte columna
			reinas[f]=-1;			//borra columna antigua
			nReinas(mat,f,c,reinas);//fila antigua, columna nueva
		}
	}

	void nReinas(char mat[][],int f,int c,int reinas[]){
		
		//comprueba validez de las coordenadas, si esta amenazado, etc.
		if (amenazado(mat,f,c,reinas)==false) { 
			
			colocarReina(mat,f,c,reinas);
			f++;							
			nReinas(mat,f,0,reinas);//probar con la sgte fila    <------(solucion s1)
			
			/*se probara si existe otra solucion s2 que es resultado de variar la posicion de una reina en s1*/
			retroceder(mat,f,reinas);	
			
				
			
		}else{//posicion ocupada o amenzada

			if (f < mat.length && c < mat.length) { 
				//si esta dentro de los limites de la matriz, seguir probando con las sgtes columnas
				c++;
				nReinas(mat,f,c,reinas);

			//}else if (f < mat.length && c==mat.length ) { //no se puede colocar en ninguna columna y todavia hay reinas por colocar
				
			///	retroceder(mat,f, reinas);

			}else if(f == mat.length){ //todas las reinas colocadas, ya existe una solucion
		
				suma();		
		
			}
		}

	}

	void suma(){
		soluciones++;
	}

	void printSolucion(){
		System.out.println("Case "+caso+": "+soluciones);
	}


	void nro_Soluciones(char mat[][]){

		int n = mat.length;
		int reinas[] = new int[n];

		inicializarReinas(reinas);
		nReinas(mat,0,0,reinas);// se prueba empezando a colocar a la reina en la columna j


		printSolucion();

		
	}

	void inicializarReinas(int reinas[]){
		for (int i =0; i< reinas.length; i++) {
				reinas[i]=-1;
		}
	}


	
	void cargaMatriz(char mat[][],String cadena,int f){

		for (int c=0;c < mat.length; c++) {

			mat[f][c] = cadena.charAt(c);

		}

	}
	
	
	public static void main(String[] args) {
		
		
		int n,caso=0;
		Scanner scan = new Scanner(System.in);
		String cad = new String();
		
		n = scan.nextInt();
		
		while(n!=0){

			if (n!=0) {

				
				scan.nextLine();
				caso++;
				problema6 p = new problema6(caso);
				char mat[][] = new char[n][n];
				int i=0;


				while(i!=n){

					cad = scan.nextLine();
					p.cargaMatriz(mat,cad,i);
					i++;

				}

				p.nro_Soluciones(mat);

				
			}

			n = scan.nextInt();///////////


			
		}

	}


}