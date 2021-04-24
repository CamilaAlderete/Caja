#include <stdio.h>
#include <math.h>

int N, exito=0;

int main()
{
    printf ("Introduzca N: ");
    scanf ("%d", &N);
    int Solucion[N];
    if (N>3)
    {
        if(Reinas (Solucion, 0))
            ImprimirSolucion (Solucion);
        else
            puts ("No se encontro solucion");
    }
    else
        puts ("No existe solucion, N debe ser mayor a 3");
    return 0;
}

void Reinas (int Solucion[], int etapa)
{
    if (etapa==N)
        return exito=0;
    Solucion[etapa]=-1;
    do
    {
        Solucion[etapa]++;
        if (Valido(Solucion, etapa))
        {
            if (etapa!=N-1)
                Reinas (Solucion, etapa+1);
            else
                exito=1;
        }
    } while (Solucion[etapa]<N-1 && !exito);
    return exito;
}


int Valido (int Solucion[], int etapa)
{
    int i;
    for (i=0; i<etapa; i++)
        if (Solucion[i]==Solucion[etapa] || fabs(Solucion[etapa]-Solucion[i])==fabs(etapa-i))
            return 0;
    return 1;
}


ImprimirSolucion (int Solucion[])
{
    int i,j,p;
    char mat[N][N];
    ini(mat);
	for (i=0; i<N; i++){
        printf ("(%d,%d)   ", Solucion[i], i);
        p=p+i;
		mat[Solucion[i]][i]='R';
        
	}
	
    for(i=0;i<N;i++){
    	printf("\n");
    	for(j=0;j<N;j++){
    		printf("%c ",mat[i][j]);
		}
	}
}

void ini(char mat[][N]){
	int i,j;
	for(i=0;i<N;i++){
    	for(j=0;j<N;j++){
    		mat[i][j]='.';
		}
	}
}
