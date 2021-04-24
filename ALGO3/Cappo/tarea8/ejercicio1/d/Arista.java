 /* 
	G16
	Eric RuizDiaz
	Luis Pereira

	Trabajo hecho con la participacion y cooperacion de G03
*/
 class Arista implements Comparable<Arista> 
    { 
        public int src, dest, weight; 
  
        // Comparator function used for sorting edges  
        // based on their weight 
        public int compareTo(Arista compareEdge) 
        { 
            return this.weight-compareEdge.weight; 
        } 
    }; 
