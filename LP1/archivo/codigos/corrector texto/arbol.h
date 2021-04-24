typedef struct registro{
	char p_incorrecta[31],p_correcta[31];
}registro;

typedef struct nodo{
  registro dato;
  struct nodo* izq,*dcho;
}nodo;

//nodo* buscar(nodo *raiz,char* dato,int n_caracteres_copiados);
nodo* crearnodo(registro dato);
void insertar(nodo** raiz, registro dato);
void InOrden(nodo*raiz);
nodo* buscar(nodo *raiz,char* dato);
