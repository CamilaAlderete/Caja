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
    FILE * ptrf1;
    FILE * ptrf2;

    printf("\nIntroducir la cantidad de notas: ");
    scanf("%d", &n);

    v = (int *)malloc(sizeof(int)*n);

    lista = (Persona *)malloc(sizeof(Persona) * n);

    ptrf1 = fopen("archivo.txt", "r");
    ptrf2 = fopen("salida.txt", "w");
    if(ptrf1 != NULL && ptrf2 != NULL)
    {
        i = 0;
        fscanf(ptrf1, "%d", &p.peso);
        while (!feof(ptrf1))
        {
            fscanf(ptrf1, "%d", &p.altura);
            fscanf(ptrf1, "%d", &p.edad);

            if(i==0)
            {
                fprintf(ptrf2,"%d\t%d\t%d", p.peso, p.altura, p.edad);
            }
            else
            {
                fprintf(ptrf2,"\n%d\t%d\t%d", p.peso, p.altura, p.edad);
            }
            lista[i] = p;
            i++;

            fscanf(ptrf1, "%d", &p.peso);
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
