#include<stdio.h>

  struct dato{

   char nombre[20];
   char apellido[20];    
   int ci,porcentaje;
   float promedio;  
  };

  typedef struct dato dato;

   struct nodo{
      dato  D;
      struct nodo*siguiente;
   };

   typedef struct nodo Nodo;
// variables globales
   char direccion1[]="claseA.txt";
   char direccion2[]="claseB.txt";
   Nodo* cabeza=NULL; 
   FILE *fp;
   FILE *fa,*fb;

   void menu();
   void cargar_datos();
   void seleccionar();
   void limpiar_entrada();
   void insertarOrden(Nodo** cabeza,dato entrada);
   Nodo* crearnodo(dato x);
   void recorrer(Nodo *cabeza);