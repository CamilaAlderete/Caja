/* 
	G16
	Eric RuizDiaz
	Luis Pereira

	Trabajo hecho con la participacion y cooperacion de G03
*/
public class Main{
     
    public static void main (String[] args) 
    { 
 

        Graph g1 = new Graph(6, 9);
        g1.arista[0].src = 1; 
        g1.arista[0].dest = 2; 
        g1.arista[0].weight = 2; 
        
        g1.arista[1].src = 1; 
        g1.arista[1].dest = 5; 
        g1.arista[1].weight = 5; 
       
        g1.arista[2].src = 1; 
        g1.arista[2].dest = 0; 
        g1.arista[2].weight = 4; 
       
        g1.arista[3].src = 0; 
        g1.arista[3].dest = 4; 
        g1.arista[3].weight = 5; 
        
        g1.arista[4].src = 5; 
        g1.arista[4].dest = 4; 
        g1.arista[4].weight = 4; 

        g1.arista[5].src = 5; 
        g1.arista[5].dest = 3; 
        g1.arista[5].weight = 8; 
  
       
        g1.arista[6].src = 5; 
        g1.arista[6].dest = 2; 
        g1.arista[6].weight = 2; 
        
        g1.arista[7].src = 2; 
        g1.arista[7].dest = 3; 
        g1.arista[7].weight = 7; 
        
        g1.arista[8].src = 4; 
        g1.arista[8].dest = 3; 
        g1.arista[8].weight = 6;  

        Kruskal k = new Kruskal();
        k.kruskal(g1);
    } 
}