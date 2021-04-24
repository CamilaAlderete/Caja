#include<stdio.h>
#include<time.h>
#include<string.h>
#include<stdlib.h>
#include<windows.h>

void mostrarMatriz(int f,int c, char matriz[][c]);

int main()
{
	srand(time(NULL));
	char palabras[20];
	int n, m;
	scanf("%d%d", &n, &m);
	char matriz[n][m];
	int i,j;
	char conjunto[] = {'A','A','A','A','B','B','C','C','C','D','D','E','E','E','E','F','F','G',
	'G','H','I','I','I','J','K','L','L','M','M','N','N','Ñ','O','O','O','O','O','P','P','P','Q','R',
	'R','S','S','S','T','T','U','U','V','W','X','Y','Z'};
	for(i=0; i<n; i++){
		for(j=0; j<m; j++){
			matriz[i][j]= conjunto[rand() % 54 ];
		}
	}
	int T, sg=0, min=0;
	scanf("%d", &T);
	while(min<T){
		sg+=5;
		if(sg == 60){
			sg=0;
			min++;
		}
		system("cls");
        mostrarMatriz(n,m,matriz);
		Sleep(5000);
	}

	system("cls");

	scanf("%[^\n]", palabras);

	//proceso de busqueda
	int mov, f, c, f2, c2, primero=0, ultimo=0;
	int posx[n*m], posy[n*m], listafil[m*n], listacol[n*m];
	int movimiento[8][2]={{-1,-1},
                          {-1,0},
                          {-1,1},
                          {0,-1},
                          {0,1},
                          {1,-1},
                          {1,0},
                          {1,1}};
	int k=0, cont=0;
	while(palabras[k]!='\0'){
		for(i=0; i<n; i++){
			for(j=0; j<m; j++){
				if(matriz[i][j]==palabras[k]){
					k++;
					cont++;

					f=i;
					c=j;

					listafil[ultimo]=i;
					listacol[ultimo]=j;

					posx[ultimo]=i;
					posy[ultimo]=j;

					ultimo++;

					while(primero<ultimo){
						f=listafil[primero];
						c=listacol[primero];

						primero++;

						for(mov=0; mov<8; mov++){
							f2=f+movimiento[mov][0];
							c2=c+movimiento[mov][1];

							if(f2>=0 && f2<=n && c2>=0 && c2<=m){
								if(matriz[f2][c2]==palabras[k]){
									listafil[ultimo]=i;
									listacol[ultimo]=j;

									posx[ultimo]=i;
									posy[ultimo]=j;

									ultimo++;
									k++;
									cont++;
								}
							}
						}
					}
				}
			}
		}
	}
	for(i=0; i<cont; i++){
		printf("%d", posx[i]);
		printf("%d", posy[i]);
	}

	return 0;
}
void mostrarMatriz(int f,int c,char matriz[][c]){
    int i,j;
	for(i=0;i<f;i++){
		for(j=0;j<c;j++){
			printf(" %c",matriz[i][j]);
		}
		printf("\n");
	}
}
