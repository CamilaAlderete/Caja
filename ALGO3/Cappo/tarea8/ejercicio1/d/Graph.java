/* 
	G16
	Eric RuizDiaz
	Luis Pereira

	Trabajo hecho con la participacion y cooperacion de G03
*/
class Graph 
{ 
    
    public int V, E;    // V-> no. of vertices & E->no.of edges 
    public Arista arista[]; // collection of all edges 
 
    Graph(int V, int E) 
    { 
        this.V = V; 
        this.E = E; 
        arista = new Arista[E]; 
        for (int i=0; i<E; ++i) 
            arista[i] = new Arista(); 
    } 

    
  
   
} 