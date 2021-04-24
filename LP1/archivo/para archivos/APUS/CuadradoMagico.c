#include <stdio.h>
#include "CuadradoMagico.h"

int main()
{
    scanf("%d", &N);
    int Cuadrado[N][N];
    LeerMatriz(Cuadrado);
    if(CuadradoMagico(Cuadrado))
    {
        puts("Es un cuadrado magico!");
        ImprimirMatriz(Cuadrado);
        ImprimirSumas(Cuadrado);
    }
    else
        puts("No es un cuadrado magico :(");
    return 0;
}
