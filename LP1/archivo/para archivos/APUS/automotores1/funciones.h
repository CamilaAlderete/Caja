struct nodo{
    char chapa[10];
    char marca[20];
    int ano;
    struct nodo *ptrSiguiente;
};

struct tran{
    char chapa[10];
    int cedula;
    char nombre[20];
    char apellido[20];
    int monto;
    struct tran *ptrSiguiente;
};

typedef struct tran NodoTransferencia;
typedef NodoTransferencia *ptrTransferencia;

typedef struct nodo NodoAuto;

ptrTransferencia ptrTR;

void CargarVehiculo();
void ListarVehiculos();
void CargarTransferencia();
void ListarTransferencias();
void insertarT(ptrTransferencia *ptrS,NodoTransferencia nodo);
void CreacionDeLista();
void Informe();

FILE * ptrV;
FILE * ptrT;


void CargarVehiculo(){
    NodoAuto vehiculo;
    fflush(stdin);
    printf("Chapa: ");
    gets(vehiculo.chapa);
    fflush(stdin);
    printf("Marca: ");
    gets(vehiculo.marca);
    fflush(stdin);
    printf("A%co: ",164);
    scanf("%d",&vehiculo.ano);
    if((ptrV=fopen("vehiculos.txt","ab+")) == NULL){
        puts("No hay memoria suficiente");
    }else{
        fwrite(&vehiculo,sizeof(NodoAuto),1,ptrV);
        puts("Carga realizada con exito");
        system("pause");
        fclose(ptrV);
    }

}

void CargarTransferencia(){
    NodoTransferencia transferencia;
    fflush(stdin);
    printf("Chapa: ");
    gets(transferencia.chapa);
    fflush(stdin);
    printf("Cedula: ");
    scanf("%d",&transferencia.cedula);
    fflush(stdin);
    printf("Nombre: ");
    gets(transferencia.nombre);
    fflush(stdin);
    printf("Apellido: ");
    gets(transferencia.apellido);
    fflush(stdin);
    printf("Monto: ");
    scanf("%d",&transferencia.monto);
    if((ptrT=fopen("tranferencias.txt","ab+")) == NULL){
        puts("No hay memoria suficiente");
    }else{
        fwrite(&transferencia,sizeof(NodoTransferencia),1,ptrT);
        puts("Carga realizada con exito");
        system("pause");
        fclose(ptrT);
    }
}

void ListarVehiculos(){
    if((ptrV=fopen("vehiculos.txt","r")) == NULL){
        puts("El archivo no existe");
    }else{
        puts("\t\tLISTA DE VEHICULOS");
		NodoAuto temp;
        fread(&temp,sizeof(NodoAuto),1,ptrV);
        while(!feof(ptrV)){
            printf("CHAPA: %s\nMARCA: %s\nMODELO: %d\n",temp.chapa,temp.marca,temp.ano);
        	puts("");
            fread(&temp,sizeof(NodoAuto),1,ptrV);
        }
    fclose(ptrV);
    }
    
    system("pause");
}

void ListarTransferencias(){
    if((ptrT=fopen("tranferencias.txt","r")) == NULL){
        puts("No existe el archivo");
    }else{
        NodoTransferencia temp;
        fread(&temp,sizeof(NodoTransferencia),1,ptrT);
        puts("\t\tLISTA DE TRANFERENCIAS");
		while(!feof(ptrT)){
            printf("CHAPA: %s\nTITULAR: %s %s\nCEDULA: %d\nMONTO: %d\n",temp.chapa,temp.nombre,temp.apellido,temp.cedula,temp.monto);
            puts("");
            fread(&temp,sizeof(NodoTransferencia),1,ptrT);
        }
        fclose(ptrT);
    }
    system("pause");
}

void CreacionDeLista(){
	ptrTR=NULL;
	if((ptrT=fopen("tranferencias.txt","r")) == NULL){
		puts("El archivo no existe");
	}else{
		NodoTransferencia temp;
		fread(&temp,sizeof(NodoTransferencia),1,ptrT);
		while(!feof(ptrT)){
			insertarT(&ptrTR,temp);	
			fread(&temp,sizeof(NodoTransferencia),1,ptrT);
		}
		puts("Lista creada exitosamente");
		
		fclose(ptrT);
	}
	system("pause");
}

void insertarT( ptrTransferencia *ptrS, NodoTransferencia nodo ){

    ptrTransferencia ptrNuevo;    /* apuntador a un nuevo nodo */
    ptrTransferencia ptrAnterior; /* apuntador a un nodo previo de la lista */
    ptrTransferencia ptrActual;   /* apuntador al nodo actual de la lista */

    ptrNuevo = malloc( sizeof( NodoTransferencia ) ); /* crea un nodo */

    if ( ptrNuevo != NULL ) {    /* es espacio disponible */
        strcpy(ptrNuevo->chapa,nodo.chapa);
        strcpy(ptrNuevo->nombre,nodo.nombre);
        strcpy(ptrNuevo->apellido,nodo.apellido);
        ptrNuevo->cedula = nodo.cedula;
        ptrNuevo->monto=nodo.monto;
        ptrNuevo->ptrSiguiente = NULL; /* el nodo no se liga a otro nodo */

        ptrAnterior = NULL;
        ptrActual = *ptrS;

        /* ciclo para localizar la ubicación correcta en la lista */
        while ( ptrActual != NULL && strncmp(nodo.chapa,ptrActual->chapa,strlen(nodo.chapa))>0 ) {
            ptrAnterior = ptrActual;          /* entra al ...   */
            ptrActual = ptrActual->ptrSiguiente;  /* ... siguiente nodo */
        } /* fin de while */

        /* inserta un nuevo nodo al principio de la lista */
        if ( ptrAnterior == NULL ) {
            ptrNuevo->ptrSiguiente = *ptrS;
            *ptrS = ptrNuevo;
        } /* fin de if */
        else { /* inserta un nuevo nodo entre ptrAnterior y ptrActual */
            ptrAnterior->ptrSiguiente = ptrNuevo;
            ptrNuevo->ptrSiguiente = ptrActual;
        } /* fin de else */
    }
}

void Informe(){
	ptrTransferencia aux;
	int T=0,i;
	aux=ptrTR;
	puts("\t\tDireccion de Control de Automotores");
	while(aux != NULL){
		i=0;
		if((ptrT=fopen("tranferencias.txt","r")) == NULL){
			puts("El archivo no existe");
		}else{
			NodoAuto temp;
			fread(&temp,sizeof(NodoAuto),1,ptrT);
			while(!(feof(ptrT))){
				if(!strncmp(aux->chapa,temp.chapa,strlen(temp.chapa))){
					if(i == 0){
						printf("CHAPA: %s \t\t MARCA: %s \t\t MODELO: %d \n",temp.chapa,temp.marca,temp.ano);
						i++;
					}
					T += aux->monto;
					printf("TITULAR: %s %s \t\t\t %d",aux->nombre,aux->apellido,aux->monto);
					fread(&temp,sizeof(NodoAuto),1,ptrT);
				}
			}
			fclose(ptrT);
		}
		aux = aux->ptrSiguiente;
	}
	system("pause");
}

