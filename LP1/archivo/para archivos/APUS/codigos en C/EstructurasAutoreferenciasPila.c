#include<stdio.h>
#include<stdlib.h>

struct nodo
{
    int dato;
    struct nodo * sgt;
};
typedef struct nodo Nodo;
typedef Nodo * PtrNodo;

void apilar(PtrNodo *ptrPila, int dato);
void imprimirPila(PtrNodo pila);
int desapilar(PtrNodo *ptrPila);

int main()
{
    int dato;
    PtrNodo pila = NULL;

    imprimirPila(pila);

    apilar(&pila, 1);
    apilar(&pila, 3);
    apilar(&pila, 5);
    imprimirPila(pila);

    apilar(&pila, 7);
    imprimirPila(pila);

    dato = desapilar(&pila);
    printf("\nDato eliminado %d", dato);

    dato = desapilar(&pila);
    printf("\nDato eliminado %d", dato);

    imprimirPila(pila);
}
void apilar(PtrNodo *ptrPila, int dato)
{
    PtrNodo nuevo = (PtrNodo) malloc(sizeof(Nodo));

    //(*nuevo).dato = dato
    nuevo->dato = dato;
    nuevo->sgt = *ptrPila;

    *ptrPila = nuevo;

}
void imprimirPila(PtrNodo pila)
{
    PtrNodo actual = pila;
    printf("\n\nPila\n");
    while(actual != NULL)
    {
        printf("%d", actual->dato);
        actual = actual->sgt;
    }
}
int desapilar(PtrNodo *ptrPila)
{
    PtrNodo eliminar = *ptrPila;
    int dato = eliminar->dato;

    *ptrPila = eliminar->sgt;
    free(eliminar);

    return dato;
}
