#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include"alumno.h"

int main(){


   menu();

   return 0;
}


void menu(){
	
    puts("\n\t BIENVENIDO AL MENU\n");
    puts("1-Carga de datos\n");
    puts("2-Seleccion de alumnos\n");
    puts("3-Informe\n");
    puts("4- Salir \n");
    int opcion;
  do{
    	puts("\n Ingrese una opcion\n");
    	scanf("%d",&opcion);
    	limpiar_entrada();
    	switch(opcion){

     		case 1: 
           	cargar_datos();
           	 break;
     		case 2: 
     		  seleccionar();
     		  break; 
     		  case 3: recorrer(cabeza);
                        break;
     		case 4:
        	  puts("\n saliendo del programa\n");
        	  break;
        }
    }while(opcion!=4);

}

void recorrer(Nodo *cabeza){

    int k;
    printf("\n\t\t Informe\n");
  
    for(k=0;cabeza;cabeza=cabeza->siguiente){
        printf("\n Nombre  %s \t",cabeza->D.nombre);
        printf("CI: %d \n Apellido: %s\t promedio de asistencia: %d \n\t\t promedio :%.2f\n",cabeza->D.ci,cabeza->D.apellido,cabeza->D.porcentaje,cabeza->D.promedio);
   }
    
}

void seleccionar(){
 puts("seleccionando elementos");
  fa=fopen(direccion1,"rt");
  dato AUX;
  while(!feof(fa)){
  	    fscanf(fa," %[^\n]",AUX.nombre);
  	    fscanf(fa," %[^\n]",AUX.apellido);
    	fscanf(fa,"%d",&AUX.ci);
  	    fscanf(fa,"%d",&AUX.porcentaje);
  	    fscanf(fa,"%f",&AUX.promedio);
  	   if((AUX.porcentaje>75 || AUX.promedio==75) && AUX.promedio>3.5){  
  	         insertarOrden(&cabeza,AUX);    
  	   } 
    }
  fclose(fa);
   
  fb=fopen(direccion2,"rt");
   while(!feof(fb)){
  	    fscanf(fb," %[^\n]",AUX.nombre);
  	    fscanf(fb," %[^\n]",AUX.apellido);
    	fscanf(fb,"%d",&AUX.ci);
  	    fscanf(fb,"%d",&AUX.porcentaje);
  	    fscanf(fb,"%f",&AUX.promedio);
  	   if((AUX.porcentaje>75 || AUX.promedio==75) && AUX.promedio>3.5){  
  	         insertarOrden(&cabeza,AUX);    
  	   } 
    }

    fclose(fb);
  
}

void insertarOrden(Nodo** cabeza,dato entrada){
   puts("insertando");
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


void limpiar_entrada(){

    char c;
    while((c=getchar())!='\n'&& c!=EOF){
    }
}


void cargar_datos(){
	
       // carga lo de la seccion A
        char n[20],a[20];
        char cedula[20];
        char asistencia[4], prom[4];
        int r;// para dar la respuesta de si se desea ingresar mas elementos;
       puts("\n DATOS PARA LA SECCION A\n");
       fa=fopen(direccion1,"wt");
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
                puts("ingrese el porcentaje de asistencia\n");
                scanf("%[^\n]",asistencia);
                limpiar_entrada();
                puts("ingrese el promedio\n");
                scanf("%[^\n]",prom);
                limpiar_entrada();

                fwrite(n,1,strlen(n),fa);
                fprintf(fa,"\n");
                fwrite(a,1,strlen(a),fa);
                fprintf(fa,"\n");
                fwrite(cedula,1,strlen(cedula),fa);
                fprintf(fa,"\n");
                fwrite(asistencia,1,strlen(asistencia),fa);
                fprintf(fa,"\n");
                fwrite(prom,1,strlen(prom),fa);
                fprintf(fa,"\n");
                puts("\n Ingresar otro alumno?\n");
                scanf("%d",&r);// se ingresa 1 para seguir ingresando
                limpiar_entrada();
            }while(r==1);    

                
        }

        fclose(fa);

       fb=fopen(direccion2,"wt"); 
       if(fb==NULL){
           puts("\nerror al abrir archivo");
            exit(1);
       }

       else{
       	    puts("\n DATOS PARA LA SECCION B\n");
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
                puts("ingrese el porcentaje de asistencia\n");
                scanf("%[^\n]",asistencia);
                limpiar_entrada();
                puts("ingrese el promedio\n");
                scanf("%[^\n]",prom);
                limpiar_entrada();

                fwrite(n,1,strlen(n),fb);
                fprintf(fa,"\n");
                fwrite(a,1,strlen(a),fb);
                fprintf(fb,"\n");
                fwrite(cedula,1,strlen(cedula),fb);
                fprintf(fb,"\n");
                fwrite(asistencia,1,strlen(asistencia),fb);
                fprintf(fb,"\n");
                fwrite(prom,1,strlen(prom),fb);
                fprintf(fb,"\n");
                puts("Ingresar otro alumno?\n");
                scanf("%d",&r);   
                limpiar_entrada();
            }while(r==1);
            fclose(fb);
        }

}

   



