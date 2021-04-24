import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

/* 
	G16
	Eric RuizDiaz
	Luis Pereira

	Trabajo hecho con la participacion y cooperacion de G03
*/

public class Grafo {
    private int V; // Numero de vertices

    // Lista de Array para representacion de lista de adyacencia
    private LinkedList<Integer> ady[];
	private static 	boolean ans = false;
	
    @SuppressWarnings("unchecked")
    Grafo(int v) {	
        V = v;
        ady = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            ady[i] = new LinkedList<>();
    }

    // Method to add an edge into the graph
    // Metodo para anhadir una arista al grafo
    void addArista(int v, int w) {
        ady[v].add(w); // Add w to v's list.
    }

    /**
     * Un metodo recursivo para contar tdos los caminos de u hasta d
     */
    int contarCaminosUtil(int u, int d, boolean visitado[], int numCaminos) {
        // Marcar el nodo actual como visitado
        visitado[u] = true;

        // Si el vertice actual es el mismo que el destino se incrementa el contador;
		if (u == d) {
            numCaminos++;
        }
		
        // Recursion para todos los vertices adyacentes al actual
        else {
            Iterator<Integer> i = ady[u].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visitado[n]) {
                    numCaminos = contarCaminosUtil(n, d, visitado, numCaminos);
                }
            }
        }

        visitado[u] = false;
        return numCaminos;
    }
	
	boolean tieneCiclo() {
		List<Integer> visitado = new ArrayList<>();
		for (int i = 0; i < ady.length; ++i) {
		  if (tieneCiclo(i, visitado)) {
			return true;
		  }
		}
		return ans;
	  }

	  private boolean tieneCiclo(int nodo, List<Integer> visitado) {
		if (visitado.contains(nodo)) {
		  ans = true;
		  return true;
		}
		visitado.add(nodo);
		for (Integer nextNode : ady[nodo]) {
		  if (tieneCiclo(nextNode, visitado)) {
			ans = true;
			return true;
		  }
		}
		visitado.remove(visitado.size() - 1);
		ans = false;
		return false;
	  }

    /* Retorna el conteo de caminos desde s hasta d*/
    int contarCaminos(int s, int d) {
        // Mark all the vertices as not visited
        boolean visitado[] = new boolean[V];
        Arrays.fill(visitado, false);

        // LLamada al metodo recursivo para contar los caminos
        int numCaminos = 0;
        numCaminos = contarCaminosUtil(s, d, visitado, numCaminos);
        return numCaminos;
    }

    public static void main(String args[]) {

		/*
		Caso de prueba 1
		*/
		
		System.out.println("Caso de prueba 1");
		Grafo g = new Grafo(5);
        g.addArista(0, 1);
        g.addArista(0, 2);
        g.addArista(0, 3);
        g.addArista(2, 1);
        g.addArista(1, 3);
		g.addArista(2, 4);
		g.addArista(4, 3);
		
		if(g.tieneCiclo()){
			System.out.println("El grafo tiene un ciclo");
		}else{
			int s = 2, d = 3;
			String num = "impar";
			if( (g.contarCaminos(s, d) % 2 ) == 0)
				num = "par";
			System.out.println("El numero de caminos del vertice 's' a 't' es " + num);
		}
		
		/*
		Caso de prueba 2
		*/
		
		System.out.println("\nCaso de prueba 2");
		g = new Grafo(5);
        g.addArista(0, 1);
        g.addArista(0, 2);
        g.addArista(0, 4);
		g.addArista(1, 3);
        g.addArista(1, 4);
        g.addArista(2, 3);
		g.addArista(2, 4);
		g.addArista(3, 4);
		
		if(g.tieneCiclo()){
			System.out.println("El grafo tiene un ciclo");
		}else{
			int s = 2, d = 3;
			String num = "impar";
			if( (g.contarCaminos(s, d) % 2 ) == 0)
				num = "par";
			System.out.println("El numero de caminos del vertice 's' a 't' es " + num);
		}
		
		/*
		Caso de prueba 3
		*/
		
		System.out.println("\nCaso de prueba 3");
		g = new Grafo(5);
        g.addArista(0, 1);
        g.addArista(0, 2);
        g.addArista(0, 3);
        g.addArista(1, 3);
        g.addArista(2, 1);
		g.addArista(2, 4);
		g.addArista(3, 0);
		g.addArista(4, 3);
		
		if(g.tieneCiclo()){
			System.out.println("El grafo tiene un ciclo");
		}else{
			int s = 2, d = 3;
			String num = "impar";
			if( (g.contarCaminos(s, d) % 2 ) == 0)
				num = "par";
			System.out.println("El numero de caminos del vertice 's' a 't' es " + num);
		}
		
		
	}
}