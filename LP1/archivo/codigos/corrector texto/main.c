#include <stdio.h>
#include<string.h>
#include <stdlib.h>
#include "arbol.h"
#include"archivo.h"
void menu(){
	puts("1.Generar archivo\n2.Cargar palabra incorrecta->correcta\n3.Ver arbol\n4.Salir");
}


int main(){

	int opcion;
	menu();
	scanf("%d",&opcion);

	char output[]="output.txt"; 
	char input[]="input.txt";
	char diccionario[]="diccionario.dat";
	FILE* ptr_input=NULL;
	FILE* ptr_output=NULL;
	nodo* raiz=NULL;

	
	while(opcion!=4){
		int i; 	
		if(opcion==1){
		  	
			ptr_input=fopen(input,"r");
			ptr_output=fopen(output,"a+");
			cargaDiccionario(&raiz,diccionario);
			//funciona, carga en arbol

			puts("\nDiccionario\n");	getchar();

		  	if(ptr_input==NULL || ptr_output==NULL || raiz==NULL){
		  	 	puts("\nerror al generar archivo\n");
		    }else{
		    	puts("\nSe creo archivo, se porcedera a corregir el texto\n");
		    	corregirArchivo(ptr_input,raiz,ptr_output);
		    	fclose(ptr_output);
				fclose(ptr_input);
		    }


		}else if(opcion==2){
			if(agregaDiccionario(&raiz,diccionario)){
				puts("\nError al agregar palabras al Diccionario\n");
			}
		}else if (opcion==3)
		{
			InOrden(raiz);
			getchar();
		}

		
		getchar();
		system("clear");
		menu();
		scanf("%d",&opcion);

	}
	return 0;
}