import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

/*
	1) Se toma la lista de cadenas
	2) Se procesa y se crea una lista de adyacencia de acuerdo a la relacion que guardan los caracteres
	3) se recorre con dfs la lista de adyacencia

	Ej.: 

	entrada:XZY
			XGY
			XWY
			ZXY
			ZGW
			ZGYZ
			YWWX

	lista:
	X->Z->Y->G
	Z->G
	Y
	G->W
	W-Y

	DFS: X Z G W Y


*/

class Main{


	

	void agregarVertice(ArrayList<ArrayList<Integer>> adyacentes,ArrayList<String> vertices, String u, String v){
		//suponiendo que es un grafo no dirigido
		//asocia cada letra con un numero, para facilitar el recorrido de las listas
		// vertices = {" B","A",...}
		//	     		 0 , 1 ,...

		/*lista de adyacencia
			vertice | adyacentes
				0 	|	1 	4 
				1 	|	0 
				2 	|	3 
				3 	|	2 
				4 	|	0 				

		*/
		int f1=0,f2=0;///banderas

		//si u no estan en lista de vertices, agregar
		if ( vertices.contains(u) == false) {
			
			vertices.add(u);
			f1=1;

		}

		//si v no estan en lista de vertices, agregar
		if ( vertices.contains(v) == false) {
			
			vertices.add(v);
			f2=1;
		}

		/*-----------------------------------------------*/
		//agregar u y v a lista de adyacencia
		int indiceU = vertices.indexOf(u);//posicion de u en lista vertices
		int indiceV = vertices.indexOf(v);//posicion de v en lista vertices
		ArrayList<Integer> l;			  //lista auxiliar

		if ( f1 ==1 ) {
			//vertice u es nuevo en la lista, no tiene todavia adyacentes
			ArrayList<Integer> l1 = new ArrayList<Integer>();
			adyacentes.add(l1);//agrega una lista mas
		}


		// u->v
		l = adyacentes.get(indiceU);	    // adyacentes a u 
		if (l.contains(indiceV)==false) {   //si v no es todavia adyacente a u 
			l.add(indiceV);					//agregar v
			adyacentes.set(indiceU,l);      // insertar lista actualizada en indiceU
		}
			

		if (f2 == 1) {
		    //vertice v es nuevo en la lista, no tiene todavia adyacentes
			ArrayList<Integer> l1 = new ArrayList<Integer>();
			adyacentes.add(l1);	//agrega una lista mas
		}

	
		
	}

	void cargarLista(ArrayList<String> datos,ArrayList<String> vertices, ArrayList<ArrayList<Integer>> adyacentes){
		
		
		Iterator<String> i = datos.iterator();//lista con cadenas

		
		String cadena1=new String();
		String cadena2=new String();
		
		String v, u;

		//los primeros caracteres de la lista de cadenas siempre estan ordenados
		//se crea lista de adyacencia

		if (i.hasNext()) {
			cadena1 = i.next();
		}

		u = Character.toString( cadena1.charAt(0));
		agregarVertice(adyacentes,vertices,u,u);

		while(i.hasNext()){

			cadena2 = i.next();

			v = Character.toString( cadena2.charAt(0) );
			
			
			if (u.equals(v)==false) {
				agregarVertice(adyacentes,vertices,u,v);				
			}
			
		}

	

	}



	void procesarDatos(ArrayList<String> cadena){
		ArrayList<String> vertices = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> adyacentes = new ArrayList<ArrayList<Integer>>(); 
		
		//se crea lista de adyacencia
		cargarLista(cadena,vertices,adyacentes);

		Iterator<String> i = cadena.iterator();
		String cadena1 = new String();
		String cadena2 = new String();


		if (i.hasNext()) {
			cadena1 = i.next();
		}

		while(i.hasNext()){

			cadena2 = i.next();
			//si los caracteres iniciales de la cadena i y la cadena i+1 son distintos
			//el primer caracter en la cadena i es menor al caracter en la cadena i+1

			if ( cadena1.charAt(0) == cadena2.charAt(0) ) { 

				// caracteres iniciales iguales
				//los siguentes caracteres pueden brindar mas informacion
				compararUnoaUno( cadena1, cadena2, vertices,adyacentes);
			}

			cadena1 = cadena2;
		}

		//imprimir(adyacentes,vertices);

		boolean visitado[] = new boolean[vertices.size()];
		dfs(vertices,adyacentes,visitado,0);

	}


	void dfs(ArrayList<String> vertices, ArrayList<ArrayList<Integer>> adyacentes, boolean visitado[], int index){
				
		if (index < vertices.size()) {
			
			
			if (visitado[index]==false) {

				String u = vertices.get(index);				
				visitado[index]=true;
				System.out.print(u);

				Iterator<Integer> i = adyacentes.get(index).iterator();
				
				while(i.hasNext()){

					Integer v=i.next();
					//int indexV = vertices.indexOf(v);
					dfs(vertices,adyacentes,visitado,v);


				}

			}				
		}
		

	}





	void compararUnoaUno(String cad1, String cad2, ArrayList<String> vertices, ArrayList<ArrayList<Integer>> adyacentes){
		int l1 = cad1.length();
		int l2 = cad2.length();
		int i=0 ; int j=0;

		String u =new String();
		String v= new String();
		ArrayList<Integer> l;// = new ArrayList<String>();

		int indiceV,indiceU;

		while(i!=l1 || j!=l2){
		
			if (i<l1 & j<l2) {
				
				//si los caracteres son distintos, uno es mayor a otro
				if(cad1.charAt(i)!=cad2.charAt(j)){

					//System.out.println(cad2.charAt(j)+" mayor que "+cad1.charAt(i));
					///guarda
					u = Character.toString(cad1.charAt(i));
					v = Character.toString(cad2.charAt(j));
					
					agregarVertice(adyacentes,vertices,u,v);


					break;//salir ya que los caracteres sgtes. ya no aportan informacion
				}
				i++; j++;

			}else{

				//si llegan al limite de la cadena
				if (i==l1) {
					i--;
				}else if(j==l2){
					j--;
				}

				//si los caracteres son distintos, uno es mayor a otro
				if( cad1.charAt(i)!= cad2.charAt(j) ){
					//System.out.println(cad2.charAt(j)+" mayor que "+cad1.charAt(i));

					u = Character.toString(cad1.charAt(i));
					v = Character.toString(cad2.charAt(j));
					
					agregarVertice(adyacentes,vertices,u,v);

					break;//salir ya que los caracteres sgtes. ya no aportan informacion
				}
			}
		}
	}


	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Main m = new Main();
		String cadena = new String();
						
		cadena = scan.nextLine();

		do{

			ArrayList<String> datos = new ArrayList<String>();

			//se ingresan las cadenas
			do{

				if (cadena.equals("#")!=true) {
					datos.add(cadena);	
				}
				cadena = scan.nextLine();

			}while(cadena.equals("#")!=true);

			m.procesarDatos(datos);
			cadena = scan.nextLine();

			System.out.println();
		
		}while(cadena.equals("")!=true);

		
		

		
	}


	
}