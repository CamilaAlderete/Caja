#include<stdio.h>
#include<stdlib.h>

/* estructura auto-referenciada */
struct nodoPila {
	int dato; /* define un dato como int */
	struct nodoPila *ptrSiguiente; /* apuntador a nodoPila */
};

typedef struct nodoPila NodoPila; /* sinónimo de la estructura nodoPila */
typedef NodoPila *ptrNodoPila; /* sinónimo para NodoPila* */

void empujar( ptrNodoPila *ptrCima, int info );
int sacar( ptrNodoPila *ptrCima );
int estaVacia( ptrNodoPila ptrCima );
void imprimePila( ptrNodoPila ptrActual );
void instrucciones( void );

 int main(){

	ptrNodoPila ptrPila = NULL; /* apunta al tope de la pila */
	int eleccion; /* elección de menú del usuario */
	int valor; /* entrada int del usuario */

	instrucciones(); /* despliega el menú */
	printf("?");
	scanf( "%d", &eleccion );
	/* mientras el usuario no introduzca 3 */
	while ( eleccion != 3 ) {

		switch ( eleccion ) {

		/* empuja el valor dentro de la pila */
			case 1:
				printf( "Introduzca un entero: " );
				scanf( "%d", &valor );
				empujar( &ptrPila, valor );
				imprimePila( ptrPila );
				break;

		/* saca el valor de la pila */
			case 2:
			/* si la pila no está vacía */
				if ( !estaVacia( ptrPila ) ) {
					printf( "El valor sacado es %d.\n", sacar( &ptrPila ) );
				} /* fin de if */

				imprimePila( ptrPila );
				break;
			default:
				printf( "Eleccion no valida.\n\n" );
				instrucciones();
				break;

		}

		printf("?");
		scanf( "%d", &eleccion );
	}

	printf( "Fin del programa.\n" );

	return 0;

}

/* despliega las instrucciones del programa para el usuario */
void instrucciones( void ){

	printf( "Introduzca su eleccion:\n"
		    "1 para empujar un valor dentro de la pila\n"
		    "2 para sacar un valor de la pila\n"
		    "3 para terminar el programa\n");
}

/* Inserta un nodo en la cima de la pila */
void empujar( ptrNodoPila *ptrCima, int info ){

	ptrNodoPila ptrNuevo; /* apuntador al nuevo nodo */

	ptrNuevo = malloc( sizeof( NodoPila ) );

	/* inserta el nodo en la cima de la pila */
	if ( ptrNuevo != NULL ) {
		ptrNuevo->dato = info;
		ptrNuevo->ptrSiguiente = *ptrCima;
		*ptrCima = ptrNuevo;
	}
	else { /* no queda espacio disponible */
		printf( "%d no se inserto. Memoria insuficiente.\n", info );
	}

}

/* Elimina un nodo de la cima de la pila */
int sacar( ptrNodoPila *ptrCima ){

	ptrNodoPila ptrTemp; /* apuntador a un nodo temporal */
	int valorElim; /* valor del nodo */

	ptrTemp = *ptrCima;
	valorElim = ( *ptrCima )->dato;
	*ptrCima = ( *ptrCima )->ptrSiguiente;
	free( ptrTemp );
	return valorElim;
}

/* Imprime la pila */
void imprimePila( ptrNodoPila ptrActual ){

	/* si la pila está vacía */
	if ( ptrActual == NULL ) {
	printf( "La pila está vacia.\n\n" );
	}
	else {
		printf( "La pila es:\n" );

	/* mientras no sea el final de la pila */
		while ( ptrActual != NULL ) {
			printf( "%d —>", ptrActual->dato );
			ptrActual = ptrActual->ptrSiguiente;
		}
		printf( "NULL\n\n" );
	}
}

/* Devuelve 1 si la pila está vacía, de lo contrario 0 */
int estaVacia( ptrNodoPila ptrCima ){

	return ptrCima == NULL;

 }