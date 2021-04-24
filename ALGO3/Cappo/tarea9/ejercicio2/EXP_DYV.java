//Este trabajo fue realizado por Eric Ruiz Diaz y Luis Pereira G16
//Con colaboracion de G03 Camila Alderete

public class EXP_DYV{
	public static long exp_dyv(long b, long e){
		long ans = 0;
		
		if (e < 1){ //aca se cumple que solo se haran las operacion de recursion si el exponente es positivo
			ans = 1; //si el exponente es menor a 1 retorna 1
		}
		else if(e == 1){
			ans = b; // si el exponente es 1, returna la base
		}
		else if(e % 2 != 0 ){ //recursion si el exponente es un numero impar
			ans = exp_dyv(b, (e-1)/2);     // (b ^ (n - 1)/2)^2 * b => b ^ (n - 1)/2 * b ^ (n - 1)/2 * b
			ans = ans * ans * b;   
			
		}else if(e % 2 == 0){//recursion si el exponente es par
			ans =  exp_dyv( b, e/2);	// (a^(e/2))^2 => a^(e/2)*a^(e/2)
			ans = ans * ans;
		}
		return ans;
	}
	
	public static long exp_fb(long b, long e){
		long ans = 1;
		for (int i = 0; i< e; i++){
			ans = ans * b;
		}
		return ans;
	}
	

	public static void main(String []args){
		long ans;
		long t1;
		long t2;
		long t_dyv;
		long t_fb;
		System.out.println("Exponente\tT(n) Divide Y Venceras\tT(n) Fuerza Bruta");
		for (int i = 10000; i <= 100000; i+= 10000){
			t1 = System.currentTimeMillis();
			ans = exp_dyv(2,i);
			t2 = System.currentTimeMillis();
			
			t_dyv = t2 - t1;
			
			t1 = System.currentTimeMillis();
			ans = exp_fb(2,i);
			t2 = System.currentTimeMillis();
			
			t_fb = t2 - t1;
			
			System.out.println(" "+i+"\t\t\t"+t_dyv+"\t\t\t"+t_fb);
			
		}
	}
}