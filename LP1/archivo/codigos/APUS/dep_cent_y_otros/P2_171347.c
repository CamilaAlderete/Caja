// Angel Lopez CI: 4.268.726
// DEV-C WINDOWS 10

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"2A.h"
#include"2L.h"
main(){
	
	int item,cant1,cant2,x;
	propietario autom;
	gana*cabeza=NULL;
	FILE*p,*q;
	q=p=NULL;
	menu();

	scanf("%d",&item);
	if(item!=5){
	while(item!=x){

		switch(item)
		{
			case 1://DEPARTAMENTO CENTRAL
				printf("Ingrese la cantidad de registros de esta seccion.\n");
				scanf("%d",&cant1);
				cargaA(cant1,&p);
				break;
			case 2: //OTROS DEPARTAMENTOS
				printf("Ingrese la cantidad de registros de esta seccion.\n");
				scanf("%d",&cant2);
				cargaB(cant2,&q);
				break;
			case 3: //seleccion de automoviles en reglas
				seleccion(&p,&q,&cabeza);
				break;
			case 4: //informe
				recorrerLista(cabeza);
				break;
			case 5:
				x=5;
				break;
		}
 
	system("pause");
	system("cls");
	menu();
	scanf("%d",&item);
	}	
	
	return 0;
}
