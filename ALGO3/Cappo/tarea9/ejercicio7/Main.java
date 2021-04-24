//Este trabajo fue realizado por Eric Ruiz Diaz y Luis Pereira G16
//Con colaboracion de G03 Camila Alderete


import java.util.Scanner;
/*

*/
public class Main{
	public static void main(String args[]){
		System.out.println("Ingrese la cantidad de alfiles");
		Scanner leer=new Scanner(System.in);
		int k=leer.nextInt();

		System.out.println("Ingrese n para el tablero");
		int n=leer.nextInt();

		int vector[]=new int[n];

		backtracking.backtrackAlfil(vector,0);

		System.out.println(backtracking.casos);
	}
}