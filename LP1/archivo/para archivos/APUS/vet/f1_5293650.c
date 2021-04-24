/*
Angel Lopez
CI: 4.268.726
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"archivos.h"
#include"listas.h"

int main (){
	
    int opc=1;
    
	while(opc != 0){
    	system ("cls");
        puts ("\t\t\t¡BIENVENIDO AL PROGRAMA!");
        printf ("\nSeleccione una de las siguientes opciones:\n");
        puts ("1 - Cargar servicio");
        puts ("2 - Cargar animal");
        puts ("3 - Listar servicios");
        puts ("4 - Listar animales");
        puts ("5 - Elaborar informe");
        puts ("6 - Informe");
        puts ("0 - Salir");
        scanf("%d",&opc);
        switch (opc)
        {
            case 1: system ("cls");
				CargarServicio();
				break;
            case 2: system ("cls");
            	CargarAnimal();
				break;
            case 3: system ("cls");
				ListarServicios();
				break;
            case 4: system ("cls");
				ListarAnimales();
				break;
			case 5: system ("cls");
				ElaborarInforme();
				break;
			case 6: system ("cls");
				Informe();
				break;
			case 0: break;

            default:  system ("cls");
            	puts ("ERROR: OPCION NO VALIDA, INTENTE DE NUEVO");
            	system ("pause");
        }
    }
    system ("cls");
    puts ("\nGRACIAS POR UTILIZAR EL PROGRAMA\n\n");
    system ("pause");
    return 0;
}

