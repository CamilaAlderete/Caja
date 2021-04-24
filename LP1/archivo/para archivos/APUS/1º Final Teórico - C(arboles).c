#include <stdio.h>
#include <string.h>

typedef struct
{
    char valor[30];
} cadena;


typedef struct
{
    struct arbol *izq;
    char valor[30];
    struct arbol *der;
} arbol;


void CargarVector(cadena *, int);
void CrearArbol(arbol **, char *);
void AgregarNodo(arbol **, char *);
void OrdenarVector(arbol **, cadena *);
void ImprimirVector(cadena *, int);
void InOrden(arbol *, cadena *, int*);


int main()
{
    int N, i;
    arbol *Raiz;
    printf("Cantidad de cadenas: ");
    scanf("%d", &N);
    cadena V[N];
    CargarVector(V, N);
    CrearArbol(&Raiz, V[0].valor);
    for(i=0; i<N; i++)
        AgregarNodo(&Raiz, &V[i].valor);
    OrdenarVector(&Raiz, V);
    puts("");
    ImprimirVector(V, N);
    return 0;
}


void CargarVector(cadena V[], int TAM)
{
    int i;
    for(i=0; i<TAM; i++)
    {
        printf("Cadena %d: ", i+1);
        fflush(stdin);
        scanf("%[^\n]", V[i].valor);
    }
}


void CrearArbol (arbol **Raiz, char valor[])
{
    *Raiz=(arbol*) calloc (1, sizeof (arbol));
    strcpy((*Raiz)->valor, valor);
}


void AgregarNodo (arbol** Nodo, char valor[])
{
    if (*Nodo==NULL)
    {
        *Nodo=(arbol*) calloc(1,sizeof(arbol));
        strcpy((*Nodo)->valor, valor);
        return;
    }
    else
    {
        if (strcmp(valor, (*Nodo)->valor) < 0)
            AgregarNodo (&((*Nodo)->izq), valor);
        else if (strcmp(valor, (*Nodo)->valor) > 0)
            AgregarNodo (&((*Nodo)->der), valor);
    }
}


void OrdenarVector(arbol **Raiz, cadena V[])
{
    int i=-1;
    InOrden(*Raiz, V, &i);
}


void InOrden(arbol *Nodo, cadena V[], int *i)
{
    if(Nodo!=NULL)
    {
        InOrden(Nodo->izq, V, i);
        strcpy(V[*i=*i+1].valor, Nodo->valor);
        InOrden(Nodo->der, V, i);
    }
}


void ImprimirVector(cadena V[], int TAM)
{
    int i;
    for (i=0; i<TAM; i++)
        printf ("%s\n", V[i].valor);
}
