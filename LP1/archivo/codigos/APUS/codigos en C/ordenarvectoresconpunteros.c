#include<stdio.h>

int main()
{
    int N = 3;
    int v1[N];
    int *v2;
    int i, j;
    int aux;

    v2 = v1;
    *(v2+0) = 15;
    *(v2+1) = 10;
    *(v2+2) = 20;

    for (i=0; i<=N-1; i++)
    {
        for (j=i; j<=N-1; j++)
        {
            if ( *(v2+j) > *(v2+j+1))
            {
                aux = *(v2+j+1);
                *(v2+j+1) = *(v2+j);
                *(v2+j) = aux;
            }
        }
    }
    for (i=0; i<=N-1; i++)
    {
        printf("%d\n", v1[i]);
    }

    return 0;
}
