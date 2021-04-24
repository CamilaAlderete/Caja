#include<stdio.h>
#include<stdlib.h>

typedef struct {
	
};

typedef struct {
	
};

typedef struct fecha{
	int dia;
	int mes;
	int anio;
}Fecha;

typedef struct horario{
	int hra;
	int min;
}Horario;

void mostrarmenu(char* nombreArchivo);
void Cargar();
void informe();
void pausar();

int main(){
	
	char nombreArchivo[]="";
	
	mostrarmenu(nombreArchivo);
	
	return 0;
}

mostrarmenu(char* nombreArchivo){
	int opcion;
	
	do{
		system("cls");
		printf("\n");
		printf("\n");
		printf("\n");
		printf("\n\n");
		printf("Ingresar una opcion: ");
		scanf("%d", &opcion);
		
		if(opcion){    /*validamos el ingreso*/
			system("cls");
			switch(opcion){
				case 1:
						system("cls");
						
						system("pause"); /*aqui podemos reemplazar por la funcion pause*/
						break;
				case 2:
						system("cls");
						
						system("pause");
						break;
				case 3:
						system("cls");
						
						system("pause");
						break;
				case 4:
						printf("\nFin del programa");
						system("pause");
						break;
			}
		} 
		else{
			printf("\nOpcion no contemplada\n");
			system("pause");
		}
	}while(opcion!=);
}
void pausar(){
	printf("Presione ENTER para continuar");
	
	while(getchar() != '\n')
	getchar();
}