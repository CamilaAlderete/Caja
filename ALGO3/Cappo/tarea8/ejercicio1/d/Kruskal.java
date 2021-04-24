//Algoritmo de Kruskal: selecciona aristas de menor peso, evitando ciclos, para formar el arbol de expansion
//se utiliza los conjuntos disjuntos para detectar ciclos en el arbol de expansion creado hasta el momento

/* 
	G16
	Eric RuizDiaz
	Luis Pereira

	Trabajo hecho con la participacion y cooperacion de G03
*/
import java.util.*; 

class Kruskal{  

    public static void kruskal(Graph graph) 
    { 
        //se ordenan las aristas de acuerdo a su peso
        Arrays.sort(graph.arista);
  
        // v subconjuntos, donde cada arista es representante de su subconjunto
        sub_conjunto subsets[] = new sub_conjunto[graph.V]; 
        for(int i=0; i<graph.V; ++i){ 
            subsets[i]=new sub_conjunto();
            subsets[i].representante = i; 
            subsets[i].rango = 0; 
        }

        //aristas del arbol de expansion
        Arista arbol_expansion[] = new Arista[graph.V];
        for (int i=0; i<graph.V; ++i){
            arbol_expansion[i] = new Arista(); 
        }

        int j = 0, i = 0;
        while (j < graph.V - 1)
        { 
            //se toman todas aristas del grafo, de menor a mayor peso
            Arista arista_aux = new Arista(); 
            arista_aux = graph.arista[i];
  
            //se buscan representantes de los subconjuntos
            int v1 = find(subsets, arista_aux.src); 
            int v2 = find(subsets, arista_aux.dest); 
  
            
            if (v1 != v2)//si los vertices no estan an el mismo subconjunto (no forman ciclo)
            { 
                arbol_expansion[j] = arista_aux;//se toma arista para el arbol 
                Union(subsets, v1, v2);//se hace union de los subconjuntos
                j++;
            } 

            i++;
        } 

        print(arbol_expansion,j);
  
       
    }

    private static void print(Arista []v1, int j){
        System.out.println("Aristas del arbol de expansion"); 
        for (int i = 0; i < j; ++i) 
            System.out.println(v1[i].src+"  " +v1[i].dest+" = " + v1[i].weight); 
    } 

    private static int find(sub_conjunto subsets[], int i) 
    { 
        //encuentra raiz 
        if (subsets[i].representante != i) //si el mismo no es representante de su subconjunto
            subsets[i].representante = find(subsets, subsets[i].representante);//busca al "padre" 
  
        return subsets[i].representante; 
    } 
  
   
    private static void Union(sub_conjunto subsets[], int x, int y) 
    { 
        //union de subconjuntos por rango, quien tenga mayor rango sera representante
        if (subsets[x].rango < subsets[y].rango){ //si el rango x < rango y
            subsets[x].representante = y; // y sera representante 
        }else if (subsets[x].rango > subsets[y].rango){ //si rango x > rango y
            subsets[y].representante = x; //x sera representante
        }else{//si son iguales 
            subsets[y].representante = x; //x sera representante del conjunto y
            subsets[x].rango++; //aumenta rango de x
        } 
    } 
}