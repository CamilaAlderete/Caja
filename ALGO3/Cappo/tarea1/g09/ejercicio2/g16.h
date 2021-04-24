#include <stdio.h>
#include <stdlib.h>
#include <math.h>
/*  G 16
    Eric Ruiz Diaz
    Luis Pereira
*/
typedef struct coordenada { //tipo de dato para guardar las coordenadas
    float x;
    float y;
}coordenada;




typedef struct nodo{    //tipo de datp para guardar los datos en una lista enlazada
    coordenada dato;
    struct nodo* sig;
}lista;




lista * crearElemento(){    //funcion para crear un nodo en la lista enlazaa

    lista * aux=(lista *)malloc(sizeof(lista));
    aux->sig=NULL;
    return aux;
}





void anadirElemento(lista * nodo,coordenada dato){  //funcion para añadir el nodo a la lista enlazada

    if(nodo->sig==NULL){

        lista *vaina=crearElemento();

        vaina->dato=dato;

        nodo->sig=vaina;

    }else{

        anadirElemento(nodo->sig,dato);

    }
}



void mostrarLista(lista S){ //funcion para mostrar la lista en pantalla

    if(S.sig!=NULL){
        printf("%.1f        %.1f\n",S.dato.x,S.dato.y);
        mostrarLista(*S.sig);
    }else{
        printf("%.1f        %.1f\n",S.dato.x,S.dato.y);
    }
}


coordenada entrada(){   //funcion para leer los datos
    coordenada temporal;
    puts("Ingrese en X = 99 y en Y = 99 para terminar la entrada");
    puts("Ingrese la coordenada en x");
    scanf("%f",&temporal.x);

    puts("Ingrese la coordenada en y");
    scanf("%f",&temporal.y);
    return temporal;
}

coordenada vectorizarLista(lista S,int c,coordenada listPunt[]){    //funcion para transformar la lista enlazada en un vector
    if(S.sig!=NULL){
        listPunt[c-1]=S.dato;
        vectorizarLista(*S.sig,c-1,listPunt);
    }else{
        listPunt[c-1]=S.dato;
    }
}

void ordenarLista(coordenada S[],int c){    //funcion para ordenar el vector ascendentemente
    int i=0,j=0;

    coordenada aux;

    for(i=0; i< c-1 ; i++){

        for(j=i+1 ; j < c  ;j++){

            if(S[i].x > S[j].x){

                aux.x=S[i].x;
                aux.y=S[i].y;

                S[i].x=S[j].x;
                S[i].y=S[j].y;

                S[j].x=aux.x;
                S[j].y=aux.y;
            }else{
                if(S[i].x == S[j].x && S[i].y > S[j].y){
	                aux.x=S[i].x;
	                aux.y=S[i].y;

	                S[i].x=S[j].x;
	                S[i].y=S[j].y;

	                S[j].x=aux.x;
	                S[j].y=aux.y;
                }
            }
        }
    }
}
float distancia(coordenada punto1,coordenada punto2){ //funcion para hallar la distancia entre dos puntos
	float valor;
	float x=pow(punto1.x-punto2.x,2);
	float y=pow(punto1.y-punto2.y,2);
	valor=sqrt(x+y);
	return valor;
}

float menordistancia(coordenada S[],int dim,int lis){   //funcion para hallar la menor distancia entre dos puntos
	int i;
	float x1,x2,y1,y2;
	float menor=100000;
	for(i=0;i<dim-1;i++){
		if(distancia(S[i],S[i+1])<menor){
			menor=distancia(S[i],S[i+1]);
			x1=S[i].x;
			x2=S[i+1].x;
			y1=S[i].y;
			y2=S[i+1].y;
		}
	}
    if(lis==3){
        printf("\nLos puntos son (%.1f,%.1f) y (%.1f,%.1f) \n",x1,y1,x2,y2);
	}
	return menor;
}
float min(float d1,float d2, float d3){ //funcion para hallar la menor distancia entre los puntos
	if(d1>d2 && d1>d3){
		return d1;
	}else{
		if(d2>d1 && d2>d3){
			return d2;
		}else{
			return d3;
		}
	}
}
void dividirLista(coordenada S[],int c){    //funcion para dividir la listas en dos e imprimir los puntos mas cercanos
    int v=0,i=0,j=0;
    if(c%2!=0){
        v=1;
    }
    int dim=(c/2)+v;

    coordenada S1[dim];
    coordenada S2[dim-v];

    for(i = 0; i<dim; i++){
        S1[j].x = S[i].x;
        S1[j].y = S[i].y;
        j++;
    }
    j=0;
    for(i=dim; i<c;i++){
        S2[j].x = S[i].x;
        S2[j].y = S[i].y;
        j++;
    }
    puts("\nDos listas");
    for(i = 0; i<dim; i++){
        printf("%.1f,%.1f  y  %.1f,%.1f\n",S1[i].x,S1[i].y,S2[i].x,S2[i].y);
	}
    float d1=menordistancia(S1,dim,1);
	float d2=menordistancia(S2,dim,2);
	float d3=menordistancia(S,c,3);

	//printf("La menor distancia es %f",min(d1,d2,d3));
}











