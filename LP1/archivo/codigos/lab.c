
#include <stdio.h>
#include "estructuras.h"
#include "archivos.h"

void imprimirMenu();

int main(){
    char opcion;
    do{
        imprimirMenu();
        fflush(stdin);
        scanf("%c", &opcion);
        switch(opcion){
            case 'a':
                    system("cls");
                    cargaCamion();
                    system("pause");
                    break;
            case 'b':
                    system("cls");
                    cargaSalida();
                    system("pause");
                    break;
            case 'c':
                    system("cls");
                    listaCamion();
                    system("pause");
                    break;
            case 'f':
                    system("cls");
                    listaSalida();
                    system("pause");
                    break;
            case 'g':
                    system("cls");
                    procesoFinMes();
                    system("pause");
                    break;
            case 'h':
                    system("cls");
                    informeMantenimiento();
                    system("pause");
                    break;
            case 'i':
                    break;
            default:
                    printf("Opcion no valida, intente de nuevo\n");
                    system("pause");
        }
    }while(opcion != 'i');
    return 0;
}

void imprimirMenu(){
    system("cls");
    printf("a) Carga de camiones\n");
    printf("b) Carga de salidas\n");
    printf("c) Listado de camiones\n");
    printf("f) Listado de salidas\n");
    printf("g) Proceso de Fin de Mes\n");
    printf("h) Informe de mantenimiento\n");
    printf("i) Salir\n");
}

