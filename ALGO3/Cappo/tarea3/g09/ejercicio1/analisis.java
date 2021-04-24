
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.PrintWriter;
import java.io.File;
//Luis Pereira
//Eric Ruiz Diaz
//G16

public class analisis {
    
        Scanner sc = new Scanner(System.in);
        public static int[] miVector, vecOriginal;
        public void CargarVector(int size){
            int i;
            miVector = new int[size];
            vecOriginal = new int [size];
            for(i = 0; i< miVector.length; i++){
                miVector[i]= ThreadLocalRandom.current().nextInt(10000, 1000000);
                vecOriginal[i]= ThreadLocalRandom.current().nextInt(10000, 1000000);
            }
    }
    public void imprimir(){
        int i;
        System.out.print("\n \t\t ELEMENTOS DEL VECTOR\n");
        for(i = 0; i < miVector.length;i++){
            System.out.print(miVector[i] + "\t");
        }
    }
    public void quicksort(int A[], int izq, int der) {

        int pivote=A[izq]; // tomamos primer elemento como pivote
        int i=izq; // i realiza la búsqueda de izquierda a derecha
        int j=der; // j realiza la búsqueda de derecha a izquierda
        int aux;
 
        while(i<j){            // mientras no se crucen las búsquedas
        while(A[i]<=pivote && i<j) {
            i++; // busca elemento mayor que pivote
        }
        while(A[j]>pivote) {
            j--;         // busca elemento menor que pivote
        }
        if (i<j) {                      // si no se han cruzado                      
            aux= A[i];                  // los intercambia
            A[i]=A[j];
            A[j]=aux;
            }
        }
        A[izq]=A[j]; // se coloca el pivote en su lugar de forma que tendremos
        A[j]=pivote; // los menores a su izquierda y los mayores a su derecha
        if(izq<j-1) {
            quicksort(A,izq,j-1); // ordenamos subarray izquierdo
        }
        if(j+1 <der) {
            quicksort(A,j+1,der); // ordenamos subarray derecho
        }
    }
     public static int busqBinaria(int  vector[], int dato, int n){
        int centro,menor=0,mayor=n-1;
        while(menor<=mayor){
            centro=(mayor + menor)/2;
            if(vector[centro]==dato) {
                return centro;
            } else if(dato < vector [centro] ){
                mayor=centro-1;
            }
            else {
            menor=centro+1;
            }
        }
        return -1;
    }
    public static void busqLineal(int vector[], int dato, int n){
        for(int i = 0; i< n; i++){
            if(vector[i] == dato){
             return;   
            }
        }
    }
    
    public static void main(String[] args)throws Exception {
        


        
        PrintWriter printWriter = new PrintWriter("g09.txt","UTF-8") ;
        long t;
        long t1, t2;
        int size= 0;
        
        printWriter.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n");
       // printWriter.newLine();
        printWriter.printf("|\t\t|\t\t   Busqueda Binaria\t\t\t\t|\t\t\t\t   Busqueda lineal\t\t  \r\n");
        printWriter.printf("|\tN\t|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n");
        printWriter.printf("|\t\t|\tT(n) en ms   |\tT/N\t|   T/(N log, N )   |     T/N^2\t|\tT(n) en ms   |\tT/N\t|     T/(N log, N)    |     T/N^2     \r\n");                    
        printWriter.printf("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n");
       
        double tn2;
        double tn;
        double nlogn; 
        
        for(int i = 0; i<20 ; i++){
           
            size = size + 50000;
            
            analisis objeto = new analisis();
            objeto.CargarVector(size);
            objeto.quicksort(miVector,0,size-1);

            t1 = System.currentTimeMillis();
            for (int j = 0; j < size; j++){
                int key = ThreadLocalRandom.current().nextInt(10000, 1000000);
                busqBinaria(miVector,key,size); 
            }
            t2 = System.currentTimeMillis();
            t = (t2-t1);
            tn = (double)t/size;
            nlogn = (double)t/( size * (Math.log(size)/Math.log(2)));
            tn2 = (double)(t / (size  * size)); 
            printWriter.printf("|\t%d\t|\t%d\t         %.6f\t   %.6f\t\t  %.6f",size,t,tn , nlogn , tn2  );
             
        
        
            t1 = System.currentTimeMillis();
            for (int j = 0; j < size; j++){
                int key = ThreadLocalRandom.current().nextInt(10000, 1000000);
                busqLineal(vecOriginal,key,size); 
            }
            t2 = System.currentTimeMillis();
            t = t2-t1;
            tn = (double)t/size;
            nlogn =(double) (t)/(size * (Math.log(size)/Math.log(2)));
            tn2 = (double)(t / (size * size));
            printWriter.printf("\t|\t  %d\t\t%.6f\t      %.6f\t%.6f \r\n",t, tn,nlogn,tn2);
        }
        
        printWriter.printf("\nversion de java:\t8\r\nversion de windows:\tWindows 10 Home 64-bit (10.0, Build 16299)\r\nram:\t\t8gb\r\ncpu:\r\t\tIntel Core i7 6600U @ 2.50GHz\r\n");
        printWriter.close();
    }   
    
}

