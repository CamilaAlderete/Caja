//Este trabajo fue realizado por Eric Ruiz Diaz y Luis Pereira G16
//Con colaboracion de G03 Camila Alderete

import java.math.*;
import java.util.*;

class BigMult{
	
	public static BigInteger desplazar_izq(BigInteger DATO, int offset){
		 BigInteger ANS;
		 String dato = DATO.toString();
		 for(int i = 0; i<offset; i++){
			dato = dato + "0";
		 }
		 ANS = new BigInteger(dato);
		 return ANS;
	}
	
	public static BigInteger diferentesSignos(BigInteger X,BigInteger Y){
		int ans = 1;	
		if(X.compareTo(BigInteger.ZERO) < 0 && Y.compareTo(BigInteger.ZERO) > 0){
			ans = -1;
		}else if(Y.compareTo(BigInteger.ZERO) < 0 && X.compareTo(BigInteger.ZERO) > 0){
			ans = -1;
		}		
		BigInteger ANS = BigInteger.valueOf(ans);
		
		return ANS;
	}
	
	public static BigInteger quickMulti(BigInteger X,BigInteger Y){
		BigInteger ANS;
		int n = Math.max(X.toString().length(),Y.toString().length());//n es el maximo de los dos numeros
		
		if(n == 1){
			ANS = X.multiply(Y);
		}else{

			BigInteger A,B,C,D,R1,R2,M1,M2,M3,S_M,P1,P2,ACSIGNO,BDSIGNO,AUXSIGNO;
			
			if(n%2 == 1)// si n es impar se agrega 1
				n++;
			
			int n2 = n/2; // n/2 se almacena

			A = X.divide(BigInteger.TEN.pow(n2));
			B = (X.subtract(A.multiply(BigInteger.TEN.pow(n2)))).abs();
			
			C = Y.divide(BigInteger.TEN.pow(n2));
			D = (Y.subtract(C.multiply(BigInteger.TEN.pow(n2)))).abs();
		
			R1 = A.subtract(B); //A - B
			R2 = D.subtract(C); //D - C
			
			AUXSIGNO = diferentesSignos(R1,R2);
			ACSIGNO  = diferentesSignos(A,C);
			BDSIGNO = diferentesSignos(B,D);
			
			M1 = (quickMulti(R1.abs(),R2.abs())).multiply(AUXSIGNO);//(A-B)*(D-C)
			M2  = (quickMulti(A.abs(),C.abs())).multiply(ACSIGNO); //A * C
			M3  = (quickMulti(B.abs(),D.abs())).multiply(BDSIGNO);// B * D
			
			S_M = M1.add(M2.add(M3)); //[M1 + M2 + M3]
			P1 = desplazar_izq(S_M,n2); //[S_AUX * 10^(N/2)]
			P2 = desplazar_izq(M2,n); // [A * C * 10^N]
			
			ANS = P1.add(P2.add(M3));// M_AUX + M_AUX2 + C2
			
		}
		return ANS;
	}

	public static BigInteger multiplicar(BigInteger num1, BigInteger num2){
			String X = num1.toString();
			String Y = num2.toString();
			String[] MP = new String[Y.length()];
			
			int i, j ,k;
			int aux;
			int buffer = 0;
			int ny = Y.length();
			int nx = X.length();
			
			for (i = ny - 1; i >= 0; i--){
				
				buffer = 0;
				MP[i] = "";
				
				for(k = 0; k < ny - i - 1; k++){
					MP[i] = MP[i] + "0";
				}
				for (j = nx - 1 ; j >= 0 ; j--){
	
					aux = Character.getNumericValue(Y.charAt(i)) * Character.getNumericValue(X.charAt(j)) + buffer;

					if(j == 0){
						MP[i] = String.valueOf(aux) + MP[i];
					}else{
						MP[i] = String.valueOf(aux%10) + MP[i];
						buffer = aux/10;
					}	
				}
			}
			int tam = MP.length;
			BigInteger[] vec = new BigInteger[tam];
			String number;
			
			for(i = 0; i < tam; i++){
				number = MP[i];
				if(i != 0){			
					vec[i] = new BigInteger(number).add(vec[i -1]);	
				}else{
					vec[i] = new BigInteger(number);
				}
			}
			return vec[tam-1];
		}
		

	public static void main(String[] args){
			
		BigInteger test [] = new BigInteger[40];
		String a = "";
		long t1,t2,tqm,tm;
		System.out.println("  +---------------------------------------------+");
		System.out.println("  | Cant de dig |        T(microsegundos)       |");
		System.out.println("  +-------------+-------------------------------+");
		System.out.println("  |      N      | Mult Recursiva|  Mult Clasica |");
		System.out.println("  +-------------+---------------+---------------+");
		int i = 0;
		
		for(int k = 5; k <= 200; k = k+5){
			for(int j = 0; j< 5; j++){
				a = a + "9";
			}
			test[i] = new BigInteger(a);
			
			t1 = System.nanoTime();
			quickMulti(test[i],test[i]); //multiplicacion metodo recursivo
			t2 = System.nanoTime();
			tqm = (t2 - t1)/1000;
			
			t1 = System.nanoTime();
			multiplicar(test[i],test[i]); //multiplicacioon iterativa
			t2 = System.nanoTime();
			tm = (t2 - t1)/1000;
			
			System.out.println("  |\t"+k+"\t|\t"+tqm+"\t|\t"+tm+"\t|");
		
			i++;
		}			
		System.out.println("  +-------------+---------------+---------------+");
	}
}