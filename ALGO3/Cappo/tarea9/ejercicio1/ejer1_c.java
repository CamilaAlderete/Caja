//Este trabajo fue realizado por Eric Ruiz Diaz y Luis Pereira G16
//Con colaboracion de G03 Camila Alderete

public class ejer1_c{

	public static void main(String args[]) 
    { 
       int a[] = {1, 1, 1, 5, 5}; 
       int b[] = {1, 2, 3};
       int c[]= {7,3,7,2,7}; 
       int d[]= {1,3,5,5,6,5,2,3,5,5,1,5,5,5,3,5,1,5};

  		E_Mayoritario m = new E_Mayoritario();
		int r;  
		/*--------------------------------------------------------------*/
  		r = m.mayoritarioLineal(a);
  		m.print(a);
  		if (r!=-1) {
  			System.out.println("Hay elemento mayoritario: "+r+"\n");

  		}else{
  			System.out.println("No hay elemento mayoritario\n");
  		}

  		/*--------------------------------------------------------------*/

  		m.print(b);
  		r = m.mayoritarioLineal(b);
  		if (r!=-1) {
  			System.out.println("Hay elemento mayoritario: "+r+"\n");

  		}else{
  			System.out.println("No hay elemento mayoritario\n");
  		}
  		/*--------------------------------------------------------------*/

  		m.print(c);
  		r = m.mayoritarioLineal(c);
  		if (r!=-1) {
  			System.out.println("Hay elemento mayoritario: "+r+"\n");

  		}else{
  			System.out.println("No hay elemento mayoritario\n");
  		}

  		/*--------------------------------------------------------------*/
  		m.print(d);
  		r = m.mayoritarioLineal(d);
  		if (r!=-1) {
  			System.out.println("Hay elemento mayoritario: "+r+"\n");

  		}else{
  			System.out.println("No hay elemento mayoritario\n");
  		}

  		/*--------------------------------------------------------------*/
  		

  		


    } 

}

class E_Mayoritario{

	
	//timepo lineal  Boyer–Moore t(n) = O(n)
	public static int mayoritarioLineal(int[] a) { 
		int count = 0;
		int i, dim = a.length;
		int mayoritario=a[0];

		//posible candidato a elemento mayoritario
		for (i = 0; i < dim; i++) {
		    if (count == 0){
		        mayoritario = a[i];
		    }

		    if (a[i] == mayoritario){ 
		        count++;
		    }else{
		        count--;
		    }
		}

		count = 0;
		//comprobacion de si es mayoritario
		for (i = 0; i < dim; i++){
		    if (a[i] == mayoritario){
		        count++;//frecuencia
		    }
		}

		//si el elemento fue insertado mas de dim/2 veces
		if (count > dim/2){
		    return mayoritario;
		}

		return -1;

	}

	public void print(int a[]){
		for (int obj: a ) {
			System.out.print(obj+" ");
		}

		System.out.println();
	}






}
