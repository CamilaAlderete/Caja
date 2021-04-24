//void corregirPalabra(char linea[],nodo* raiz,int* contador);
void corregirArchivo(FILE* ptr_input,nodo* raiz,FILE* ptr_output);
void cargaDiccionario(nodo** raiz, char diccionario[]);
int agregaDiccionario(nodo** raiz,char diccionario[]);
void inicializar(char linea[],int dim);