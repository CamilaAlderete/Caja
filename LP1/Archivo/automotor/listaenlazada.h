#include<stdio.h>
#include<string.h>
#include<stdlib.h>

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

  FILE* FAUTO;
  FILE* FT;
  int cont_auto=0;
  
 struct nodo{

    AUTO dato;
    struct nodo *siguiente;
};

 typedef struct nodo Nodo;

  void insertarOrden(Nodo **cabeza,AUTO entrada);
  Nodo* crearnodo(AUTO x);
  void recorrer(Nodo* cabeza);
  Nodo* buscar(Nodo **cabeza,TRANSFERENCIA T);
 void transferencias();
 void informe(Nodo*cabeza);
 void  cargar_autos(Nodo*cabeza);
 void limpiar_entrada();


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

   Nodo* buscar(Nodo **cabeza,TRANSFERENCIA T){

       Nodo*nuevo;
       nuevo=*cabeza;
     
       while(nuevo!=NULL){
       	   if(strcmp(nuevo->dato.chapa,T.chapa)==0)
       	   	 return nuevo;
       	   else{
              nuevo=nuevo->siguiente;
       	   	}
       }


    }

   void cargar_autos(Nodo* cabeza){

     AUTO C;// variable
     int control=0;// para determinar la veces que se quiere ingresar datos
     FAUTO=fopen("automotores.dat","a+b");
     if(FAUTO==NULL){
     	FAUTO=fopen("automotores.dat","w+b");
     	if(FAUTO==NULL)
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
   
   void transferencias(){

       TRANSFERENCIA TA;// variable
        // oara determinar la veces que se quiere ingresar datos
     FT=fopen("transaccion.dat","a+b");
     if(FT==NULL){
     	FT=fopen("transaccion.dat","w+b");
     	if(FT==NULL)
     	puts("error al abir el archivo");
     } 
     else{
       
          puts("ingrese la chapa\n");
          scanf("%[^\n]",TA.chapa);
          limpiar_entrada();
          puts("Ingrese el nombre del propietario n");
          scanf("%[^\n]",TA.nombre); 
          limpiar_entrada();
          puts("Ingrese el apellido\n");
          scanf("%[^\n]",TA.apellido);
          limpiar_entrada();
          puts("ingrese el nº de cedula\n");
          scanf("%d",&TA.cedula);
          limpiar_entrada();
          puts("ingrese el monto\n");
          scanf("%d",&TA.monto);
          limpiar_entrada();
          fwrite(&TA,sizeof(TRANSFERENCIA),1,FT);
       } 
       fclose(FT);
   
    }    

    void limpiar_entrada(){
    	char c;
    	while((c=getchar())!='\n' && c!=EOF){

    	}
    }
  
  void informe(Nodo* cabeza){
     int cont_monto=0;
  
     TRANSFERENCIA T;
     Nodo* recibe;//lo que se recibe de la busqueda;
     FT=fopen("transaccion.dat","rb");
     puts("-----------------TRANSFERENCIA-----------------\n");
     if(FT==NULL){
     puts("error al abrir archivo\n");
     }
     else{
      fread(&T,sizeof(TRANSFERENCIA),1,FT);
      while(!feof(FT)){
          recibe=buscar(&cabeza,T);
          if(recibe!=NULL);
          cont_monto=cont_monto+T.monto;
          printf("nº chapa:%s\n",T.chapa);
          printf("modelo:%d\n",recibe->dato.modelo);
          printf("marca:%s\n",recibe->dato.marca);
          puts("----PROPIETRARIO----\n");
          printf("Nombre: %s\n Apellido:%s\n",T.nombre,T.apellido);
          printf("CI:%d\n",T.cedula);
          }
           fread(&T,sizeof(TRANSFERENCIA),1,FT);
      }
    
    fclose(FAUTO);
    fclose(FT);
  }

 