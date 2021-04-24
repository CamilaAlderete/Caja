/*
	G16
	Luis Pereira
	Eric Ruiz Diaz

	Fue hecho con ayuda y cooperacion de G03 Camila Alderete

	Para ambos casos de borrado será de O(1) porque
	en borrar arista es simplemente remover el "nodo" de la lista enlazada, es tiempo constante
	y borrar vertice es practicamente eliminar la lista enlazada completa, tiempo constante
*/


import java.util.LinkedList;
import java.util.Scanner;
public class GFG 
{

    static class Graph
    {
        int V;
        LinkedList<Integer> adjListArray[];
         
    
        Graph(int V)
        {
            this.V = V;
             
            
            adjListArray = new LinkedList[V];
             
            
            for(int i = 0; i < V ; i++){
                adjListArray[i] = new LinkedList<>();
            }
        }
    }
     
    
    static void addEdge(Graph graph, int src, int dest)
    {
 
        graph.adjListArray[src-1].addFirst(dest-1);
        graph.adjListArray[dest-1].addFirst(src-1);
    }
      

    static void borrarArista(Graph graph, int src, int dest){
    	graph.adjListArray[src-1].remove(graph.adjListArray[src-1].indexOf(dest-1));
    	graph.adjListArray[dest-1].remove(graph.adjListArray[dest-1].indexOf(src-1));
    }

    static void borrarVertice(Graph graph,int src){
    	graph.adjListArray[src-1].clear();
    }

    static void printGraph(Graph graph)
    {       
        for(int v = 0; v < graph.V; v++)
        {
            System.out.println("Lista de adyacencia del vertice "+ v);
            System.out.print("Cabeza");
            for(Integer pCrawl: graph.adjListArray[v]){
                System.out.print(" -> "+pCrawl);
            }
            System.out.println("\n");
        }
    }
      
   
    public static void main(String args[])
    {
        Scanner leer=new Scanner(System.in);
        System.out.println("Ingrese 99 para terminar la insercion");
        System.out.println("Ingrese la cantidad de vertices");
        int V =leer.nextInt();
        
        Graph graph = new Graph(V);
        int k=0,v1=0,v2=0;
        try{
        	System.out.println("Ingrese los vertices para unirlos");
	       	v1 =leer.nextInt();
	        v2 =leer.nextInt();
	        for(int i=0; v1!=99 || v2!=99; i++){
	        	addEdge(graph, v1, v2);	
	        	System.out.println("Ingrese los vertices para unirlos");
	        	v1 =leer.nextInt();
	        	v2 =leer.nextInt();
        	}
        }catch(Exception e){
        	System.out.println("Ingresó una cantidad mayor de vertices");
        }
        
        v1=0;
        v2=0;
        System.out.println("sig");
        printGraph(graph);
        System.out.println("Ingrese las aristas para borrar");
       	v1 =leer.nextInt();
        v2 =leer.nextInt();
        for(int i=0; v1!=99 || v2!=99; i++){
        	borrarArista(graph, v1, v2);	
        	System.out.println("Ingrese las aristas para borrar");
        	v1 =leer.nextInt();
        	v2 =leer.nextInt();
        }
        System.out.println("sig");
        printGraph(graph);
       
       	System.out.println("Ingrese el vertice para borrar");
       	v1 =leer.nextInt();
       	borrarVertice(graph,v1);
       
        
        System.out.println("sig");
        printGraph(graph);
    }
}

