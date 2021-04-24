/*
	Eric Ruiz Diaz
	Ruben Izembrandt
	G09
*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Graph 
{
	public Node rootNode;
	public ArrayList nodes=new ArrayList();
	public int[][] adjMatrix;
	int size;
	
	public void setRootNode(Node n)
	{
		this.rootNode=n;
	}
	
	public Node getRootNode()
	{
		return this.rootNode;
	}
	
	public void addNode(Node n)
	{
		nodes.add(n);
	}
	
	//This method will be called to make connect two nodes
	public void connectNode(Node start,Node end)
	{
		if(adjMatrix==null)
		{
			size=nodes.size();
			adjMatrix=new int[size][size];
		}

		int startIndex=nodes.indexOf(start);
		int endIndex=nodes.indexOf(end);
		adjMatrix[startIndex][endIndex]=1;
		adjMatrix[endIndex][startIndex]=1;
	}
	
	private Node getUnvisitedChildNode(Node n)
	{
		
		int index=nodes.indexOf(n);
		int j=0;
		while(j<size)
		{
			if(adjMatrix[index][j]==1 && ((Node)nodes.get(j)).visited==false)
			{
				return (Node)nodes.get(j);
			}
			j++;
		}
		return null;
	}
	
	public void bfs()
	{
		
		Queue q=new LinkedList();
		q.add(this.rootNode);
		printNode(this.rootNode);
		rootNode.visited=true;
		while(!q.isEmpty())
		{
			Node n=(Node)q.remove();
			Node child=null;
			while((child=getUnvisitedChildNode(n))!=null)
			{
				child.visited=true;
				printNode(child);
				q.add(child);
			}
		}
		clearNodes();
	}
	
	public Node bfs_find(char search_label)
	{
		
		Queue q=new LinkedList();
		q.add(this.rootNode);
		if(this.rootNode.label == search_label) {
			return this.rootNode;
		}
		rootNode.visited=true;
		while(!q.isEmpty())
		{
			Node n=(Node)q.remove();
			Node child=null;
			while((child=getUnvisitedChildNode(n))!=null)
			{
				child.visited=true;
				if(child.label == search_label){
					return child;
				}
				q.add(child);
			}
		}
		clearNodes();
		return null;
	}
	
	public void dfs()
	{
		Stack s=new Stack();
		s.push(this.rootNode);
		rootNode.visited=true;
		printNode(rootNode);
		while(!s.isEmpty())
		{
			Node n=(Node)s.peek();
			Node child=getUnvisitedChildNode(n);
			if(child!=null)
			{
				child.visited=true;
				printNode(child);
				s.push(child);
			}
			else
			{
				s.pop();
			}
		}
		clearNodes();
	}
	
	
	private void clearNodes()
	{
		int i=0;
		while(i<size)
		{
			Node n=(Node)nodes.get(i);
			n.visited=false;
			i++;
		}
	}
	
	
	private void printNode(Node n)
	{
		System.out.print(n.label+" ");
	}

	public void set_inversa(){
		/*
			En este codigo se emplea el uso de un grafo representado por una matriz,
			para hacer su inversa lo que haremos será cambiar de lugar las posiciones de las filas y columnas,
			es decir que por ejemplo el elemento G[1][2] = 1 ahora será G[2][1] = 1;
		*/
		int aux;
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				//System.out.printf("%d\t",this.adjMatrix[i][j]);
				aux=adjMatrix[i][j];
				adjMatrix[i][j]=adjMatrix[j][i];
				adjMatrix[j][i]=aux;
			}
			//System.out.println(" ");
		}
	}
}