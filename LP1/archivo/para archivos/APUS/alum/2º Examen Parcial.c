#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>
#include "funciones.h"


int main()
{
    char opcion;
    do
    {
        system("cls");
        puts("\t\t\tBIENVENIDO AL PROGRAMA");
        puts("Seleccione una de las siguientes opciones");
        puts("1 - Crear archivo");
        puts("2 - Pasar de archivo a lista");
        puts("3 - Agregar nuevo elemento");
        puts("4 - Imprimir lista");
        puts("5 - Pasar de lista a archivo");
        puts("0 - Salir");
        switch(opcion=getch())
        {
            case '1':   CrearArchivo();
                        break;

            case '2':   PasarAL();
                        break;

            case '3':   system("cls");
                        puts("Nuevo alumno");
                        printf("Apellido: ");
                        fflush(stdin);
                        scanf("%[^\n]", &ALU.apellido);
                        printf("Nombre: ");
                        fflush(stdin);
                        scanf("%[^\n]", &ALU.nombre);
                        printf("CI: ");
                        scanf("%d", &ALU.ci);
                        printf("Promedio: ");
                        scanf("%f", &ALU.promedio);
                        AgregarNuevo(ALU, Cabeza);
                        break;

            case '4':   ImprimirLista(Cabeza);
                        break;

            case '5':   PasarLA();
                        break;

            case '0':   break;

            default:    system("cls");
                        puts("Opcion no valida, intente de nuevo");
                        Sleep(2000);
        }
    } while(opcion!='0');
    return 0;
}
