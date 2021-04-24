#include <stdio.h>
#include<string.h>
#include <stdlib.h>
#include "arbol.h"
#include"archivo.h"


void inicializar(char buffer[],int dim){
	for (int i = 0; i < dim; ++i)
	{
		buffer[i]='\0';
	}
}
void corregirArchivo(FILE* ptr_input,nodo* raiz,FILE* ptr_output){
	
	int contador=0;
	char buffer[100]={'\0'};
	char scontador[4]={0};	

	nodo* p=NULL;

	
	fscanf( ptr_input,"%s",buffer);


	while(!feof(ptr_input)){
		printf("\npalabra a ser corregida: %s\n",buffer);
		//corregirPalabra(buffer,raiz,&contador);//pasa el buffer a ser corregida

		p=buscar(raiz,&buffer[0]);

		if (p!=NULL)
		{
			fprintf(ptr_output, "%s ",p->dato.p_correcta);//guarda en archivo buffer corregida
			contador++;
		}else{
			fprintf(ptr_output, "%s ",buffer);//palabra correcta, sin modificacion
		
		}
		
		inicializar(buffer,100);
		
		fscanf( ptr_input,"%s",buffer);
		//fgets(buffer,99,ptr_input);//toma una nueva buffer/////////%[^\n]
		
	}

		
	puts("\ncontador de palabras corregidas:");
	sprintf(scontador,"%d",contador);

	fprintf(ptr_output,"\nnumero de palabras corregidas: %s\n",scontador);//pasa numero de palabras corregidas a archivo

	
	
}



void cargaDiccionario(nodo** raiz, char diccionario[]){
	/*carga datos del archivo binario  a un arbol*/
	registro aux;
	FILE* ptr_diccionario;
	ptr_diccionario=fopen(diccionario,"rb");
	if(ptr_diccionario==NULL){
		puts("Error al abrir diccionario");
		exit(-1);
	};

	fread(&aux, sizeof(registro),1,ptr_diccionario);
	while(!feof(ptr_diccionario)){
		//paso dato de archivo binario a aux
		//printf("palabra del diccionario corecta e incorrecta:%s\n%s\n",aux.p_correcta,aux.p_incorrecta);
		insertar(&(*raiz),aux);//inserto el dato en arbol
		fread(&aux, sizeof(registro),1,ptr_diccionario);
	}
	

	fclose(ptr_diccionario);
	
}

int agregaDiccionario(nodo** raiz,char diccionario[]){
	/*agrega palabras a diccionario*/

	system("clear");
	registro dato;
	FILE* ptr_diccionario=NULL;
	ptr_diccionario=fopen(diccionario,"ab+");
	if (ptr_diccionario==NULL) return 0;
	int op;
	do{
		system("clear");
		puts("/nIngrese palabra correcta e incorrecta, 1 para agregar,2 para salir\n");
		scanf("%d",&op);
		if(op==1){
			scanf("%s",dato.p_correcta);
			scanf("%s",dato.p_incorrecta);

			system("clear");

			fwrite(&dato,sizeof(registro),1,ptr_diccionario);//guarda en archivo
			insertar(&(*raiz),dato);//inserta en arbol
		}
	}while(op!=2);

	fclose(ptr_diccionario);

	return 1;
}

/*void corregirPalabra(char buffer[],nodo* raiz,int* contador){
	
	int i;//auxiliar
	int ini=0;
	int fin=ini+1;//inicio y fin de palabra
	
	int bandera=0;
	while(bandera!=1){	
		//busca inicio y fin de una palabra
		puts("\nciclo para busqueda palabra\n");

		while(buffer[fin]!=' ' && buffer[fin]!='.' && buffer[fin]!=','&& buffer[fin]!='\0'){
			fin++;
			printf("%d\n",fin);	
		} 
		//avanzo mientras haya letras//

		char aux[10]={'\0'};//donde voy a guardar una palabra
	
		int posAux=0;
		i=ini;
		

		//int n_caracteres_copiados=0;
		

		//aux=(char*)malloc(sizeof(char)*(fin-ini)+1);
		puts("\ncopia palabra\n");
		printf("inicio cadena:%d\n",ini);
		while(i<=fin){
			//copia palabra en aux[]
			aux[posAux]=buffer[i];
			printf("%c\n",buffer[i]);
			i++;
			posAux++;
			//n_caracteres_copiados++;

		}

		//printf("caracteres copiados:%d\n",n_caracteres_copiados);
		aux[i]='\0';

		nodo* p=NULL;

		printf("\npalabra a  modificar:%s\n",aux);
		
		//busco palabra en mi arbol, le mando n_caacteres coppiados-1 xq el final no cuenta
		p=buscar(raiz,&aux[0],strlen(aux));
		
		if(p!=NULL){
			printf("\npalabra correcta:%s\n",p->dato.p_correcta);
		}

		if(p!=NULL){
			
			i=ini;
			//reemplaza palabra incorrecta
			while(i<=fin){
				buffer[i]=p->dato.p_correcta[i];
				i++;
			}

			*contador++;//una palabra corregida mas
			printf("palabras corregidas:%d",*contador);
		}else{
			puts("no pasa nada...\n");
		}
		
		getchar();
		if(buffer[ini]=='\0'){
			 bandera=1;
			 break;	
		}

		ini=fin++;

	}



}*/