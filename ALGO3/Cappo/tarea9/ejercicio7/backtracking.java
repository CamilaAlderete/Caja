//Este trabajo fue realizado por Eric Ruiz Diaz y Luis Pereira G16
//Con colaboracion de G03 Camila Alderete

public class backtracking{
	public static int casos=0;
	

	static boolean backtrackAlfil(int solucion[],int etapa){
	
		if(etapa>=solucion.length){
			return false;
		}
		
		

		boolean exito=false;
		solucion[etapa]=-1;
			do{
				solucion[etapa]=solucion[etapa]+1;
					
				if(ValidarAlfil(solucion,etapa)){
					
					if(etapa!=solucion.length){	
						
						exito=backtrackAlfil(solucion,etapa+1);

					}	
				}else{
						
					exito=true;
					casos=casos+1;
		
				}

			}while(solucion[etapa]==solucion.length || exito);
		return exito;
	}


	private static boolean ValidarAlfil(int solucion[],int k){
		for(int i=0; i<k; i++){			
			if(solucion[k]-k==solucion[i]-i || solucion[k]+k==solucion[i]+k){
				//System.out.println("entro?");
				return false;
			}
		
		}
		return true;
	}
}