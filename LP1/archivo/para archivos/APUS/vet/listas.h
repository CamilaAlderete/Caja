/*Prototipos de las funciones*/
void ElaborarInforme();
void Agregar(ptrServicio *ptrS,servicio serv);
void Informe();

/*Funcion ElaborarInforme*/
void ElaborarInforme(){
	Servicio=NULL;//se inicializa a NULL el apuntador a la lista enlazada de servicios 
	/*Se abre el archivo servicios.txt*/
	if((ptrS=fopen("servicios.txt","r")) == NULL){
		puts("Archivo no existe");
	}else{
		servicio temp; //se declara una variable temporal de tipo servicio
		fread(&temp,sizeof(servicio),1,ptrS);//se lee los datos de primer servicio
		while(!feof(ptrS)){ // se repite hasta que sea fin del archivo
			Agregar(&Servicio,temp);//se envia los datos del servicio a la funcion agregar para que sea añadida a la lista enlaza de los servicios
			fread(&temp,sizeof(servicio),1,ptrS);//se lee los datos del siguiente servicio
		}
		puts("Informe realizado exitosamente");
		system("pause");
		fclose(ptrS); //se cierra el archivo servicios.txt
	}
} //fin de la funcion ElaborarInforme


/*Funcion Informe*/
void Informe(){
	/*se inicializan las variables a ser utilizadas
	i = bandera para identificador de servicio
	M = cantidad de machos
	H = cantidad de hembras
	A = cantidad de animales
	SV = cantidad de servicios
	*/
	int i=0,M=0,H=0,A=0,SV=0;
	char m[]="macho"; //auxiliar para macho
	char h[]="hembra"; //auxiliar para hembra
	puts("\t\t\t\t Facultad de Veterinaria");
	puts("\t\t\t  Control de Vacunacion al 2016/03/12");
	ptrServicio ptr; //se declar un apuntador a tipo servicio
	ptr=Servicio; // ptr apunta a lista enlazada de servicios
	while(ptr != NULL){// mientras ptr no apunte a NULL
		SV++; //se aumenta la cantidad de servicios
		M=H=i=0; //se inicializan la cantidad de machos,hembras y la bandera para los servicios
		/*Se abre el archivo animales.txt*/
		if((ptrA=fopen("animales.txt","r")) == NULL){
			puts("No existe el archivo");
		}else{
			animal temp; // se declara una variable temporal de tipo animal
			fread(&temp,sizeof(animal),1,ptrA);//se lee los datos del primer animal
			while(!feof(ptrA)){// se repite hasta que sea el final del archivo
				if(!strncmp(ptr->codigo,temp.codigo,3)){ //si los codigos son iguales, procede
					if(!strncmp(temp.sexo,m,1)){ //pregunta si el animal es macho
						M++; //de ser macho, aumenta el contador de machos
					}else{
						H++; //de ser hembra, aumenta el contador de hembras
					}
					if(i == 0){//controla que el codigo del servicio solo se imprima una vez
						i++; //activa la bandera
						/*imprime el codigo de servicio y la raza*/
						printf("Servicio: %s \t\tRaza: %s \n",temp.codigo,ptr->raza);
						printf("Encargado: %s\n",ptr->responsable);
					}
					/*Imprime los datos del animal*/
					printf("Identificador: %d \t\t\t\t\t\tFecha: %d/%d/%d\n",temp.identificador,temp.vacunacion.dia,temp.vacunacion.mes,temp.vacunacion.ano);
				}
				fread(&temp,sizeof(animal),1,ptrA);//lee los datos del siguiente animal
			}
			fclose(ptrA);//cierra el archivo animal.txt
		}
		printf("Machos: %d \t\tHembras: %d  \t\t\tTotal corral: %d\n\n",M,H,M+H);//imprime la cantidad de machos y hembras que hay en el corral
		A=A+H+M;//aumenta el contador de animales
		ptr=ptr->ptrSiguiente;//pasar al siguinte servicio de la lista enlazada
	}
	printf("Total Servicios: %d \t\t\tTotal Animales: %d\n",SV,A);//imprime el total de servicios y animales
	
	system("pause");
}

/*funcion Agregar*/
void Agregar( ptrServicio *ptrS, servicio serv ){ 
   
   ptrServicio ptrNuevo;    /* apuntador a un nuevo nodo */
   ptrServicio ptrAnterior; /* apuntador a un nodo previo de la lista */
   ptrServicio ptrActual;   /* apuntador al nodo actual de la lista */

   ptrNuevo = (servicio *) malloc( sizeof( servicio ) ); /* crea un nodo */

	if ( ptrNuevo != NULL ) {    /* es espacio disponible */
		strcpy(ptrNuevo->codigo,serv.codigo);   /* coloca los datos del servicio en ptrNuevo */
    	strcpy(ptrNuevo->raza,serv.raza);
    	strcpy(ptrNuevo->responsable,serv.responsable);
    	ptrNuevo->cantidad=serv.cantidad;
		ptrNuevo->ptrSiguiente = NULL; /* el nodo no se liga a otro nodo */
		ptrAnterior = NULL;
	    ptrActual = *ptrS;
	
	    /* ciclo para localizar la ubicación correcta en la lista */
	    while ( ptrActual != NULL && strncmp(serv.codigo,ptrActual->codigo,3) < 0 ) { 
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
   } /* fin de if */
   else {
		printf( "No se inserto. No hay memoria disponible.\n");
   } /* fin de else */

} /* fin de la función insertar */
