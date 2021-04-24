
#define CANTIDAD 5 // se asume que hubo 5 laboratorios


 typedef struct Puntaje PUNTAJE;


 struct datos{

   char nombre[20],apellido[20];
   int ci;
   float promedio;
 };

 typedef struct datos dato;

 struct nodo{

     dato D;
     struct nodo*siguiente;
 };

   typedef struct nodo Nodo;

  FILE* fa,*fb;
  Nodo *cabeza=NULL;
  
   
   Nodo* crearnodo(dato x);
   void crear_archivo();
   void insertar_en_lista();
   void ingresar_alummnos();
   void menu();
   void insertarOrden(Nodo**cabeza,dato entrada);
   void limpiar_entrada();
   void cargar_puntajes();
   dato buscar_alumno(Nodo* cabeza, int cedula);
    void recorrer(Nodo*cabeza);
