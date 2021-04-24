/*
	G16
	Luis Pereira
	Eric Ruiz Diaz
	
	Con ayuda y participacion de G03 Camila Alderete

	Representacion de matricial del grafo

	Este codigo realiza la transpuesta en un tiempo de O(|V|^2)
*/

public class Main {
		public static void main(String[] args) 
		{
			
			Node n1=new Node('a');
			Node n2=new Node('b');
			Node n3=new Node('c');
			Node n4=new Node('d');
			Node n5=new Node('e');
			Node n6=new Node('f');
			Node n7=new Node('g');
			Node n8=new Node('h');
			Node n9=new Node('i');
			Node n10=new Node('j');
			Node n11=new Node('k');
			Node n12=new Node('l');
			Node n13=new Node('m');
	
			
			Graph gt=new Graph();
			gt.addNode(n1);
			gt.addNode(n2);
			gt.addNode(n3);
			gt.addNode(n4);
			gt.addNode(n5);
			gt.addNode(n6);
			gt.addNode(n7);
			gt.addNode(n8);
			gt.addNode(n9);
			gt.addNode(n10);
			gt.addNode(n11);
			gt.addNode(n12);
			gt.addNode(n13);
			gt.setRootNode(n1);

			
			
			gt.connectNode(n2,n1);
			gt.connectNode(n3,n1);
			gt.connectNode(n4,n2);
			gt.connectNode(n4,n2);
			gt.connectNode(n6,n3);
			gt.connectNode(n7,n3);
			gt.connectNode(n8,n4);
			gt.connectNode(n9,n5);
			gt.connectNode(n10,n5);
			gt.connectNode(n10,n6);
			gt.connectNode(n12,n7);
			gt.connectNode(n11,n8);
			gt.connectNode(n11,n9);
			gt.connectNode(n12,n10);
			gt.connectNode(n13,n11);
			gt.connectNode(n13,n12);

		
			


			gt.bfs();
			System.out.println("\nBFS del grafo sin invertir");
			gt.set_inversa();
			

			System.out.println("\nBFS del grafo invertido");
			gt.bfs();
				
			
		}
	
	}