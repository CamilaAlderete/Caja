/*
Angel López
CI: 4.268.726
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"archivos.h"
#include"listas.h"

int main (){
    int opc;

    do
    {
        system ("cls");
        puts ("\t\t\t¡BIENVENIDO AL PROGRAMA!");
        printf ("\nSeleccione una de las siguientes opciones:\n");
        puts ("1-Agregar seccion A ");
        puts ("2-Agregar seccion B ");
        puts ("3-Seleccion");
        puts ("4-Informe");
        puts ("0- Salir");
        scanf("%d",&opc);
        switch (opc)
        {
            case 1: system ("cls");
                AgregarA();
                break;

            case 2: system ("cls");
                AgregarB();
                break;

            case 3: system ("cls");
                A=B=cantSeleccionados=0;
                Seleccion();
                break;
            case 4: system("cls");
                Informe();
                break;
            case 0: break;

            default:  system ("cls");
                      puts ("ERROR: OPCION NO VALIDA, INTENTE DE NUEVO");
                      system ("pause");
        }
    }
    while (opc != 0);
    system ("cls");
    puts ("\nGRACIAS POR UTILIZAR EL PROGRAMA\n\n");
    system ("pause");
    return 0;
}

