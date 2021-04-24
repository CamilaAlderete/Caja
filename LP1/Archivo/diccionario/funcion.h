

struct registro{
	
	char p_incorrecta[31],p_correcta[31];
};

typedef struct registro palabra;

struct nodo{
	palabra D;
	struct nodo*izq;
	struct nodo *der;
};

typedef struct nodo Nodo;
  FILE *FD; // para archivo diccionario
  FILE *OUT; //para archivo de salida
  FILE *T; //para el texto a corregir
  Nodo* raiz=NULL;
  int contp=0,contc=0;
 void Archivo_corregido();
 void pasar_a_arbol();
 void Cargar_en_diccionario();
 void insertar_en_arbol(Nodo** raiz,palabra AUX);
 Nodo* crearnodo(palabra dato);
 void  visualizar(Nodo*raiz);
 void limpiar_entrada();
int buscar_en_arbol(Nodo*raiz,palabra NUEVO);
void corregir_texto();
char *buscar(Nodo *raiz,char* cadena);
