#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include"funcion.h"

int main()
{
	  printf("\nBIENVENIDO AL MENU\n");
	  
	  int opcion;
	  
	  do{
	  	printf("Ingrese una opcion valida\n 1-Pasar a arbol\n 2- Cargar palabra incorrecta->correcta \n 3-Corregir texto\n 4-salir");
	  	 scanf("%d",&opcion);
	  	 limpiar_entrada();
	  }while(opcion<1 || opcion>4);
	  
	  while(opcion!=4){
	  	
	  	  if(opcion==1){
	  		 pasar_a_arbol();	
			}
			if(opcion==2){
				Cargar_en_diccionario();
			}
			if(opcion==3){
				corregir_texto();
			}
			do{
				printf("\ningrese una opcion valida\n");
				scanf("%d",&opcion);
				limpiar_entrada();
			}while(opcion<1 && opcion>4);
	   }
	  
	   printf("SALIENDO DEL MENU");
 	
	return 0;
}

void corregir_texto(){
    int k=0;
    char cadena[10];
    char correccion[10];
    OUT=fopen("out.txt","w");
    T=fopen("input.txt","r");
    if( OUT==NULL || T==NULL){
    	puts("error al abrir un archivo");
    }
    else if(OUT!=NULL && T!=NULL){
       while(!feof(T)){
           k++;
          fscanf(T,"%s",cadena);
           contp++;
           strcpy(correccion,buscar(raiz,cadena));
           if(!strcmp(" ",correccion)){
           printf("\nCORRECCION ENVIADA:%s",correccion);
           fwrite(cadena,sizeof(cadena),1,OUT);
           if(k==4){
           	break;
           }
          /* 	fprintf(OUT,"%s\t",cadena);
           	if(contp%12==0){
           		fprintf(OUT,"\n");
           	}*/
           
           }

         else{
         	printf("\nCORRECCION ENVIADA:%s",correccion);
         	fwrite(correccion,sizeof(correccion),1,OUT);

          /* 	printf("correccion:%s\n",correccion);
           /*	fprintf(OUT,"%s\t",correccion);
           	if(contp%12==0){
           		fprintf(OUT,"\n");
           	}*/
     //      	 contc++;
           } 
       	}    
    }
    fclose(OUT);
    fclose(T);
    //printf("Se corrigio el texto con exito\n cantidad de palabras:%d\n cantidad de palabras corregidas:%d\n",contp,contc);

}

char *buscar(Nodo *raiz,char* cadena){
   int r=0;
  if(raiz){
    r=strcmp(cadena,raiz->D.p_incorrecta);
    printf("\ncompara %s y %s",cadena, raiz->D.p_incorrecta);
  }
  if(!raiz){
  	return " ";
  }
  if(r==0){
  	return raiz->D.p_correcta; 
  }
  else if(r>0){
  	return buscar(raiz->der,cadena); 
  }
  else if(r<0){
  	return buscar(raiz->izq,cadena);
  }
}

void Cargar_en_diccionario(){
    palabra NUEVO;
    FD=fopen("diccionario.dat","ab");
    if(FD==NULL){
    	puts("Error al abrir el archivo");
    }
    else{
    	puts("ingrese la palabra mal escrita y su correccion\n");
    	scanf("%[^\n]",NUEVO.p_incorrecta);
    	limpiar_entrada();
    	scanf("%[^\n]",NUEVO.p_correcta);
    	limpiar_entrada();
       if(buscar_en_arbol(raiz,NUEVO)){
           puts("ya existe en el diccionario"); 
        }
        else{
           insertar_en_arbol(&raiz,NUEVO);// se inserta en el arbol
           fwrite(&NUEVO,sizeof(palabra),1,FD);// se escribe en el archivo        	
        }
    }
    visualizar(raiz);
    fclose(FD);
}

int buscar_en_arbol(Nodo*raiz,palabra NUEVO){
    int r;
    if(raiz){
    	r=strcmp(NUEVO.p_correcta,raiz->D.p_correcta);
    }

   if(!raiz){
        return 0;
    }
    else if(r==0){
        return 1;        
    }
    else if(r<0){
    	return buscar_en_arbol(raiz->izq,NUEVO);
    }
    else if(r>0){
    	return buscar_en_arbol(raiz->der,NUEVO);
    }
}



void limpiar_entrada(){

  char c;
    while((c=getchar())!='\n'&& c!=EOF){
    }

}

void pasar_a_arbol(){
    palabra AUX={"",""};

    FD=fopen("diccionario.dat","rb");
    if(FD==NULL){
      puts("Error al abrir el archivo\n");

    }
    else{
    	while(!feof(FD)){
    		fread(&AUX,sizeof(palabra),1,FD);
    		 insertar_en_arbol(&raiz,AUX);
    	}
    	visualizar(raiz);
    }
    fclose(FD);
}

Nodo* crearnodo(palabra dato){

	Nodo* t;
	t=(Nodo*)malloc(sizeof(Nodo));
	t->der=t->izq=NULL;
	t->D=dato;
	return t;
}


void insertar_en_arbol(Nodo** raiz,palabra AUX){
    int r;// para la comparacion de palabras en la raiz y el nuevo nodo;
   if((*raiz)){  
    r=strcmp(AUX.p_correcta,(*raiz)->D.p_correcta); 
   }
    if(!(*raiz)){

    	*raiz=crearnodo(AUX);
    }
    else if(r<0){
    	insertar_en_arbol(&((*raiz)->izq),AUX);
    }
    else if(r>0){
      insertar_en_arbol(&((*raiz)->der),AUX);
    }

}
  

void visualizar(Nodo*raiz){
  if(raiz){
  	 visualizar(raiz->izq);
  	 printf("%s %s \n",raiz->D.p_correcta,raiz->D.p_incorrecta);
  	 visualizar(raiz->der);
   }
}



