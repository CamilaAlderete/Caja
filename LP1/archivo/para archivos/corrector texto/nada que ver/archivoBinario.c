#include<stdio.h>
#include<stdlib.h>
/*ARCHIVO BINARIO*/
//#define NOMBRE_ARCHIVO "/home/camila/Escritorio/c/archivo/empleados.bin"
typedef struct persona
{
	char *nombre;// si no se sabe tamanio, dejar en puntero
	int edad;
	double salario;
}persona;

int escribeArchivo(persona empleados[],int dim, const char *nombreArchivo);
int leeArchivo(persona empleados[], int dim, const char *nombreArchivo);
void imprime(persona empleados[],int dim);
void imprimePersona(persona *persona);

int main()
{
	persona empleados[]={{"juan",25,5000}, {"ana",30,6000},{"luis",40,7000}, {"fran",50, 8000}};
	int dim=sizeof(empleados)/sizeof(persona);
	persona empleadosEntrada[dim];
	char *nombreArchivo="Data.bin";

	/*pasara datos de empleados a archivo*/
	if(!escribeArchivo(empleados,dim,nombreArchivo)){
		puts("\nerror escritura\n");
		return 1;
	}
	/*pasara detos de archivo a empleados entrada*/
	if(!leeArchivo(empleadosEntrada,dim,nombreArchivo)){
		puts("\nError lectura\n");
		return 2;
	}

	imprime(empleadosEntrada,dim);//en empleadosEntrada ya esta copiada la info del archivo


	return 0;
}

int escribeArchivo(persona empleados[],int dim, const char *nombreArchivo){
	FILE *F=NULL;
	F=fopen(nombreArchivo,"wb");
	if(F==NULL){
		return 0;
	}

	int i;
	for (int i = 0; i < dim; ++i)
	{
		/*fwrite() escribe un buffer de cualquier tipo de dato en un archivo binario*/
		fwrite(&empleados[i],sizeof(persona),1,F);/*estructura persona,dim. persona, 1,puntero a archivo*/
	}
	fflush(F);//limpiar el canal de datos
	fclose(F);
	return 1;
}
int leeArchivo(persona empleados[], int dim, const char *nombreArchivo){/*empleadosEntrada es aqui empleados*/

	FILE *F;
	F=fopen(nombreArchivo,"rb");
	if(F==NULL) return 0;

	int i;
	for (int i = 0; i < dim; ++i)
	{
		/*fread() para leer de un archivo n bloques de bytes y almacenar lo leido en un buffer*/
		fread(&empleados[i], sizeof(persona),1,F);
		/*pasa datos de archivo a empleado(empleado entrada)*/
	}
	//al leer no hace falta fflush, solo cuando se escribe
	return 1;

}

void imprime(persona empleados[],int dim){/*empleadosEntrada es aqui empleados*/

	int i;
	for (int i = 0; i < dim; ++i)
	{
		imprimePersona(&empleados[i]);
		puts("\n");
	}
}

void imprimePersona(persona *persona){
	printf("Nombre:%s\n",persona->nombre);
	printf("Edad:%d\n",persona->edad);
	printf("salario:%lf\n",persona->salario);
	

}