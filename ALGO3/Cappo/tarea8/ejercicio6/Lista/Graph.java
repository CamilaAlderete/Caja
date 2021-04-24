import java.util.LinkedList;

public class Graph{

	int n; 
    LinkedList<Integer> adjListArray[]; 
      
    Graph(int v) 
    { 
        this.n = v; 
          
        adjListArray = new LinkedList[v]; 
          
        for(int i = 0; i < v ; i++){ 
            adjListArray[i] = new LinkedList<>(); 
        } 
    } 

    public void addArista(int src, int dest) 
    { 
        this.adjListArray[src].add(dest);  
    } 
    public Graph setInversa(){
    	Graph inverso = new Graph(this.n);
    	int aux=0;
    	for(int v = 0; v < this.n; v++) 
        { 
            for(Integer pCrawl: this.adjListArray[v]){ 
                inverso.addArista(pCrawl,v);
            } 
        }

        return inverso;

    }
    public void imprimir() 
    {        
        for(int v = 0; v < this.n; v++) 
        { 
            System.out.println("Lista de adyacencia del vertice "+ v); 
            System.out.print("Cabeza"); 
            for(Integer pCrawl: this.adjListArray[v]){ 
                System.out.print(" -> "+pCrawl); 
            } 
            System.out.println("\n"); 
        }
        System.out.println("----------------"); 
    } 
}
