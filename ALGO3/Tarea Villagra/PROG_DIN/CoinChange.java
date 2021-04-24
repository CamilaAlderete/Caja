class CoinChange{

	void minCoinChange(int coin[],int value){

		int v[] = new int[value+1];//tabla de memoizacion

		//inicializacion tabla
		for (int i=0; i< v.length; i++) {
			v[i]=0; //ninguna moneda en i
		}

		//desde valor 1 hasta V
		for (int i=1; i<v.length;i++ ) {
			
			//busqueda de la menor diferencia, que dara la moneda de mayor denominacion
			int index = findMin(coin,i); 

			if (index==-1) { // i < moneda 
				continue;
			}

			if ( i-coin[index] == 0) { //i - moneda ==0
				v[i]=1;
			}else{					   //i - moneda > 0
				//numero de monedas calculadas con anterioridad + 1
				v[i]= v[ i-coin[index] ] + 1;
			}

		}

		print(v);

		System.out.println("\nNumero minimo de monedas: "+v[v.length-1]);

	}

	void print(int v[]){
		for (int i=0; i < v.length; i++) {
			System.out.print(v[i]+" ");
		}
	}

	int findMin(int coin[],int i){
		/*busqueda de la moneda con la mayor denominacion posible*/
		/*a mayor denominacion, menor numero de monedas se necesitan para completar V*/

		int min = Integer.MAX_VALUE;
		int diferencia,index=-1;

		for (int j=0; j<coin.length; j++) {
			
			diferencia= i-coin[j];
			
			if (diferencia>0 && diferencia<min) {
				
				min=diferencia;
				index=j;

			}else if (diferencia==0) {
				return j;
			}


		}

		return index;

	}

	public static void main(String[] args) {
		
		int coin[]={1,2,4};
		int value = 8;

		System.out.print("Monedas: ");
		CoinChange c= new CoinChange();
		c.print(coin);
		System.out.println("\nMonto:"+value);
		
		c.minCoinChange(coin,value);



	}

}