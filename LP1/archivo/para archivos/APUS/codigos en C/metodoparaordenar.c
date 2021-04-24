#include<stdio.h>

int main()
{
    int m=4,n=5;
    int mat[m][n];
    int aux;


    int i,j;
    int fil1,fil2,col1,col2;

    for (i=0; i<m*n-1; i++)
    {
        for (j=0; j<m*n-1; j++)
        {
            fil1= j/n;
            col1= j%n;

            fil2= (j+1)/n;
            col2= (j+1)%n;

            if (mat[fil1][col1] > mat[fil2][col2])
            {
                aux= mat[fil1][col1];
                mat[fil1][col1]= mat[fil2][col2];
                mat[fil2][col2]= aux;
            }
        }
    }
    return 0;
}
