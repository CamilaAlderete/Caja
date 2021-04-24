import java.util.*;

/*
1) se crea lista de adyacencia
2)se aplica bfs al grafo, ya que con bfs se tienen caminos con el menor numero de aristas
*/

public class problema3{

	void procesarGrafo(ArrayList adyacentes[], int src,int dest){

		
		Queue<Integer> cola = new LinkedList<>();
		cola.add(src);

		int dim = adyacentes.length;
		int salto[] = new int[dim];
		int anterior[] = new int[dim];
		boolean visitado[] = new boolean[dim];

		for (int i =0; i<dim ; i++) {
			salto[i] = Integer.MAX_VALUE;
			anterior[i]=i;
			visitado[i]=false; 	
		} 

		salto[src]=0;

		calculoSalto(adyacentes,cola,salto,anterior,visitado);

		if (src==anterior[src] && dest!=anterior[dest]) {//si se puede llegar de src a dest
	
			printCamino(anterior,dest);
			System.out.println();
		
		}else{

			System.out.println("connection impossible");

		}


	}

	void printCamino(int anterior[],int dest){

		//visualizacion en pantalla del camino de src a dest
		if (anterior[dest]!=dest) {// si v tiene como padre a u
			printCamino(anterior, anterior[dest]);
			System.out.print(dest+" ");
		}else{
			System.out.print(dest+" ");
		}

	}

	void calculoSalto(ArrayList adyacentes[], Queue<Integer> cola, int salto[],int anterior[],boolean visitado[]){

		//procesado de vertices en "anchura"
		int u,v; 
		while(cola.size()!=0){
			
			u= cola.poll();
			Iterator<Integer> it = adyacentes[u].iterator();	
			visitado[u]=true;
			//System.out.println(u);

			while( it.hasNext() ){//todos los vertices adyacentes a u
				
				v= it.next();

				if (visitado[v]==false) {
					// u--v
					//si el numero de saltos pasando por u es menor al numero de saltos por otro camino
					if ( (salto[u]+1 < salto[v]) ) { 
						
						salto[v]= salto[u]+1;
						anterior[v]=u;
						
					}


					cola.add(v);
					

				}
		

			}
			//System.out.println("4");
			
		}

	}

	void cargaLista(ArrayList<Integer> adyacentes[], String cadena){
	
		//////////problema: vertices con numeros de dos o mas digitos
		//busqueda de u

		String u = new String();
		int i=0;

		for(;;){

			if ( Character.toString( cadena.charAt(i) ).equals("-")==false) {

				u = u+Character.toString( cadena.charAt(i) );
				i++;

			}else{

				break;

			}
		}

		//System.out.print(u);

		int uInt = Integer.parseInt(u);
		int vInt;

		String v = new String();
		i++;

		while(i < cadena.length()){

			if ( Character.toString( cadena.charAt(i) ).equals(",")==false ) {
				
				v = v + Character.toString( cadena.charAt(i) );
			
				if (i==cadena.length()-1) {
				
					vInt = Integer.parseInt(v);
					
					adyacentes[uInt].add(vInt);
					//adyacentes[vInt].add(uInt);

					//System.out.println(v);
				}
				
			}else{

				vInt = Integer.parseInt(v);

				adyacentes[uInt].add(vInt);
				//adyacentes[vInt].add(uInt);
				//System.out.println(v);
				v = "";
			}

			i++;
		}
		
	}

	public static void main(String[] args) {
		

		
		Scanner scan = new Scanner(System.in); 
		String entrada = new String();

		problema3 p =new problema3();
		String str = new String();
		do{

			entrada = scan.nextLine();

			if (entrada.equals("")==false) {
				//numero de vertices
				int V = Integer.parseInt(entrada);
				
				ArrayList <Integer> adyacentes[] = new ArrayList[V+1];
				
				for (int i=0; i<V+1; ++i){ 
		            adyacentes[i] = new ArrayList();
		        } 
				
				//int i=1;
		        //carga de lista de adyacencia
				while(V!=0){

					str = scan.nextLine();//////////problema: vertices con numeros de dos o mas digitos
					p.cargaLista(adyacentes,str);
					//i++;
					V--;

				}

				int nCaminos = scan.nextInt();
				int ini,fin;
				//String cadena = new String();

				System.out.println("-----");

				while(nCaminos!=0){
		
					//str = scan.nextLine();

					ini = scan.nextInt();
					fin = scan.nextInt(); 

					//ini = Integer.parseInt( Character.toString( str.charAt(0) ) );

					//fin = Integer.parseInt( Character.toString( str.charAt(2) ) );

					p.procesarGrafo(adyacentes,ini,fin);

					nCaminos--;
				}	


			}

				scan.nextLine();
						


		}while(entrada.equals("")!=true);


	}
	
}