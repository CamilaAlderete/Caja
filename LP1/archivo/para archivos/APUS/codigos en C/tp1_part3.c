#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <windows.h>

//struct palabra
struct palabra{
    char *p_palabra,*p_palabracompleta,*p_nuevo;
};
//Prototipado de Funciones.
void hacerValidacion(int *,int *);
void hacerCarga(int f, int c, char matriz[][c]);
void mostrarMatriz(int f,int c, char matriz[][c]);
void *cargarPalabra(void *arg);
//Funcion Busqueda
void hacerBusqueda(int f,int c, char matriz[][c], struct palabra p2);
//Hilos Para la Busqueda
void *hilo1(void *arg);
void *hilo2(void *arg);
//Funciones Cruz
void procceDerech(int x, int y, int f, int c, int i, char matriz[][c], struct palabra p2);
void procceIzquie(int x, int y, int f, int c, int i, char matriz[][c], struct palabra p2);
void procceArriba(int x, int y, int f, int c, int i, char matriz[][c], struct palabra p2);
void procceAbajoo(int x, int y, int f, int c, int i, char matriz[][c], struct palabra p2);
//Funciones X
void procceDiader(int x, int y, int f, int c, int i, char matriz[][c], struct palabra p2);
void procceDiaizq(int x, int y, int f, int c, int i, char matriz[][c], struct palabra p2);
void procceDiaizab(int x, int y, int f, int c, int i, char matriz[][c], struct palabra p2);
void procceDiadeab(int x, int y, int f, int c, int i, char matriz[][c], struct palabra p2);
//Inicio de la Funcion Principal
int main(void){
    srand(time(NULL));
    int f,c,min=0,sg=0,T=1;
    //Variable de Struct
    struct palabra p2;
    //Creacion de Hilos
    pthread_t h1 ;
    //Solicitar Parametros.
    printf("Ingrese la Fila de la Matriz, comprendido entre f<=12: ");
	scanf("%d",&f);
	printf("Ingrese la Columna de la Matriz, comprendido entre c<=15: ");
	scanf("%d",&c);
	//Solicitar Validacion
	hacerValidacion(&f,&c);
	char *matriz[f][c];
    //Proceso Carga
    hacerCarga(f,c,matriz);
    p2.p_palabra = malloc(20*sizeof(char));
    p2.p_nuevo = malloc(20*sizeof(char));
    //printf("\nIngrese la primera palabra :\n");
    printf("\nIniciar:(SI/NO)\n");
    scanf("%s",p2.p_palabra);
	system("pause");
    //Proceso de Tiempo
    while(min<T){
		sg+=5;
		if(sg == 60){
			sg=0;
			min++;
		}
		system("cls");
		printf("Tiempo:[%.2d] Minutos Transcurridos.. \n\n",min);
        mostrarMatriz(f,c,matriz);
		pthread_create(&h1, NULL,cargarPalabra,(void *)&p2);
		Sleep(5000);

	}
	system("cls");
    printf("Palabras:%s",p2.p_palabracompleta);
	pthread_join ( h1 , NULL ) ;
    return 0;
}
void hacerValidacion(int *ptr_f, int *ptr_c){

	int b = 0, c = 0;//Bandera
		while(b==0 && c==0){
			if((*ptr_f <=12 && *ptr_c <=15)){
				printf("Los Valores son Correctos..\n");
				b=1;c=1;
			}else{
				//Proceso Fila
				printf("\nIngrese Valores Correctos Respecto a la Especificacion !!\n");
				if((*ptr_f >= 13)){
					printf("Ingrese la Fila de la Matriz, comprendido entre f<=12: ");
					scanf("%d",&ptr_f);
				}
				//Proceso Columna
				if((*ptr_c >= 16)){
					printf("Ingrese la Columna de la Matriz, comprendido entre f<=15: ");
					scanf("%d",&*ptr_c);
				}
			}
		}
	return;
}
void hacerCarga(int f,int c,char matriz[][c]){
	char conjunto[] = {'A','A','A','A','B','B','C','C','C','D','D','E','E','E','E','F','F','G',
	'G','H','I','I','I','J','K','L','L','M','M','N','N','Ñ','O','O','O','O','O','P','P','P','Q','R',
	'R','S','S','S','T','T','U','U','V','W','X','Y','Z'};
	int i,j;
	for(i=0;i<f;i++){
		for(j=0;j<c;j++){
			matriz[i][j]= conjunto[rand() % 54 ];
		}
	}
	return;
}
void *cargarPalabra(void *arg){
    struct palabra *p;
    p = ( struct parametros *) arg ;
    p->p_palabracompleta = realloc(p->p_palabra,20*sizeof(char));
    printf("\nIngrese las sgte palabras :\n");
    scanf("%s",p->p_nuevo);
    strcat(p->p_palabracompleta," ");
    strcat(p->p_palabracompleta,p->p_nuevo);
}
void mostrarMatriz(int f,int c,char matriz[][c]){
    int i,j;
	for(i=0;i<f;i++){
		for(j=0;j<c;j++){
			printf(" %c",matriz[i][j]);
		}
		printf("\n");
	}
}
void hacerBusqueda(int f,int c, char matriz[][c], struct palabra p2){
	int x,y,i,b;
	i=3;
	//Creacion de Hilos
    pthread_t h1 ;
	pthread_t h2 ;
	//Busqueda e Comparacion
	while(){
		for(x=0;x<f;x++){
			for(y=0;y<c;y++){
				if(strcmp(matriz[x][y],p2.p_palabracompleta+i) == 0){
					//Funciones Cruz
					procceDerech(x,y,f,c,i,matriz,p2);
					procceIzquie(x,y,f,c,i,matriz,p2);
					procceArriba(x,y,f,c,i,matriz,p2);
					procceAbajoo(x,y,f,c,i,matriz,p2);
					//Funciones X
					procceDiader(x,y,f,c,i,matriz,p2);
					procceDiaizq(x,y,f,c,i,matriz,p2);
					procceDiaizab(x,y,f,c,i,matriz,p2);
					procceDiadeab(x,y,f,c,i,matriz,p2);
				}
			}
		}	
		while(b==0){
			i++;
			if(strcmp(p2.p_palabracomleta+i," ") == 0){
				b=1;
			}
		}
		i++;
	}
}	
void procceDerech(int x, int y, int f, int c, int i, char matriz[][c], struct palabra p2){
	int b,k,z=0;
	k = i;
	while(b==0){
		if(strcmp(p2.p_palabracompleta+k," ") != 0){
			if(strcmp(p2.p_palabracompleta+k,matriz[x][y+z]) != 0){
				z++;
				k++;
				//Proceso Guardar Palabras.
			}else{
				b=1;
			}
		}
	}
	return;
}


