//Este trabajo fue realizado por Eric Ruiz Diaz y Luis Pereira G16
//Con colaboracion de G03 Camila Alderete

public class ejer1_a{

	public static void main(String args[]) 
    { 
       int a[] = {1, 1, 1, 5, 5}; 
       int b[] = {1, 2, 3};
       int c[]= {7,3,7,2,7}; 
       int d[]= {1,3,5,5,6,5,2,3,5,5,1,5,1,5,3,5,1,5};

  		E_Mayoritario m = new E_Mayoritario();
		int r;  
		/*--------------------------------------------------------------*/
  		r = m.mayoritario(a,0,a.length-1);
  		m.print(a);
  		if (r!=-1) {
  			System.out.println("Hay elemento mayoritario: "+r+"\n");

  		}else{
  			System.out.println("No hay elemento mayoritario\n");
  		}

  		/*--------------------------------------------------------------*/

  		m.print(b);
  		r = m.mayoritario(b,0,b.length-1);
  		if (r!=-1) {
  			System.out.println("Hay elemento mayoritario: "+r+"\n");

  		}else{
  			System.out.println("No hay elemento mayoritario\n");
  		}
  		/*--------------------------------------------------------------*/

  		m.print(c);
  		r = m.mayoritario(c,0,c.length-1);
  		if (r!=-1) {
  			System.out.println("Hay elemento mayoritario: "+r+"\n");

  		}else{
  			System.out.println("No hay elemento mayoritario\n");
  		}

  		/*--------------------------------------------------------------*/
  		m.print(d);
  		r = m.mayoritario(d,0,d.length-1);
  		if (r!=-1) {
  			System.out.println("Hay elemento mayoritario: "+r+"\n");

  		}else{
  			System.out.println("No hay elemento mayoritario\n");
  		}

  		/*--------------------------------------------------------------*/
  		

  		


    } 

}

class E_Mayoritario{

	/*Tiempo: T(n) = 2T(n/2) + O(n)  ----> nlog(n) */
	public static int mayoritario(int a[],int ini, int fin){
		//if (ini<=fin) {
			
			if (ini==fin){//si hay un solo elemento
				return a[ini];
			}

			int k = (ini+fin)/2;

			int eIzq= mayoritario(a,ini,k);//busqueda en sub array izq
			int eDer = mayoritario(a,k+1,fin);//busqueda en sub array der

			if (eIzq == eDer) {
				return eIzq;
			}

			int fIzq = frecuencia(a,eIzq);
			int fDer = frecuencia(a,eDer);

			//quien tenga la mayor frecuencia sera el mayoritario parcial
			if (fIzq >  k) {
				return eIzq;
			}else if (fDer > k) {
				return eDer;
			}

			return -1;
		//}

		//return;

	}

	private static int frecuencia(int a[],int e){
		int suma=0;
		for (int i=0 ; i< a.length; i++) {
			
			if (a[i]==e) {
				suma++;
			}
		}

		return suma;
	}

	

	public void print(int a[]){
		for (int obj: a ) {
			System.out.print(obj+" ");
		}

		System.out.println();
	}


	/* tiempo no lineal

	public int mayoritario(int []a, int dim){

		if ((dim==1 || dim==2) && a[0]!=0) {
			return a[0];
		}
		if (a[0]==0) {
			return -1;
		}

		int []b= new int[dim];
		int i;
		for (i=0; i<dim; i++) {
			b[i]=0;
		}

		i=0;
		int j =0;
		for (i = 0; i<dim-1; i++) {
			if (a[i]==a[i+1]) {
				b[j]= a[i];
				j++;
			}/*else{
				if ((i<a.length-2) && a[i]==a[i+2]) {
					b[j]= a[i];
					j++;
				}
			}*/
		//}

		/*return mayoritario(b,j);



	}*/




}
