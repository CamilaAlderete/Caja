#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int escribir(char nombre[]);
int copiar(char nombre[]);
/*/home/camila/Escritorio/c/archivo/texto.txt*/
int main()
{
	char nombre[]="texto.txt";
	if(!escribir(nombre)){
		puts("\nError al escribir en archivo\n");
		return 1;
	}

	if(!copiar(nombre)){
		puts("\nError al copiar texto\n");
		return 1;
	}
	/**/
	return 0;
}

int escribir(char nombre[]){
	FILE *ptrfile=NULL;
	ptrfile=fopen(nombre,"w");//abierto para escritura
	if(ptrfile==NULL) return 0;
	
	/*INGRESO DE TEXTO EN ARCHIVO*/

	fprintf(ptrfile,"Escritura en archivo txt\n");//ingresa texto en archivo
	char buffer[100]; int op=0; puts("\ningrese texto, 1 para salir\n");
	
	while(op!=1){
		scanf("%[^\n]",buffer);//ingresa texto
		
		fprintf(ptrfile, "%s",buffer);//pasa texto en archivo
		//fputs(buffer,ptrfile);//lo mismo que fprintf
		
		printf("%s\n",buffer);
		
		fprintf(ptrfile,"\n");//ingresa espacio

		

		puts("\ningrese texto, 1 para salir\n");
		scanf("%d",&op);
	}

	fclose(ptrfile);
	return 1;
}

int copiar(char nombre[]){

	FILE *ptrfile=NULL;
	ptrfile=fopen(nombre,"r");
	
	if(ptrfile==NULL){
		return 0;
	}

	char buffer[500];
	/*copia las lineas del archivo en un vector*/

	puts("\nLectura por lineas\n");
	while(feof(ptrfile)==0){
		fgets(buffer,500,ptrfile);
		printf("%s",buffer);
		/*imprime dos veces ultima linea*/
	}

	rewind(ptrfile);//situar el puntero del archivo al principio

	/*Copia caracter por caracter, de archivo a variable*/
	puts("\nLectura por caracteres\n");
	char c1[2]; char c2;

	while(feof(ptrfile)==0){
		//fgets(c1,2,ptrfile);//funciona
		//printf("%s",c);

		c2=fgetc(ptrfile);
		printf("%c",c2);
		/*al usar feof y c2, se imprime basura*/
	}
	
	
	
	fclose(ptrfile);

	return 1;
}