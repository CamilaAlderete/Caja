class UnlimitedKnapSack{

	/*	
		obs.: existe cantidad ilimitada de items

		Pesos: 		4 1 2 
		Beneficio: 	9 2 1 
		Capacidad de la mochila: 7


		beneficio:			0 2 4 6 9 11 13 15 
		capacidad:  		0 1 2 3 4  5  6  7

		peso | beneficio
		  4		 9							 |
		  1      2			  | | |    
		  2      1                            


		4+1+1+1 = 7
		9+2+2+2 = 15
		
*/

	void knapSack(int peso[],int beneficio[],int w){

		int tabla[]= new int[w+1];
		int items[]=new int[w+1];
		tabla[0]=0;

		for (int i=1; i<tabla.length; i++) { // c=1, c=2,..., c=w
			max(tabla,peso,beneficio,i,items);
		}



		int i=tabla.length -1;
		printDatos(i,tabla,peso,items);

	}

	void printDatos(int i, int tabla[],int peso[],int items[]){

		System.out.println("\nbeneficio | item tomados");
		while(i>0){
			System.out.print(tabla[i]+"   |   ");
			System.out.print(peso[items[i]]+" \n");

			i=i-peso[ items[i] ];
		}

		System.out.println("\ntabla:");

		for (int a : tabla) {
			System.out.print(a+" ");
		}
		System.out.println();
		
	}

	void max(int tabla[],int peso[],int beneficio[],int c,int items[]){

		int diferencia; 
		for (int i=0; i<peso.length; i++) { //prueba con todos items disponibles
			
			diferencia = c - peso[i]; //capacidad - peso en i

			if ( diferencia >=0 ) {
				
				if ( beneficio[i]+tabla[diferencia] > tabla[c] ) {
					tabla[c]= beneficio[i]+tabla[diferencia];
					items[c]=i;//cual item tome
				}
			}

		}

	}

	void print(int v[]){
		for (int a: v) {
			System.out.print(a+" ");
			
		}

		System.out.println();

	}

	public static void main(String[] args) {
		int peso[]={4,1,2};
		int beneficio[]={9,2,1};
		int w=7;

		UnlimitedKnapSack obj=new UnlimitedKnapSack();

		System.out.print("Pesos: ");
		obj.print(peso);
		System.out.print("Beneficio: ");
		obj.print(beneficio);
		System.out.println("Capacidad de la mochila: "+w);

		obj.knapSack(peso,beneficio,w);

		


	
	}	
}