#include<stdio.h>
struct vehiculo{
	
	  char chapa[6];
	  char marca[10];
	  int modelo;
};

typedef struct vehiculo AUTO;

 struct transfer{
         char chapa[6];
         int cedula;
         char nombre[20];
         char apellido[20];
         int monto;
 };

 typedef struct transferencia TRANSFERENCIA;

  FILE* FAUTO;
  FILE* FT;
