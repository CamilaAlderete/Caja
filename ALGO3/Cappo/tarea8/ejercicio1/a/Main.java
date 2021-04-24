import java.util.Iterator;
import java.util.Stack; 
import java.util.LinkedList; 

/*un grafo dirigido es llamado fuertemente conexo si
 para cada par de vértices u y v existe un camino de 
 u hacia v y un camino de v hacia u. */
  
class Digrafo 
{ 
    private int V;  
    private LinkedList<Integer> ListaAdj[];
  
    public Digrafo(int v) 
    { 
        V = v;
        ListaAdj = new LinkedList[v];

        //lista de adyacencia 
        for (int i=0; i<v; ++i) 
            ListaAdj[i] = new LinkedList(); 
    } 

     public void ComponentesConectados() 
    { 
        
        Stack pila = new Stack();
  
        //se hara un primer recorrido en profundidad 
        boolean visitado[] = new boolean[V]; 
        for(int i = 0; i < V; i++) 
            visitado[i] = false; 
  
        
        for (int i = 0; i < V; i++) //procesar todos los vertices
            if (visitado[i] == false) 
                apilarDfs(i, visitado, pila); 
                //Dfs y se apilaran los vertices visitados de acuerdo al tiempo de finalizacion
  
       //se halla transpuesta del grafo
        Digrafo gTranspuesta = transpuesta(); 
  
        //se hara un segundo recorrido en profundidad, pero en el grafo transpuesto 
        for (int i = 0; i < V; i++) 
            visitado[i] = false; 
  
        //Dfs de acuerdo al tiempo de finalizacion de los vertices, en el grafo transpuesto
        while (pila.empty() == false) 
        { 
            
            int v = (int )pila.pop(); 
  
            if (visitado[v] == false) 
            { 
                //recursivamente se procesaran e imprimiran componentes fuertemente conectados
                gTranspuesta.DFSUtil(v, visitado); 
                System.out.println(); 
            } 
        } 
    } 
  
  
    public void agregarArista(int v, int w)
    {
        ListaAdj[v].add(w);
    } 
  
    
    private void DFSUtil(int v,boolean visitado[]) 
    {  
        visitado[v] = true; 
        System.out.print(v + " "); 
  
        int w; 
  
        //Dfs 
        Iterator<Integer> i =ListaAdj[v].iterator(); 
        while (i.hasNext()) 
        { 
            w = i.next(); 
            if (!visitado[w]) 
                DFSUtil(w,visitado); 
        } 
    } 
  
   
    private void apilarDfs(int v, boolean visitado[], Stack pila) 
    { 
        
        visitado[v] = true; //vertice procesado
        Iterator<Integer> i = ListaAdj[v].iterator(); //procesar vertices adyacentes
        //DFs y apila
        while (i.hasNext()) 
        { 
            int w = i.next(); 
            if(!visitado[w]){ 
                apilarDfs(w, visitado, pila);//Dfs 
            }
        } 

        //System.out.println("Elemento pila: "+v);
        pila.push(v); 
    } 

     private Digrafo transpuesta() 
    { 
        Digrafo g = new Digrafo(V); 
        for (int v = 0; v < V; v++) 
        { 
            Iterator<Integer> i =ListaAdj[v].listIterator(); 
            while(i.hasNext()) 
                g.ListaAdj[i.next()].add(v); //g[i][v]
        } 
        return g; 
    } 
  

   
    
} 

public class Main{
    public static void main(String args[]) 
    { 
        Digrafo g = new Digrafo(8); 
        g.agregarArista(0, 1); 
        g.agregarArista(1, 4); 
        g.agregarArista(1, 5);
        g.agregarArista(1, 2);
        g.agregarArista(4, 0); 
        g.agregarArista(4, 5);
        g.agregarArista(5, 6);
        g.agregarArista(6, 5);
        g.agregarArista(2, 6);
        g.agregarArista(2, 3);
        g.agregarArista(3, 2);
        g.agregarArista(3, 7);
        g.agregarArista(7, 3);
        g.agregarArista(7, 6); 
  
        System.out.println("Componentes fuertemente conectados grafo 1"); 
        g.ComponentesConectados();

        Digrafo g1 = new Digrafo(5); 
        g1.agregarArista(0, 1); 
        g1.agregarArista(1, 2);
        g1.agregarArista(1, 3);
        g1.agregarArista(2, 0);
        g1.agregarArista(2, 4);
        g1.agregarArista(4, 3);
        g1.agregarArista(3, 4);

        System.out.println("Componentes fuertemente conectados grafo 2"); 
        g1.ComponentesConectados();
         
        


    } 
}