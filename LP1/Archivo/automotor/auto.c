#include<stdio.h>
#include<stdlib.h>
#include<string.h>

struct vehiculo{
	
	  char chapa[6];
	  char marca[10];
	  int modelo;
};

typedef struct vehiculo AUTO;



struct transferencia{
         char chapa[6];
         int cedula;
         char nombre[20];
         char apellido[20];
         int monto;
 };

 typedef struct transferencia TRANSFERENCIA;




 struct nodo{

    AUTO dato;
    struct nodo *siguiente;
};

 typedef struct nodo Nodo;

FILE* FAUTO;
  FILE* FT;
  int cont_auto=0;

 void cargar_autos(Nodo* cabeza);
 void limpiar_entrada();
void insertarOrden(Nodo **cabeza,AUTO entrada);
  Nodo* crearnodo(AUTO x);
  void recorrer(Nodo* cabeza);
  Nodo* buscar(Nodo **cabeza,TRANSFERENCIA T);
 void transferencias();

int main(){


  Nodo* cabeza=NULL;
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
			if(opcion=='c'){
				recorrer(cabeza);
			do{
				printf("\ningrese una opcion valida\n");
				opcion=getchar();
				limpiar_entrada();
			}while(opcion!='a'&& opcion!='b' && opcion!='c'&& opcion!='d' && opcion!='e');
	   }
	  
	   printf("SALIENDO DEL MENU");
    }	
	return 0;
}
void limpiar_entrada(){
    	char c;
    	while((c=getchar())!='\n' && c!=EOF){

    	}
    }

 void insertarOrden(Nodo** cabeza,AUTO entrada){
 	puts("insertando\n");
    Nodo* nuevo;
    nuevo=crearnodo(entrada);
    int r=0;
    if((*cabeza)){
     r=strcmp(entrada.chapa,(*cabeza)->dato.chapa);
    }

    if((*cabeza)==NULL){
        puts("entra\n");
        (*cabeza)=nuevo;
    }
    else if(r>0){

        nuevo->siguiente=*cabeza;
        *cabeza=nuevo;
    }
    else{
        r=strcmp(entrada.chapa,(*cabeza)->dato.chapa);
        Nodo*anterior,*p;
        anterior=p=*cabeza;
        
        while((p->siguiente!=NULL) && (r<0)){
            anterior=p;
            p=p->siguiente;
        }

       if(r>0)
          anterior=p;
          nuevo->siguiente=anterior->siguiente;
          anterior->siguiente=nuevo;
    }
     printf("%s",(*cabeza)->dato.chapa);

   puts("saliendo\n");
  }


   Nodo* crearnodo(AUTO x){
      puts("crea el nodo\n");
      Nodo* a;
      a=(Nodo*)malloc(sizeof(Nodo));
      strcpy(a->dato.chapa,x.chapa);
      strcpy(a->dato.marca,x.marca);
      a->dato.modelo=x.modelo;
      a->siguiente=NULL;
      return a;
   }

   void recorrer(Nodo *cabeza){

       Nodo* aux;
       aux=cabeza;
       while(aux!=NULL){
       	printf("%s\n",aux->dato.chapa);
       	aux=aux->siguiente;
       }
   }
  
  void cargar_autos(Nodo* cabeza){

     AUTO C;// variable
     int control=0;// para determinar la veces que se quiere ingresar datos
     FAUTO=fopen("auto.dat","wb");
     	if(FAUTO==NULL){
     	puts("error al abir el archivo");
     } 
     else{
          puts("ingrese la chapa\n");
          scanf("%[^\n]",C.chapa);
          limpiar_entrada();
          puts("Ingrese la marca\n");
          scanf("%[^\n]",C.marca); 
          limpiar_entrada();
          puts("Ingrese el modelo\n");
          scanf("%d",&C.modelo);
          limpiar_entrada();
          fwrite(&C,sizeof(AUTO),1,FAUTO);
          cont_auto++;
          insertarOrden(&cabeza,C);
          fclose(FAUTO);
        }
    }