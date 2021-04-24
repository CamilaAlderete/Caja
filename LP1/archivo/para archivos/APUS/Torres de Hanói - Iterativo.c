#include <stdio.h>
#include <stdlib.h>

int main() {
    int n, x;
    printf("Cuantos Discos?  ");
    scanf("%d", &n);
    printf("\n");
    for (x = 1; x < (1 << n); x++)
        printf("Mover de la Torre %i a la Torre %i.\n", ((x & x - 1) % 3) + 1, (((x | x - 1) + 1) % 3) + 1) ;
    return 0;
}
