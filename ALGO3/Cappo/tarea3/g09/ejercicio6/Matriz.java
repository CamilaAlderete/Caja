//Eric Ruiz Diaz
//Luis Pereira
//G16
import java.util.Scanner;

public class Matriz {
    public static boolean contiene ( int [] [] m, int valor ) {
        int N = m.length;
        for ( int f = 0; f < N; f++ ){
            for ( int c = 0; c < N; c++ ){
                if ( m[f][c] == valor ){
                return true;
                }
            }
        }
        return false;
    }
    
    public static int[][] ordenar(int [][]m){
        int F= m.length;
        for( int i=0; i < F; i++){//ordena la matriz de abajo hacia arriba
            for( int j=0;j< F; j++){
                for(int x=0; x < F; x++){
                    for(int y=0; y <F; y++){
                        if(m[i][j] < m[x][y]){
                            int t = m[i][j];
                            m[i][j] = m[x][y];
                            m[x][y] = t;
                        }
                    }
                }
            } 
        }
        return m;
        
    }
    
    public static void main(String []args){
        int N;
        System.out.println("Ingrese el tamaño de la matriz\n");
        Scanner entrada=new Scanner(System.in);
        N=entrada.nextInt();
        int [][] m=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                m[i][j]=(int) (Math.random() * 100) + 1;
            }
        }
        System.out.println("------------------------\n");
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
               System.out.print(m[i][j]+"\t");
            }
            System.out.println("\n");
        }
        m=ordenar(m);
        System.out.println("------------------------\n");
        System.out.println("------------------------\n");
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
               System.out.print(m[i][j]+"\t");
            }
            System.out.println("\n");
        }
        System.out.println("------------------------\n");
        System.out.println("El valor de 100 se encuentra disponible:"+contiene(m,100));
    }
}
