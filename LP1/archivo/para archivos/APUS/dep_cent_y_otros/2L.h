// Angel Lopez CI: 4.268.726 
// DEV-C WINDOWS 10
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void menu(void)
{
	printf("MENU\n");
	printf("1-Cargar Departamento Central.\n");
	printf("2-Cargar Resto de los departamentos\n");
	printf("3-Seleccion\n");
	printf("4-Informe.\n");
	printf("5-Salir.\n");
}

void crearLista(gana**cabeza,propietario autom)
{
	(*cabeza)=(gana*)malloc(sizeof(gana));
	strcpy((*cabeza)->nombre,autom.nombre);
	strcpy((*cabeza)->apellido,autom.apellido);
	(*cabeza)->chasis=autom.chasis;
	(*cabeza)->anopag=autom.anopag;
	(*cabeza)->siguiente=NULL;
}

void reseleccion(gana**cabeza)
{
     gana*actual,*anterior,*temp;
     for(actual=(*cabeza);actual->siguiente!=NULL;actual=actual->siguiente)
     anterior=actual;

     temp=anterior->siguiente;//se elimina el ultimo propietario de la lista que no entra debido a que son plazas limitadas
     anterior->siguiente=NULL;
     free(temp);
}

void insertar(gana**cabeza,propietario autom)
        {

         gana*nuevo=NULL,**actual;
         if((*cabeza)==NULL){

          crearLista(cabeza,autom);

        }else{

         nuevo=(gana*)malloc(sizeof(gana));
         strcpy(nuevo->nombre,autom.nombre);
         strcpy(nuevo->apellido,autom.apellido);
         nuevo->chasis=autom.chasis;

         nuevo->anopag=autom.anopag;

         actual=cabeza;

         while((*actual)!=NULL&& ((*actual)->anopag>nuevo->anopag))
         actual=&((*actual)->siguiente);

         nuevo->siguiente=(*actual);
         (*actual)=nuevo;

         if(conteo(*cabeza)==9)
         reseleccion(cabeza);//si se superan si las plazas se llenan y hay mas automoviles que cumplieron el requisito se seleccionan los primeros
         }
        }

int conteo(gana*cabeza)//CONTEO DE LOS GANADORES!!! 
{
     gana*actual;
     int cont=0;
     for(actual=cabeza;actual!=NULL;actual=actual->siguiente)
     cont++;
     return cont;
}

void seleccion(FILE**p,FILE**q,gana**cabeza)
{
         propietario auxiliar;
         (*cabeza)=NULL;
         (*p)=fopen("datosG.dat","r");
         (*q)=fopen("datosH.dat","r");
         if((*p)!=NULL)
         {
            printf("\nAUTOMOVILES EN DEL DEP CENTRAL\n");
            while(fread(&auxiliar,sizeof(propietario),1,(*p))==1)
            {
            	printf("\nNombre  Apellido  Año pagado  Chasis\n");
                printf("%s\t%s\t\t%d\t%d\n",auxiliar.apellido,auxiliar.nombre,auxiliar.anopag,auxiliar.chasis);
                
               if(auxiliar.chasis%10==1 ||auxiliar.chasis%10==9 && auxiliar.anopag==2018)//se verifica la unidad y se ve si esta pagado en el año
               insertar(cabeza,auxiliar);//se inserta el elemento leido del archivo de el departamento central
            }
         }else printf("El archivo del departamento central esta vacio.\n");
         fclose(*p);

         if((*q)!=NULL)
         {
            printf("\nAAUTOMOVILES EN OTROS DEPARTAMENTOS\n");
            while(fread(&auxiliar,sizeof(propietario),1,(*q))==1)
            {
            	printf("\nNombre  Apellido  Año pagado  Chasis\n");
              printf("%s\t/%s\t/%d\t/%d\n",auxiliar.apellido,auxiliar.nombre,auxiliar.anopag,auxiliar.chasis);
               if(auxiliar.chasis%10==1 ||auxiliar.chasis%10==9 && auxiliar.anopag==2018)
               insertar(cabeza,auxiliar);//se inserta el elemento leido del archivo de los otros departamentos
            }
         }else printf("El archivo de otros deparamentos esta vacio\n");
         fclose(*q);
        // printf("xxx\n%d\nxxx",auxiliar.anopag);
}

void recorrerLista(gana*cabeza)
{
gana*actual;
if(cabeza!=NULL){
	printf("\t\t\t\tAUTOMOVILES PREMIADOS\n");//imprime los automoviles que posaron las pruebas y se llevan el premio
	for(actual=cabeza;actual!=NULL;actual=actual->siguiente){
	
	printf("Nombre  Apellido  Chasis  Año Pagado\n");
	printf("%s\t/%s\t/%d/  %d\n",actual->apellido,actual->nombre,actual->chasis,actual->anopag);
	
	}
	printf("//\n\nEL CODIGO FUNCIONA PERO ME IMPRIME BASURA EN EL AÑO DE PAGO AL RECORRER LA LISTA!");
	
	
}else printf("No se han seleccionado los automoviles para generar el informe.\n");
}


/*DATOS IMPORTANTES PARA EL PROFESOR!
EN LA FUNCION SELECCION ME IMPRIME BASURA AL QUERER MOSTRAR AL USUARIO EL año de pago !!!
*/

