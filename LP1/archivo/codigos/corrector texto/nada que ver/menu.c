#include<stdio.h>

void menu(){
	
	  printf("\nBIENVENIDO AL MENU\n");
	  
	  int opcion;
	  
	  do{
	  	printf("Ingrese una opcion valida\n");
	  	 scanf("%d",&opcion);
	  }while(opcion<0 || opcion>3);
	  
	  while(opcion!=0){
	  	
	    if(opcion==1){
	  	 	
		}
		if(opcion==2){
		}
		if(opcion==3){
		}
		
		do{
			printf("\ningrese una opcion valida\n");
			scanf("%d",&opcion);
		}while(opcion<0 || opcion>3);
	  	
	  }
	  
	   printf("SALIENDO DEL MENU");
 }
	

