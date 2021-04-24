#include<stdio.h>
#include<stdlib.h>
#include"vete.h"

int main(){
	
	char nombreArchivo[]="servicios.dat";
	char Archivo[]="animales.dat";
	mostrarmenu(nombreArchivo,Archivo);
	
	return 0;
}

void mostrarmenu(char* nombreArchivo,char* Archivo){
	int opcion;
	do{
		printf("\n 1- Carga de servicios ");
		printf("\n 2- Carga de animales");
		printf("\n 3-Listado de servicios");
		printf("\n 4-Listado de animales");
		printf("\n 5-Elaborar");
		printf("\n 6-Informe");
		printf("\n 7-recorrer lista enlazada");
		printf("\n 0-Salir");
		printf("\nIngresar una opcion: ");
		scanf("%d", &opcion);
		limpiar_entrada();
		if(opcion){    /*validamos el ingreso*/
			system("cls");
			switch(opcion){
				case 1:
						Servicios(nombreArchivo);

						break;
				case 2:
				      Animales(Archivo);
						
						break;
				case 3:
                       lista_servicios(nombreArchivo);
						
						break;
				case 4:
                       
                       lista_animales(Archivo);
				        break;

                 case 5:
                          ordenar(nombreArchivo);
                          break;
                 case 6:
                         Informe(Archivo);
                           break;
			     case 7:
			             recorrer(cabeza);
			             break; 
			   	case 0:      
						printf("\nFin del programa");
						break;
			}
		} 
		else{
			printf("\nOpcion no contemplada\n");
			system("pause");
		}
	}while(opcion!=0);
}
void pausar(){
	
	printf("\nPresione ENTER para continuar");
	
	while(getchar() != '\n'){
	   getchar();
	}
}
		