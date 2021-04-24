#include <stdio.h>

int main(){
    int n,m;
    scanf("%d%d",&n,&m);
    int mat[n][m],i,j;
    for(i=0;i<n;++i){
        for(j=0;j<m;++j){
            scanf("%d",&mat[i][j]);
        }
    }
    printf("hol");
    int aux=0,min=0,fil,col,ii,jj;
    for(i=0;i<n;++i){
        for(j=0;i<m;++j){
            for(ii=0;ii<n;++ii){
                for(jj=0;jj<m;++jj){
                    aux+=mat[ii][jj]*((i-ii)*(i-ii)+(j-jj)*(j-jj));

                }
            }
                        if(aux<min){
                        min=aux;
                        fil=i;
                        col=j;
                    }
        }
    }
    printf("%d\n",min);
    printf("%d %d",fil,col);
    return 0;
}
