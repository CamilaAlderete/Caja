/*
	G16
	Luis Pereira
	Eric Ruiz Diaz
	
	Trabajo hecho con la participacion y cooperacion de G03
*/

import java.util.*;// para Array.fill

class Ordenacion{

  public static void radixSort(String[] array,char lower,char upper){
    int indiceMax = 0;//maxima longitud de cadena
   
    for(int i=0;i<array.length;i++){
      
      //hallar la mayor longitud de cadena
      if(array[i].length()-1 > indiceMax){
        indiceMax = array[i].length()-1;
      }

    }
    
    //se "encola" y "desencola" de acuerdo al ultimo caracter, penultimo, .... , primero
    for(int i=indiceMax; i>=0; i--){
      countingSort(array,i,lower,upper);
    }
  }
  
  
  private static void countingSort(String[] array,int indice,char lower,char upper){
    // el countingSort emula el comportamiento de una cola, necesaria en radixSort
    String[] temporal = new String[ array.length ];
    int[] contador = new int[ (upper-lower)+2 ];// 'a' a la 'z'
    Arrays.fill(contador,0);
    
    //"encola" de acuerdo al n-esimo caracter
    //se halla frecuencia
    for(int i=0; i<array.length ;i++){
      
      int indiceCaracter;
      
      if ( array[i].length()-1 < indice ) {
        indiceCaracter = 0;
      }else{
        indiceCaracter = (array[i].charAt(indice) - lower)+1;
      }

      contador[indiceCaracter]++;
    }

    //se halla el flujo
    for(int i=1;i<contador.length;i++){
      contador[i] += contador[i-1];
    }
    
    //ordena en temporal, "desencola"
    for(int i=array.length-1;i>=0;i--){
      int indiceCaracter;
     
      if ( array[i].length()-1 < indice ) {
        indiceCaracter = 0;
      }else{
        indiceCaracter = (array[i].charAt(indice) - lower)+1;
      }

      temporal[contador[indiceCaracter]-1] = array[i];
      contador[indiceCaracter]--;
    }
    

    //de temporal a final
    for(int i=0;i<temporal.length;i++){
      array[i] = temporal[i];   
    }

  }
  
  
}

public class Main{
  public static void main (String[] args) {
    String[] v = {"enelgumeno","celulosa", "soberbia","anarquia","monopolio","rotura","almibar","radiologia","mandato"};
   
   	for(String obj : v){
      System.out.println(obj);
    }

     System.out.println();

    Ordenacion m = new Ordenacion();

    m.radixSort(v,'a','z');//rango [a,z]

    for(String obj : v){
      System.out.println(obj);
    }
  }
}