#include <stdio.h>
#include <string.h>

typedef struct
{
    char valor;
    struct pila *sig;
} pila;


void AgregarPila(pila **, char);
char SacarPila(pila**);
void SumarPilas(pila **, pila **, pila **);
void ImprimirPila(pila **);

int main()
{
    int i;
    pila *InicioPila1=NULL, *InicioPila2=NULL, *InicioPila3=NULL;
    char Numero1[20], Numero2[20];
    printf ("Primer numero: ");
    scanf("%[^\n]", Numero1);
    fflush(stdin);
    printf ("Segundo numero: ");
    scanf("%[^\n]", Numero2);

    /*HACER QUE AMBAS CADENAS TENGAN MISMA LONGITUD (Rellenar con 0 el que tenga menos dígitos
                de modo a que ambos números tengan igual cantidad de dígitos*/

    for(i=0; Numero1[i]!='\0'; i++)
        AgregarPila(&InicioPila1, Numero1[i]);
    for(i=0; Numero2[i]!='\0'; i++)
        AgregarPila(&InicioPila2, Numero2[i]);
    SumarPilas(&InicioPila1, &InicioPila2, &InicioPila3);
    printf("La suma es: ");
    ImprimirPila(&InicioPila3);
    return 0;
}


void AgregarPila(pila **Inicio, char digito)
{
    pila *Aux;
    Aux=*Inicio;
    *Inicio=(pila *)calloc(1, sizeof(pila));
    (*Inicio)->valor=digito;
    (*Inicio)->sig=Aux;
}


void SumarPilas(pila **InicioPila1, pila **InicioPila2, pila **InicioPila3)
{
    int numero, acarreo=0;
    while((*InicioPila1 && *InicioPila2)!=NULL)
    {
        if((numero=(int)(SacarPila(InicioPila1)-48) + (int)(SacarPila(InicioPila2)-48))>=10)
        {
            numero-=10;
            AgregarPila(InicioPila3, (char)(numero+acarreo+48));
            acarreo=1;
        }
        else
        {
            AgregarPila(InicioPila3, (char)(numero+acarreo+48));
            acarreo=0;
        }
    }
    if(acarreo==1)
        AgregarPila(*InicioPila3, (char)(acarreo+48));
}


char SacarPila(pila **Inicio)
{
    char digito=(*Inicio)->valor;
    *Inicio=(*Inicio)->sig;
    return digito;
}


void ImprimirPila(pila **Inicio)
{
    while(*Inicio!=NULL)
        printf("%c", SacarPila(Inicio));
}
