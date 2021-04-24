import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/*

  G16
  Eric RuizDiaz
  Luis Pereira

  Trabajo hecho con la participacion y cooperacion de G03

*/

 
public class ArbolExpansionMax
{
    private List<Arista> aristas;
    private int numDeVertices;
    public static final int valorMIN = -1;
    private int visitado[];
    private int arbolExpansion[][];
 
    public ArbolExpansionMax(int numDeVertices)
    {
        this.numDeVertices = numDeVertices;
        aristas = new LinkedList<Arista>();
        visitado = new int[this.numDeVertices + 1];
        arbolExpansion = new int[numDeVertices + 1][numDeVertices + 1];
    }
 
    public void arbolExpansionMax(int matrizDeAdyacencia[][])
    {
        boolean finished = false;
        for (int origen = 1; origen <= numDeVertices; origen++)
        {
            for (int destino = 1; destino <= numDeVertices; destino++)
            {
                if (matrizDeAdyacencia[origen][destino] != valorMIN && origen != destino)
                {
                    Arista arista = new Arista();
                    arista.vertIni = origen;
                    arista.vertFin = destino;
                    arista.peso = matrizDeAdyacencia[origen][destino];
                    matrizDeAdyacencia[destino][origen] = valorMIN;
                    aristas.add(arista);
                }
            }
        }
        Collections.sort(aristas, new AristaComparator());
        MirarCiclo mirarCiclo = new MirarCiclo();
        for (Arista arista : aristas)
        {
            arbolExpansion[arista.vertIni][arista.vertFin] = arista.peso;
            arbolExpansion[arista.vertFin][arista.vertIni] = arista.peso;
            if (mirarCiclo.mirarCiclo(arbolExpansion, arista.vertIni))
            {
                arbolExpansion[arista.vertIni][arista.vertFin] = 0;
                arbolExpansion[arista.vertFin][arista.vertIni] = 0;
                arista.peso = valorMIN;
                continue;
            }
            visitado[arista.vertIni] = 1;
            visitado[arista.vertFin] = 1;
            for (int i = 0; i < visitado.length; i++)
            {
                if (visitado[i] == 0)
                {
                    finished = false;
                    break;
                } else
                {
                    finished = true;
                }
            }
            if (finished)
                break;
        }
        System.out.println("El Arbol de Expansion Maximo es:\n");
        for (int i = 1; i <= numDeVertices; i++)
            System.out.print("\t" + i);
        System.out.println("\n");
        for (int origen = 1; origen <= numDeVertices; origen++)
        {
            System.out.print(origen + "\t");
            for (int destino = 1; destino <= numDeVertices; destino++)
            {
                System.out.print(arbolExpansion[origen][destino] + "\t");
            }
            System.out.println();
        }
    }
 
    public static void main(String... arg)
    {
        int matrizAdyacencia[][] = {
			{0, 0,  0,  0,  0,  0,  0},
			{0, 0,  10, 8,  6,  0,  9},
			{0, 10, 0,  9,  20, 0,  0},
			{0, 8,  9,  0,  5,  3,  17},
			{0, 6,  20, 5,  0,  15, 5},
			{0, 0,  0,  3,  15, 0,  14},
			{0, 9,  0,  17, 5,  14, 0},							
		};
        int numVertices = 6;
		
		
		System.out.println("\nLa Matriz de Adyacencia es:\n");
        for (int i = 1; i <= numVertices; i++)
            System.out.print("\t" + i);
        System.out.println("\n");
        for (int origen = 1; origen <= numVertices; origen++)
        {
            System.out.print(origen + "\t");
            for (int destino = 1; destino <= numVertices; destino++)
            {
                System.out.print(matrizAdyacencia[origen][destino] + "\t");
            }
            System.out.println();
        }
		System.out.println("\n\n");
		ArbolExpansionMax arbolExpansionMax = new ArbolExpansionMax(numVertices);
        arbolExpansionMax.arbolExpansionMax(matrizAdyacencia);
        
		Scanner scan = new Scanner(System.in);
		
		while(numVertices > 0){
			System.out.println("\n\nIngrese un numero de vertices para generar un grafo por consola\nPresione '0' para salir del programa");
			
			
			numVertices = scan.nextInt();
			
			if(numVertices > 0){
				matrizAdyacencia = new int[numVertices + 1][numVertices + 1];
		 
				System.out.println("Ingresa la matriz de adyacencia con peso del grafo");
				for (int i = 1; i <= numVertices; i++)
				{
					for (int j = 1; j <= numVertices; j++)
					{
                        System.out.println("Fila: "+i+" - Columna: "+j);
						matrizAdyacencia[i][j] = scan.nextInt();
						if (i == j)
						{
							matrizAdyacencia[i][j] = 0;
							continue;
						}
						if (matrizAdyacencia[i][j] == 0)
						{
							matrizAdyacencia[i][j] = valorMIN;
						}
					}
				}
				arbolExpansionMax = new ArbolExpansionMax(numVertices);
				arbolExpansionMax.arbolExpansionMax(matrizAdyacencia);
				
			}
		}
		scan.close();
	}
}
 
class Arista
{
    int vertIni;
    int vertFin;
    int peso;
}
 
class AristaComparator implements Comparator<Arista>
{
    @Override
    public int compare(Arista arista1, Arista arista2)
    {
        if (arista1.peso < arista2.peso)
            return 1;
        if (arista1.peso > arista2.peso)
            return -1;
        return 0;
    }
}
 
class MirarCiclo
{
    private Stack<Integer> stack;
    private int matrizDeAdyacencia[][];
 
    public MirarCiclo()
    {
        stack = new Stack<Integer>();
    }
 
    public boolean mirarCiclo(int matrizAdyacencia[][], int origen)
    {
        boolean hayCiclo = false;
        int numNodos = matrizAdyacencia[origen].length - 1;
 
        matrizDeAdyacencia = new int[numNodos + 1][numNodos + 1];
        for (int vertIni = 1; vertIni <= numNodos; vertIni++)
        {
            for (int vertFin = 1; vertFin <= numNodos; vertFin++)
            {
			matrizDeAdyacencia[vertIni][vertFin] = matrizAdyacencia[vertIni][vertFin];
            }
         }
 
         int visitado[] = new int[numNodos + 1];
         int elemento = origen;
         int i = origen;
         visitado[origen] = 1;
         stack.push(origen);
 
         while (!stack.isEmpty())
         {
             elemento = stack.peek();
             i = elemento;
             while (i <= numNodos)
             {
                 if (matrizDeAdyacencia[elemento][i] >= 1 && visitado[i] == 1)
                 {
                     if (stack.contains(i))
                     {
                         hayCiclo = true;
                         return hayCiclo;
                     }
                 }
                 if (matrizDeAdyacencia[elemento][i] >= 1 && visitado[i] == 0)
                 {
                     stack.push(i);
                     visitado[i] = 1;
                     matrizDeAdyacencia[elemento][i] = 0;// mark as labelled;
                     matrizDeAdyacencia[i][elemento] = 0;
                     elemento = i;
                     i = 1;
                     continue;
                  }
                  i++;
             }
             stack.pop();
        }
        return hayCiclo;
    }
}