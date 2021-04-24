 #include<stdio.h>
 #include<stdlib.h>
 #include<string.h>

 struct SERVICIOS {
	 char codigo[4];
	 char raza[15];
	 char responsable[25];
	 int cantidad;//cantidad de animales
};
  
 typedef struct SERVICIOS servicios;

 struct fecha{
	int dia;
	int mes;
	int anio;
};

typedef struct fecha Fecha;

struct ANIMAL {
	char codigo[4];
	int identificador;
	char sexo[3];
	Fecha F;
};

typedef struct ANIMAL animal;

struct nodo{
   servicios dato;
   struct nodo*siguiente; 	
   };

  typedef struct nodo Nodo; 


FILE* FS;
FILE* FA;
int CANTANIMAL=0;
Nodo*cabeza=NULL;
Nodo* buscar(Nodo* cabeza,int bandera);
void mostrarmenu(char* nombreArchivo,char* Archivo);
void pausar();
void limpiar_entrada();
void Servicios(char* nombreArchivo);
void Animales(char* Archivo);
void lista_servicios(char* nombreArchivo);
void lista_animales(char* Archivo);
void insertarOrden(Nodo** cabeza,servicios entrada);
void recorrer(Nodo* cabeza);
void informe(char* Archivo);
void ordenar(char* nombreArchivo);

void ordenar(char* nombreArchivo){
     servicios AUX;
     FS=fopen(nombreArchivo,"rb");
      if(FS==NULL){
      	puts("\nerror");
      }
      else{
      	 fread(&AUX,sizeof(servicios),1,FS);
      	 while(!feof(FS)){
      	 	insertarOrden(&cabeza,AUX);
      	 	fread(&AUX,sizeof(servicios),1,FS);
      	 }
      	 fclose(FS);
      }
      pausar();
}


void limpiar_entrada(){
	char c;
	while((c=getchar())!='\n' && c!=EOF){

	}
}

void  Servicios(char* nombreArchivo){
	  servicios S;
	  FS=fopen(nombreArchivo,"ab");
	  if(FS==NULL){
     FS=fopen(nombreArchivo,"wb");
     if(FS==NULL) 
	      puts("error\n");
	  }
	  else{
         puts("\nCodigo:");
         scanf("%[^\n]",S.codigo);
         limpiar_entrada();
         puts("\n raza:");
         scanf("%[^\n]",S.raza);
         limpiar_entrada();
         puts("\n responsable:");
         scanf("%[^\n]",S.responsable);
         limpiar_entrada();
         puts("\n cantidad de animales");
         scanf("%d",&S.cantidad);
         limpiar_entrada();
         fwrite(&S,sizeof(servicios),1,FS);
	     fclose(FS);
	  }
	  pausar();
}

  void Informe(char* Archivo){
      animal A;
      int cantF=0,cantM=0;// contadores de machos y hembras
      Nodo* ptr=cabeza;// el nodo que recibe el servicio a analizar se asigna directamente a la cabeza de la lista enlazada
      int animalservicio=0,CONT_TOTAL=0;// el contador de animales de in servicio y el auxiliar del total de animales
      FA=fopen(Archivo,"rb");
      if(FA==NULL)
         puts("\n error");
       else{
        puts("----------------FACULTAD DE VETERINARIA--------------\n");
        puts("---------------CONTROL DE VACUNACION-----------------\n");
       while(ptr!=NULL){
         cantM=0;
         cantF=0;
         printf("\nservicio:%s\t raza:%s\n responsable:%s",ptr->dato.codigo,ptr->dato.raza,ptr->dato.responsable);
         fread(&A,sizeof(animal),1,FA);
         while(!feof(FA)){
            if(strcmp(ptr->dato.codigo,A.codigo)==0){  
              printf("\n identificador: %d\t\t Fecha: %d/%d/%d",A.identificador,A.F.dia,A.F.mes,A.F.anio);
              if(strcmp(A.sexo,"M")==0){
                cantM++;
              }
              else{
                cantF++;
              }
            }
            fread(&A,sizeof(animal),1,FA); 
          }   
          printf("\n machos:%d \t\t hembras :%d",cantM,cantF);
          CONT_TOTAL=CONT_TOTAL+cantF+cantM;
          rewind(FA);
          ptr=ptr->siguiente;
        }
        printf("\n total de animales :%d",CONT_TOTAL);
        fclose(FA);
      }
     pausar();
  }
 
 Nodo* buscar(Nodo* cabeza,int bandera){
      int contaux=-1;
      Nodo* nuevo;
       contaux++;
       nuevo=cabeza;
       if(contaux==bandera){
        return nuevo;
       }
    else{
         while(contaux!=bandera){
            nuevo=nuevo->siguiente;
            contaux++;
          }

         return nuevo;
  }
  }
      

 

  void recorrer(Nodo *cabeza){
      Nodo* AUX;
      AUX=cabeza;

     while(AUX!=NULL){
     	 printf("\ncodigo:%s",AUX->dato.codigo);
       AUX=AUX->siguiente;
     }
     
   pausar();
  } 

void Animales(char* Archivo){

      animal A;
      FA=fopen(Archivo,"a+b");
      if(FA==NULL){
      	FA=fopen(Archivo,"wb");
         if(FA==NULL)
         	puts("error\n");
      }
     else{
         puts("\n codigo:");
         scanf("%[^\n]",A.codigo);
         limpiar_entrada();
         puts("\n identificador:");
         scanf("%d",&A.identificador);
         limpiar_entrada();
         puts("\n sexo:");
         scanf("%[^\n]",A.sexo);
         limpiar_entrada();
         puts("\n fecha de ultima vacunacion:");
         do{
         puts("\n dia:");
         scanf("%d",&A.F.dia);
         limpiar_entrada();
         }while(A.F.dia<1 || A.F.dia>31);
         do{
         	puts("\n mes:");
         	scanf("%d",&A.F.mes);
         	limpiar_entrada();
         }while(A.F.mes<0 || A.F.mes>12);
         	puts("\n anho");
         	scanf("%d",&A.F.anio);
         	limpiar_entrada();
         fwrite(&A,sizeof(animal),1,FA);
         fclose(FA); 	
      }
      pausar();
}

void lista_servicios(char* nombreArchivo){

        servicios AUX;
        FILE*FS;
        FS=fopen(nombreArchivo,"rb");
        if(FS==NULL){
        	puts("\n error");
        }
        else{
         fread(&AUX,sizeof(servicios),1,FS);
         while(!feof(FS)){
            printf("\ncodigo:%s \n raza: %s \n responsable:%s \n cantidad de animales:%d",AUX.codigo,AUX.raza,AUX.responsable,AUX.cantidad);
            fread(&AUX,sizeof(servicios),1,FS);
         }
         fclose(FS);

        }
      pausar();

}
		
void lista_animales(char* Archivo){
        
        animal AUX;
        FA=fopen(Archivo,"rb");
        if(FA==NULL){
        	puts("\n error");
        }
        else{
         fread(&AUX,sizeof(animal),1,FA);
         while(!feof(FA)){
            CANTANIMAL++;
            printf("\ncodigo:%s \n identificador %d \n sexo:%s \n fecha de vacunacion:%d/%d/%d",AUX.codigo,AUX.identificador,AUX.sexo,AUX.F.dia,AUX.F.mes,AUX.F.anio);
            fread(&AUX,sizeof(animal),1,FA);
         }
         fclose(FA);

        }
    
      pausar();

}

 Nodo* crearnodo(servicios x){
      Nodo* a;
      a=(Nodo*)malloc(sizeof(Nodo));
      strcpy(a->dato.codigo,x.codigo);
      strcpy(a->dato.raza,x.raza);
      strcpy(a->dato.responsable,x.responsable);
      a->dato.cantidad=x.cantidad;
      a->siguiente=NULL;
      return a;
   }
		
void insertarOrden(Nodo** cabeza,servicios entrada){
    Nodo* nuevo;
    nuevo=crearnodo(entrada);
    int r;
    if((*cabeza)){
    r=strcmp(entrada.codigo,(*cabeza)->dato.codigo);
    }

    if(*cabeza==NULL){
         
        *cabeza=nuevo;
    }
    else if(r>0){
        nuevo->siguiente=*cabeza;
        *cabeza=nuevo;
    }
    else{

        Nodo*anterior,*p;
        anterior=p=*cabeza;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
        while((p->siguiente!=NULL) && (strcmp(nuevo->dato.codigo,p->dato.codigo))>0){

            anterior=p;
            p=p->siguiente;
        }

       if(strcmp(nuevo->dato.codigo,p->dato.codigo)>0)
          anterior=p;
          nuevo->siguiente=anterior->siguiente;
          anterior->siguiente=nuevo;
    }

      
}
