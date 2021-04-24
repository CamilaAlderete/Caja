/*
Grupo G16:
Luis Pereira
Eric RuizDiaz
2019
*/


public class ejecutor{
    public static void main ( String [] args ) {
        int tamanho = 0;
        long t;
        long t1, t2;
        double tn2;
        double tn;
        double nlogn;

        BST<Integer> bst = new BST<Integer>();

        // --------------------------- PEOR CASO NO MEJORADO ----------------------- //
        System.out.println("BST SIN EVITAR PEOR CASO");
        System.out.println("|\t N\t|  T(n) en ms\t|      T/N\t| T/(N log, N )\t|\tT/N^2\t     |");
        System.out.println("--------------------------------------------------------------------------------------");
        for(int i = 0; i < args.length; i++){  

            //Castear tamaño de arreglo
            tamanho = Integer.parseInt(args[i]);
            //Cargar los datos para el peor caso (ordenado ascendentemente)
            cargarDatosAscendentemente(tamanho,bst);

            //Capturar primer tiempo
            t1 = System.currentTimeMillis();

            //Buscar ultimo elemento (peor caso)
            bst.buscar(tamanho-1);

            //Captura tiempo final
            t2 = System.currentTimeMillis();
            
            t = t2-t1;

            tn = (double)t/tamanho;
            nlogn =(double) (t)/(tamanho * (Math.log(tamanho)/Math.log(2)));
            tn2 = (double)(t / (tamanho * tamanho));

            System.out.printf("|\t%d\t|\t%d\t|   %.6f    |   %.6f    |   %.14f",tamanho,t,tn , nlogn , tn2  );
            System.out.println(" |");
        }


        // --------------------------- PEOR CASO MEJORADO ----------------------- //

        //Reseteo
        bst = new BST<Integer>();

        System.out.println("\n\nBST CON MEJORA PARA EVITAR PEOR CASO");
        System.out.println("|\t N\t|  T(n) en ms\t|      T/N\t| T/(N log, N )\t|\tT/N^2\t     |");
        System.out.println("--------------------------------------------------------------------------------------");
        for(int i = 0; i < args.length; i++){  

            //Castear tamaño de arreglo
            tamanho = Integer.parseInt(args[i]);
            //Cargar los datos para el peor caso (ordenado ascendentemente)
            cargarDatosAscendentementeMejorado(tamanho,bst);

            //Capturar primer tiempo
            t1 = System.currentTimeMillis();

            //Buscar ultimo elemento (peor caso)
            bst.buscar(tamanho-1);

            //Captura tiempo final
            t2 = System.currentTimeMillis();
            
            t = t2-t1;

            tn = (double)t/tamanho;
            nlogn =(double) (t)/(tamanho * (Math.log(tamanho)/Math.log(2)));
            tn2 = (double)(t / (tamanho * tamanho));

            System.out.printf("|\t%d\t|\t%d\t|   %.6f    |   %.6f    |   %.14f",tamanho,t,tn , nlogn , tn2  );
            System.out.println(" |");
        }

    }

    public static void cargarDatosAscendentemente(int tamanho,BST<Integer> bst){
        for(int i = 0; i< tamanho; i++){
            //Agrega de la forma tradicional de BST
            bst.agregar(i);
        }
    }

    public static void cargarDatosAscendentementeMejorado(int tamanho,BST<Integer> bst){
        for(int i = 0; i< tamanho; i++){
            //Agrega de manera que quede balanceado
            bst.insertarMejorado(i);
        }
    }
}
