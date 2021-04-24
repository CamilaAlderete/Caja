/*  G 16
    Eric Ruiz Diaz
    Luis Pereira
    El algoritmo fue hecho con cierta disponibilidad de tiempo, se ha aprendido que es importante leer completamente y mas de una vez el problema cuando
        aun se ha entendido ya que puede llevar a ciertas irregularidades en el proceso de creacion del algoritmo
*/

#include "g16.h"


int main(){
    coordenada punto;   //variable usada para el ingreso de los datos

    lista tempS;        //lista enlazada temporal donde se cargara los datos que se ingresen


    tempS.sig=NULL;     //como la lista enlazada temporal posee un atributo llamado *sig lo inicializamos en NULL

    punto=entrada();    //funcion que llama a la entrada estandar y guarda los datos en la variable punto
    system("cls");

    int contador=0;

    while(punto.x != 99 && punto.y!= 99){   //usaremos como centinela el valor 99 para que deje de introducir los datos

        anadirElemento(&tempS,punto);       //la funcion "anadirElemento" hace que el valor leido por la entrada estandar se ingrese en la Lista Enlazada

        contador++;             //tendremos un contador para contar cuantos puntos se introducen, para luego dimensionar un arreglo unidimensional con ese valor

        punto=entrada();
        system("cls");
    }

    coordenada S[contador];     //creamos y dimensionamos un array con el valor del contador, la idea es ingresar en una lista enlazada y luego colocarlos en un vector
    puts("Mostrar Lista (Lista que acaba de ingresar)");
    mostrarLista(*tempS.sig);   //mostramos en pantalla la lista enlazada

    *S=vectorizarLista(*tempS.sig,contador,S);  //ingresamos la lista enlazada en un vector
    free(tempS.sig);

    ordenarLista(S,contador);   //ordenamos el vector de orden ascendente segun muestra el ejercicio
    //puts("\nLa lista 3 corresponde a la lista completa");
    dividirLista(S,contador);   //dividimos en dos sublistas el vector con los puntos


    /*int i=0;

    puts("\n");

    while(i<contador){
        printf("%.1f y %.1f\n",S[i].x,S[i].y);
        i++;
    }*/



    return 0;
}
