#include <stdio.h>
#include <stdlib.h>
#define N 6

int Caballo(int, int, int);
int Valido(int, int);
void InicializarTablero();
void ImpresionSolucion();

int exito=0;
int Tablero[N][N];
int Solucion[N*N][2];
int Movimientos[8][2]={{1,-2},{-1,2},{-2,-1},{-2,1},{-1,2},{1,2},{2,-1},{2,1}};

int main()
{
    int x, y;
    puts ("Introducir coordenadas");
    printf ("x: ");
    scanf ("%d", &x);
    printf ("y: ");
    scanf ("%d", &y);
    if (x<N && y<N && x>=0 && y>=0)
    {
        InicializarTablero();
        Tablero[x][y]=1;
        if (Caballo(x, y, 2))
            ImpresionSolucion();
        else
            puts ("No se encontro solucion");
    }
    else
        puts ("Coordenadas invalidas");
    return 0;
}

int Caballo(int x, int y, int salto)
{
    int nx, ny, k=0;
    exito=0;
    do
    {
        k++;
        nx=x+Movimientos[k-1][0];
        ny=y+Movimientos[k-1][1];
        if(Valido(nx, ny))
        {
            Tablero[nx][ny]=salto;
            if(salto!=N*N)
            {
                Caballo(nx, ny, salto+1);
                if (!exito)
                    Tablero[nx][ny]=0;
            }
            else
                exito=1;
        }
    } while(!exito && k<8);
    return exito;
}

int Valido(int x, int y)
{
    if (x<N && y<N && x>=0 && y>=0)
        if(Tablero[x][y]==0)
            return 1;
    return 0;
}

void InicializarTablero()
{
    int i, j;
    for (i=0; i<N; i++)
        for (j=0; j<N; j++)
            Tablero[i][j]=0;
}

void ImpresionSolucion()
{
    system("cls");
    int i, j;
    for (i=0; i<N; i++)
    {
        for (j=0; j<N; j++)
            printf ("%d\t", Tablero[i][j]);
        printf("\n");
    }
}
