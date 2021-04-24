/*
	G16
	Luis Pereira
	Eric Ruiz Diaz
	
	Con ayuda y participacion de G03 Camila Alderete

	Representacion en lista enlazada del grafo

	Este codigo realiza la transpuesta en un tiempo de O(|V| + |E|)
*/

public class Main {
	public static void main(String[] args) {

		/*Graph gt1=new Graph(5);

		gt1.addArista(0,1);
		gt1.addArista(1,3);
		gt1.addArista(1,2);
		gt1.addArista(2,0);
		gt1.addArista(2,4);
		gt1.addArista(3,4);
		gt1.addArista(4,3);
		gt1.imprimir();*/

		Graph gt2=new Graph(8);

		gt2.addArista(0,1);
		gt2.addArista(1,2);
		gt2.addArista(1,5);
		gt2.addArista(1,4);
		gt2.addArista(2,3);
		gt2.addArista(2,6);
		gt2.addArista(3,2);
		gt2.addArista(3,7);
		gt2.addArista(4,0);
		gt2.addArista(4,5);
		gt2.addArista(5,6);
		gt2.addArista(6,5);
		gt2.addArista(7,6);
		gt2.addArista(7,3);

		gt2.imprimir();

		Graph inverso_gt1=gt1.setInversa();
		inverso_gt1.imprimir();
	}
}