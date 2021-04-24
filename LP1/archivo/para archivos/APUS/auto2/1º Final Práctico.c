/*  Alumno: Angel Lopez  
    CI: 4.268.726
    FP-UNA
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Funciones338.h"


int main()
{
    char opcion;
    do
    {
        system("cls");
        puts("\t\t\tBIENVENIDO AL PROGRAMA");
        puts("Seleccione una de las siguientes opciones");
        puts("1 - Carga de vehiculos");
        puts("2 - Carga de transferencias");
        puts("3 - Listado de vehiculos");
        puts("4 - Listado de transferencias");
        puts("5 - Impresion del informe");
        puts("0 - Salir");
        switch(opcion=getch())
        {
            case '1':   CargarVehiculos();
                        break;

            case '2':   CargarTransferencias();
                        break;

            case '3':   ImprimirVehiculos(CabezaVehiculo);
                        break;

            case '4':   ImprimirTransferencias(CabezaPersona);
                        break;

            case '5':   Informe();
                        break;

            case '0':   GuardarArchivos();
                        break;

            default:    system("cls");
                        puts("Opcion no valida, intente de nuevo");
                        system("pause");
        }
    } while(opcion!='0');
    return 0;
}
