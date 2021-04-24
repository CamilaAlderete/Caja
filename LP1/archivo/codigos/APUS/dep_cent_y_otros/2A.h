// Angel Lopez CI: 4.268.726
// DEV-C WINDOWS 10
#include<string.h>
typedef struct dato{
char nombre[20];
char apellido[20];
int chapa;
int chasis;
int anopag;
struct dato*siguiente;
} gana;

typedef struct Datos{
char nombre[20];
char apellido[20];
int chapa;
int chasis;
int  anopag;
} propietario;

void cargaA(int cantidad,FILE**p)//PARA EL DEPARTAMENTO CENTRAL!
{
propietario autom;
int i=1;
*p=fopen("datosG.dat","a+");
	if((*p)==NULL)
	*p=fopen("datosG.dat","w+");
	else{
			while(i<=cantidad)
			{
			    printf("Ingrese el nombre \n");
				scanf("%s",autom.nombre);
				fflush(stdin);

				printf("Ingrese el apellido \n");
				scanf("%s",autom.apellido);
				fflush(stdin);

				printf("Ingrese el chasis \n");
				scanf("%d",&autom.chasis);
//fflush(stdin);
                printf("Ingrese el año en que se pago \n");
				scanf("%d",&autom.anopag);
//fflush(stdin);
				fwrite(&autom,sizeof(propietario),1,*p);
				i++;
			}
 	}
 	fclose(*p);
}

void cargaB(int cantidad,FILE**q)//PARA OTROS DEPARTAMENTOS
{
propietario autom;
int i=1;
*q=fopen("datosH.dat","a+");
	if((*q)==NULL)
	*q=fopen("datosH.dat","w+");
	else{
			while(i<=cantidad)
			{
			    printf("Ingrese el nombre \n");
				scanf("%s",autom.nombre);
				fflush(stdin);

				printf("Ingrese el apellido \n");
				scanf("%s",autom.apellido);
				fflush(stdin);

				printf("Ingrese el chasis \n");
				scanf("%d",&autom.chasis);
//fflush(stdin);
				printf("Ingrese el año pagado  \n");
				scanf("%d",&autom.anopag);
				fflush(stdin);
				fwrite(&autom,sizeof(propietario),1,*q);
				i++;
			}
 	}
 	fclose(*q);
}

