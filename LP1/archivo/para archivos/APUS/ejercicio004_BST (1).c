#include<stdio.h>
#include<stdlib.h>

struct nodo
{
	int dato;
	struct nodo *izq;
	struct nodo *der;
};
typedef struct nodo Nodo;
typedef Nodo * PtrNodo;

void insertar(PtrNodo *ptrRaiz,int dato);
void imprimir(PtrNodo raiz, int profundidad);


int main()
{
	PtrNodo raiz = NULL;

	imprimir(raiz, 0);

	insertar(&raiz, 5);
	insertar(&raiz, 7);
	insertar(&raiz, 2);
	insertar(&raiz, 6);
	insertar(&raiz, 1);
	insertar(&raiz, 3);
	insertar(&raiz, 4);
	insertar(&raiz, 15);
	insertar(&raiz, 13);
	insertar(&raiz, 11);
	insertar(&raiz, 14);

	imprimir(raiz, 0);

}

void insertar(PtrNodo *ptrRaiz,int dato)
{
	if(*ptrRaiz == NULL)
	{
		PtrNodo nuevo = (PtrNodo) malloc(sizeof(Nodo));

		nuevo->dato = dato;
		nuevo->izq = NULL;
		nuevo->der = NULL;

		*ptrRaiz = nuevo;
	}
	else if((*ptrRaiz)->dato > dato)
	{
		insertar(&(*ptrRaiz)->izq, dato);
	}
	else if((*ptrRaiz)->dato < dato)
	{
		insertar(&(*ptrRaiz)->der, dato);
	}
}
void imprimir(PtrNodo raiz, int profundidad)
{
	int p;
	if(raiz == NULL)
		return;

	imprimir(raiz->der, profundidad + 1);


	printf("\n");
	for(p = 0; p < profundidad; p++)
		printf("\t");
	printf("%d", raiz->dato);


	imprimir(raiz->izq, profundidad + 1);

}
