#include<stdio.h>
#include"listaenlazada.h"
#include<stdlib.h>

	int main()
{
    Nodo*cabeza=NULL;
	  printf("\nBIENVENIDO AL MENU\n");
	  
	  int opcion;
	  
	  do{
	  	printf("Ingrese una opcion valida\n a-Carga de autos\n b- Cargar de transacciones\n c-Listado de autos\n d-Informe\n");
	  	opcion=getchar();
	  	 limpiar_entrada();
	  }while(opcion!='a'&& opcion!='b' && opcion!='c'&& opcion!='d'&& opcion!='e');
	  
	  while(opcion!='e'){
	  	
	  	  if(opcion=='a'){
	  		 cargar_autos(cabeza);	
			}
			if(opcion=='b'){
				transferencias();
			}
			if(opcion=='c'){
				recorrer(cabeza);
			}
			if(opcion=='d'){
                informe(cabeza);
			}
         
			do{
				printf("\ningrese una opcion valida\n");
				opcion=getchar();
				limpiar_entrada();
			}while(opcion!='a'&& opcion!='b' && opcion!='c'&& opcion!='d' && opcion!='e');
	   }
	  
	   printf("SALIENDO DEL MENU");
 	
	return 0;
}
  
