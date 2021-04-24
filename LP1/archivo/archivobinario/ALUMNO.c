#include<stdio.h>
#include"funciones.h"
#include<string.h>
#include<stdlib.h>

int main(){

  menu();
   
    return 0;

}

void limpiar_entrada(){

   char c;
   while((c=getchar())!='\n' && c!=EOF){

   } 
}

void separar(char cadena[]){

    dato AUX={' ',' ',0,0};
    char numero[10];
    char prom[10];
    int cont=0;
    int i=0,j=0;
    
    for(i=0;i<strlen(cadena);i++){
        
         if(cadena[i]!=','){
             
              if(cont==0){
                AUX.nombre[j]=cadena[i];
                j++;
              }
            if(cont==1){
                AUX.apellido[j]=cadena[i];
               j++;
            } 
            if(cont==2){
                numero[j]=cadena[i];
                j++;
            }
            if(cont==3){
                 prom[j]=cadena[i];
                j++; 
            }
          }
           else{
             
             if(cont==2){
                AUX.ci=atoi(numero);
             }
             cont++;
             if(cont>3){
                cont=0;
             }
             j=0;
             
            
           }
    }
     AUX.promedio=atof(prom);
     insertarOrden(&cabeza,AUX);
}
   
dato buscar_alumno(Nodo* cabeza, int cedula){
   puts("\n hola");
   Nodo* actual,*anterior;
   actual=cabeza;// nodo auxiliar;
   int encontrado=0;
  while(actual!=NULL && !encontrado){
      
      if(actual->D.ci==cedula){
         encontrado=1;
      } 
      else
        actual=actual->siguiente;
    }
     if(encontrado==1){

       return actual->D;  
     }
     else{

        puts("\nno se encuentra el alumno en la lista\n");
     }
}

void cargar_puntajes(){
    int cedula=0,i=0;
    char PUNTAJE[3]={' ',' ',' '};
    char dia[20];
    int repetir=1;
    dato ALUMNO={' ',' ',0,0};
    puts("INGRESANDO PUNTAJES");
    fb=fopen("laboratorios.DAT","w");
    do{
       puts("INGRESE EL NUMERO DE CEDULA\n");
        scanf("%d",&cedula);
        limpiar_entrada(); 
        ALUMNO=buscar_alumno(cabeza,cedula); 
        if(ALUMNO.ci!=0){
           fwrite(ALUMNO.nombre,1,strlen(ALUMNO.nombre),fb);
           fprintf(fb,"\n");
           fwrite(ALUMNO.apellido,1,strlen(ALUMNO.apellido),fb);
           fprintf(fb,"\n");
           fprintf(fb,"LABORATORIO:\n");
            for(i=0;i<CANTIDAD;i++){
                puts("ingrese la fecha del laboratorio:\n");
                scanf("%[^\n]",dia);
                limpiar_entrada();
                fwrite(dia,1,strlen(dia),fb);
                fprintf(fb,"\n");
                puts("\n Ingrese el puntaje:\n");
                scanf("%[^\n]",PUNTAJE);
                limpiar_entrada();
                fwrite(PUNTAJE,1,strlen(PUNTAJE),fb);
            }
        }
        else{
            exit(1); 
        }
        puts("ingresar otros puntajes\n");
        scanf("%d",&repetir);
        limpiar_entrada();
    }while(repetir==1);
}  
void recorrer(Nodo *cabeza){

    int k;
    printf("\n\t\t Informe\n");
  
    for(k=0;cabeza;cabeza=cabeza->siguiente){
        printf("\n Nombre: %s\n Apellido:%s\n ",cabeza->D.nombre,cabeza->D.apellido);
        printf("cedula:%d  promedio :%.2f\n",cabeza->D.ci,cabeza->D.promedio);
   }   
}

void insertar_en_lista(){
    int i=0;
    int cont=0;
    char cadena[50];
    char c;
    
    fa=fopen("archivo.csv","r");
    if(fa==NULL){
        puts("error al leer archivo\n");
        exit(1);
    }
    else{
        while(!feof(fa)){
          fscanf(fa,"%s",cadena);
          separar(cadena);
        }
       
    }

}


void insertarOrden(Nodo** cabeza,dato entrada){
  
    Nodo* nuevo;
    nuevo=crearnodo(entrada);
    int aux;
    if((*cabeza)!=NULL)
    aux=strcmp((*cabeza)->D.apellido,entrada.apellido);

    if(*cabeza==NULL){

        *cabeza=nuevo;
    }
    else if(aux>0){

        nuevo->siguiente=*cabeza;
        *cabeza=nuevo;
    }
    else{

        Nodo*anterior,*p;
        anterior=p=*cabeza;

        while((p->siguiente!=NULL) && aux<0){

            anterior=p;
            p=p->siguiente;
        }

       if(aux<0)
          anterior=p;
          nuevo->siguiente=anterior->siguiente;
          anterior->siguiente=nuevo;
    }

 }

 Nodo* crearnodo(dato x){

      Nodo* a;
      a=(Nodo*)malloc(sizeof(Nodo));
      a->D=x;
      a->siguiente=NULL;
      return a;
   }


void crear_archivo(){
    
       // carga lo de la seccion A
        char n[20],a[20];
        char cedula[20];
        char  promedio[4];
        int r;// para dar la respuesta de si se desea ingresar mas elementos;
       fa=fopen("archivo.csv","w");
       if(fa==NULL){
     
           puts("error creando archivo");
           exit (1);
       }
       
        else{

            do{
                r=1;
                puts("ingrese el nombre\n");
                scanf("%[^\n]",n);
                limpiar_entrada();
                puts("ingrese el apellido\n");
                scanf("%[^\n]",a);
                limpiar_entrada();
                puts("ingrese el numero de cedula\n");
                scanf("%[^\n]",cedula);
                limpiar_entrada();
                puts("ingrese el promedio\n");
                scanf("%[^\n]",promedio);
                limpiar_entrada();

                fwrite(n,1,strlen(n),fa);
                fprintf(fa,",");
                fwrite(a,1,strlen(a),fa);
                fprintf(fa,",");
                fwrite(cedula,1,strlen(cedula),fa);
                fprintf(fa,",");
                fwrite(promedio,1,strlen(promedio),fa);
                fprintf(fa,"\n");
                puts("\n Ingresar otro alumno?\n");
                scanf("%d",&r);// se ingresa 1 para seguir ingresando
                limpiar_entrada();
            }while(r==1);    

                
        }

        fclose(fa);
    }    

void ingresar_alumnos(){

          // carga lo de la seccion A
        char n[20],a[20];
        char cedula[20];
        char  promedio[4];
        int r;// para dar la respuesta de si se desea ingresar mas elementos;
       fa=fopen("archivo.csv","a");
       if(fa==NULL){
     
           puts("error creando archivo");
           exit (1);
       }
       
        else{

            do{
                r=1;
                puts("ingrese el nombre\n");
                scanf("%[^\n]",n);
                limpiar_entrada();
                puts("ingrese el apellido\n");
                scanf("%[^\n]",a);
                limpiar_entrada();
                puts("ingrese el numero de cedula\n");
                scanf("%[^\n]",cedula);
                limpiar_entrada();
                puts("ingrese el promedio\n");
                scanf("%[^\n]",promedio);
                limpiar_entrada();

                fwrite(n,1,strlen(n),fa);
                fprintf(fa,",");
                fwrite(a,1,strlen(a),fa);
                fprintf(fa,",");
                fwrite(cedula,1,strlen(cedula),fa);
                fprintf(fa,",");
                fwrite(promedio,1,strlen(promedio),fa);
                fprintf(fa,"\n");
                puts("\n Ingresar otro alumno?\n");
                scanf("%d",&r);// se ingresa 1 para seguir ingresando
                limpiar_entrada();
            }while(r==1);    

                
        }

        fclose(fa);
    }    



void menu(){
	
    puts("\n\t BIENVENIDO AL MENU\n");
    puts("1-Crear archivo de alumnos\n");
    puts("2-Cargar datos en la lista enlazada\n");
    puts("3-Informe\n");
    puts("4-Guardar datos de la lista al archivo \n");
    puts("5-Agregar nuevos alumnos\n");
    puts("6-Agregar puntaje\n");
    puts("7-Salir\n");

    int opcion;
  do{
    	puts("\n Ingrese una opcion\n");
    	scanf("%d",&opcion);
    	limpiar_entrada();
    	switch(opcion){

     		case 1: 
           	crear_archivo();
           	 break;
     		case 2: 
     		   insertar_en_lista();
     		  break; 
     		 case 3: recorrer(cabeza);
                 break;
     		 //case 4:
             //	 puts("\n saliendo del programa\n");
        	 // break;
            case 5:
              ingresar_alumnos();
             break;
             case 6:
              cargar_puntajes();
             break;
             case 7:
             puts("\nSaliendo del programa\n");
             break;
        }      

    }while(opcion!=7);

}
