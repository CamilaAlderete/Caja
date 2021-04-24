#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"funciones.h"

int main (){
    int opcion;

    do
    {
        fflush(stdin);
        system ("cls");
        puts ("\t\t\t¡BIENVENIDO AL PROGRAMA!");
        printf ("\nSeleccione una de las siguientes opciones:\n");
        puts ("1 - Cargar vehiculo");
        puts ("2 - Carga de transferencias");
        puts ("3 - Listado de vehiculos");
        puts ("4 - Listado de transferencias");
        puts ("5 - Creacion de la lista");
        puts ("6 - Impresion del informe y salida");
        puts ("0 - Salir");
        scanf("%d",&opcion);
        switch (opcion)
        {
            case 1: system ("cls");
                CargarVehiculo ();
                break;

            case 2: system ("cls");
                CargarTransferencia();
                break;

            case 3: system ("cls");
                ListarVehiculos();
                break;
            case 4: system("cls");
                ListarTransferencias();
                break;
            case 5: system("cls");
            	CreacionDeLista();
            	break;
			case 6: system("cls");
                Informe();
                break;
            case 0: break;

            default:  system ("cls");
                      puts ("ERROR: OPCION NO VALIDA, INTENTE DE NUEVO");
                      system ("pause");
        }
    }
    while (opcion != 0);
    system ("cls");
    puts ("\nGRACIAS POR UTILIZAR EL PROGRAMA\n\n");
    system ("pause");
    return 0;
}
