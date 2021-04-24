/*
	G16
	Luis Pereira
	Eric Ruiz Diaz
	
	Trabajo hecho con la participacion y cooperacion de G03
*/
/* --------------------------------------------------------------------
    Radix con matriz que comienza en 1, resultado:
    101 23 23 23 34 45 65 67 89 90
    sin tener en cuenta el 0, hay problemas al momento de ordenar 
    linea 63

-----------------------------------------------------------------------*/
public class RadixSortMatriz {  
public static void main(String[] args) {  
        int i;  
        int[] a = {90,23,101,45,65,23,67,89,34,23};  
         for(i=0;i<10;i++){  
            System.out.printf("%d ",a[i]);
         }  
        System.out.println(); 
        radix_sort(a);    
        for(i=0;i<10;i++){  
            System.out.printf("%d ",a[i]);  
        }
    }  
  
    static int mayor(int a[])  
    {     
        //busqueda de mayor elemento en array
        int max=a[0], i;   
        for(i=1;i<10;i++)  
        {  
            if(a[i]>max)  
            max = a[i];  
        }  
        return max;  
    }  

    static void radix_sort(int a[])  
    {  
        int colas[][]=new int[10][1500];  
        int contador_cola[]=new int[10];  
        int i, c, f, fila, operaciones=0, divisor=1, max, pass;  
       
        max = mayor(a);

        while(max>0)  
        {   
            //cuantas divisiones por 10 pueden realizarse
            operaciones++;  
            max/=10;  
        }  

        for(pass=0;pass<operaciones;pass++) // desde 0 a numDivisionesPosibles 
        {  
            for(i=0;i<10;i++) {
                /*contador cola, donde i es la fila y el elemento en esa posicion es
                la cantidad de columnas ocupadas en esa fila*/
                contador_cola[i]=0;  
            }

            //se encola de acuerdo a los digitos
            for(i=0;i<10;i++)  
            {  
                //a[i]/divisor me dara el digito con que quiero trabajar

                /*fila = ( (((a[i]/divisor)==0)? 1: (a[i]/divisor)))%10;
                con matriz que no comienza en 0  
                */fila = ( a[i]/divisor )%10;
                colas[ fila ][ contador_cola[fila] ] = a[i];  
                contador_cola[fila] += 1;//en esa fila hay un elemento mas agregado  
            }  

            
            //se procede a desapilar
            i=0;  
            //System.out.println("Matriz: ");
            for(f=0;f<10;f++)  
            {  
                for(c=0;c<contador_cola[f];c++)  
                {  
                   // System.out.printf("%d ",colas[f][c]);
                    a[i] = colas[f][c];  
                    i++;  
                }
                //System.out.println();
  
            }

            //System.out.println();

            //se trabaja con las unidades, luego decenas, centenas ...
            divisor *= 10;  
        }  
    }  
}  