import java.io.*;
import java.util.*;

class problema2{

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


		//v->u
		l = adyacentes.get(indiceV);		// adyacentes a v
		if (l.contains(indiceU)==false) {	// si u no es todavia adyacente a v
			
			l.add(indiceU);					//agregar u
			adyacentes.set(indiceV,l);      // insertar lista actualizada en indiceV
		}

		/*----------------------------------------------*/
		
	}

	void cargarLista(ArrayList<String>vertices,ArrayList<String> aristas,ArrayList<ArrayList<Integer>> adyacentes){
		
		Iterator<String> iteradorArista = aristas.iterator();

		
		String cadena=new String();
		String v, u;

		while(iteradorArista.hasNext()){

			cadena = iteradorArista.next();

			v = Character.toString( cadena.charAt(0) );
			u = Character.toString( cadena.charAt(1) );

			agregarVertice(adyacentes,vertices,u,v);
			
     	}

	   //imprimir(vertices,adyacentes);
	   recorrido_profundidad(vertices,adyacentes);

    }


	/*void imprimir(ArrayList<ArrayList<Integer>> lista){

		int k=0;

		for (ArrayList<Integer> iterador1: lista) {
			
			System.out.print(k+" ");
			k++;//vertice
			
			for (Integer iterador2: iterador1) {
				
				System.out.print(iterador2+" ");//adyacentes
			}

			System.out.println();
		}

	}*/


	void recorrido_profundidad(ArrayList<String> Vertices, ArrayList<ArrayList<Integer>>adyacentes){
          int cont=0;// contador de cantidad de subgrafos maximos 
          String VERTICE;// indica el vertice que se visito
          int Index=0; // indice para localizar la lista de adyacencia
          boolean Visitado[]=new boolean[Vertices.size()];// vector que indica si ya fue visitado o no un vertice 
          Iterator<String> visita=Vertices.iterator(); // para mover la lista de vertices
            while(visita.hasNext()){
          	  VERTICE=visita.next(); // se extrae el vertice
          	  Index=Vertices.indexOf(VERTICE); // se extrae indice
              if(Visitado[Index]==false){
                   DFS(Vertices,Index,adyacentes,Visitado); // se envia a la funcion DFS para empezar a visitar los vertices
               		cont++;
               }
            }
            
            System.out.println(cont);

	}

   void DFS(ArrayList<String> Vertices,int INDEX,ArrayList<ArrayList<Integer>>adyacentes, boolean visitado[] ){
		String VERTICE;
		int ADYACENTE=0;//indica un vertice adyacente
	    ArrayList<Integer>L=new ArrayList<Integer>();
		Iterator<Integer>l=L.iterator();
		visitado[INDEX]=true; // se marca como visitado el vertice
        VERTICE=Vertices.get(INDEX);
       //  System.out.println(VERTICE+"\n");// imprime vertice visitado
        for(int i=0;i<adyacentes.get(INDEX).size();i++){
        	ADYACENTE=adyacentes.get(INDEX).get(i);
        	if(visitado[ADYACENTE]==false){	
              DFS(Vertices,ADYACENTE,adyacentes,visitado);
        	}

        }
    
    }


	public static void main(String[] args) {
		
		/*OJO: caso especial
		 C
		 AB ---> 2*/
		Scanner scan = new Scanner(System.in);
		String e = new String();

		int num_casos= scan.nextInt();
		scan.nextLine();
		scan.nextLine();
		

		while(num_casos!=0){

			num_casos--;
			String E_MaxGrado = scan.nextLine();

			ArrayList<ArrayList<Integer>> adyacentes = new ArrayList<ArrayList<Integer>>(); 
			ArrayList<String> aristas = new ArrayList<String>(); 
			ArrayList<String> vertices = new ArrayList<String>();
			
			E_MaxGrado = E_MaxGrado+E_MaxGrado;
			//System.out.println(E_MaxGrado);
			aristas.add(E_MaxGrado);
			boolean verticeUnico=true;

			do{
				//mientras haya aristas que ingresar
				e = scan.nextLine();
				if (e.equals("")==false) {
					verticeUnico=false;
					aristas.add(e);

				}

			}while(e.equals("")==false);

			if (verticeUnico==true){ //si no se ingresaron aristas, y existe solo un vertice
				System.out.println(1);
				continue;
			}

			problema2 p = new problema2();
	    	p.cargarLista(vertices,aristas,adyacentes);

			

		}


		
     }
}