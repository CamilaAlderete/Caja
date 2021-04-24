//Este trabajo fue realizado por Eric Ruiz Diaz y Luis Pereira G16
//Con colaboracion de G03 Camila Alderete


public class vuelto{
	public static int [] minimizarVuelto(long monto,int [] denominaciones){
	
		denominaciones=bubble(denominaciones);

		long cant=0;
	 	
	 	for(int i=0; i<denominaciones.length; i++){
	 		if(monto >= denominaciones[i]){
	 			
	 			cant=monto / denominaciones[i];
	 			System.out.println("Se necesitaran "+ cant + " billetes de " + denominaciones[i]);
	 			monto=monto-(cant*denominaciones[i]);
	 		}

		}
		System.out.println("Sobra "+monto);
		return denominaciones;
	}

	private static int[] bubble(int []vector){
		int aux;

		for(int i=0; i<vector.length; i++){

			for(int j=0; j<vector.length; j++){
				
				if(vector[i]>vector[j]){
					aux=vector[i];
					vector[i]=vector[j];
					vector[j]=aux;
				}
			
			}

		}
		for(int i=0; i<vector.length; i++){
			System.out.println("V["+i+"]="+vector[i]);
		}
		return vector;	
	}
}