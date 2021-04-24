#include<stdio.h>

struct prueba{
	int precio;
	int dia;
	char  mes[10];
};
typedef struct prueba PRUEBA;

void limpiar_entrada();
void limpiar_entrada(){
	char c;
	while((c=getchar())!='\n'&& c!=EOF ){

	}
}
void actualizar(FILE* FP);

 int main(){
    int opcion=0;
    PRUEBA P;
    FILE* FP;
    FP=fopen("prueba.txt","a+");
    if(FP==NULL){
    puts("no abre\n");
    }
  else{
    do{	
     puts("precio\t");
     scanf("%d",&P.precio);
     limpiar_entrada();
     puts("dia\t");
     scanf("%d",&P.dia);
     limpiar_entrada();
     puts("mes\n");
     scanf("%[^\n]",P.mes);
     limpiar_entrada();
     fprintf(FP,"%d %d %s\n",P.precio,P.dia,P.mes);
     puts("insertar mas datos?\n");
     scanf("%d",&opcion); 
    }while(opcion!=0);
    fclose(FP);
   }
   actualizar(FP);
}
void actualizar(FILE* FP){
	 fpos_t posicion=0;
	int linea=0;
	PRUEBA DATO;
	int nuevo;
	FP=fopen("prueba.txt","r+");
	if(FP==NULL)
		puts("error");
	else{
	puts("ingrese el nº de linea");
	do{
	rewind(FP);	
	fgetpos(FP,&posicion);
	printf("posicion depues del rewind:%d\n",posicion);
	scanf("%d",&linea);
    fseek(FP,(linea-1)*(sizeof(PRUEBA)),SEEK_SET);
    fgetpos(FP,&posicion);
    printf("despues del fseek: %d\n",posicion);
    fscanf(FP, "%d %d %s",&DATO.precio,&DATO.dia,DATO.mes);
    fgetpos(FP,&posicion);
    printf("posicion actual:%d\n",posicion);
   // printf("el precio anterior es:%d",DATO.precio);
   // puts("ingrese el nuevo precio\n");
    //scanf("%d",&nuevo);
    //DATO.precio=nuevo;
    printf("nuevo valor a actualizar es:%d %d %s\n",DATO.precio,DATO.dia,DATO.mes);
    //fseek(FP,(linea-1)*(sizeof(PRUEBA)),SEEK_SET);
    //fprintf(FP,"%d %d %s",DATO.precio,DATO.dia,DATO.mes);
    puts("otro\n");
    scanf("%d",&nuevo);
    
    }while(nuevo!=0);   
   } 
}



