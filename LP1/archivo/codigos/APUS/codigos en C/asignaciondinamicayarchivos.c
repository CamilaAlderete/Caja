#include<stdio.h>
#include<stdlib.h>

struct persona
{
    int peso;
    int altura;
    int edad;
};
typedef struct persona Persona;

int main()
{
    int n;
    int i;
    int *v;
    Persona *lista;
    Persona p;
    FILE * ptrf;

    printf("\nIntroducir la cantidad de notas: ");
    scanf("%d", &n);

    v = (int *)malloc(sizeof(int)*n);

    lista = (Persona *)malloc(sizeof(Persona) * n);

    ptrf = fopen("archivo.txt", "r");
    if(ptrf != NULL)
    {
        i = 0;
        fscanf(ptrf, "%d", &p.peso);
        while (!feof(ptrf))
        {
            fscanf(ptrf, "%d", &p.altura);
            fscanf(ptrf, "%d", &p.edad);

            lista[i] = p;
            i++;

            fscanf(ptrf, "%d", &p.peso);
        }
        printf("\n\nN\tPeso\tAltura\tEdad");
        for(i=0; i<n; i++)
        {
            printf("\n%d\t%d\t%d\t%d", i+1, lista[i].peso, lista[i].altura, lista[i].edad);
        }
    }
    else
    {
        printf("\nERROR en la apertura de archivo");
    }
}
