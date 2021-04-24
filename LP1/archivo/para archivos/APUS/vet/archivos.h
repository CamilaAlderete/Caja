/*Estructura de datos para los servicios*/
struct ser{
	char codigo[4];
	char raza[15];
	char responsable[25];
	int cantidad;
	struct ser *ptrSiguiente;
};
typedef struct ser servicio; //servicio = struct ser
typedef servicio *ptrServicio; // *ptrServicio = servicio *

/*Estructura de datos para la fecha*/
struct fec{
	int dia;
	int mes;
	int ano;
};
typedef struct fec fecha; //fecha = struct fec

/*Estructura de datos para los animales*/
struct ani{
	char codigo[4];
	int identificador;
	char sexo[10];
	fecha vacunacion;
};
typedef struct ani animal;//animal = struct ani

ptrServicio Servicio; //apuntador a la lista enlazada de servicios

FILE *ptrA; //apuntador al archivo animales.txt
FILE *ptrS; //apuntador al archivo servicios.txt

/*Prototipos de las funciones*/
void CargarAnimal();
void CargarServicio();
void ListarServicios();
void ListarAnimales();

/*Funcion Cargar servicio*/
void CargarServicio(){
	servicio temp; //se declara una variable temporal de tipo servicio
	/*Se procde a leer los datos del servicio a ser agregado*/
	fflush(stdin);
	printf("Codigo: ");
	gets(temp.codigo);
	fflush(stdin);
	printf("Raza: ");
	gets(temp.raza);
	fflush(stdin);
	printf("Responsable: ");
	gets(temp.responsable);
	fflush(stdin);
	printf("Cantidad: ");
	scanf("%d",&temp.cantidad);
	/*Se crea el archivo o si ya existe se abre para actualizarlo*/
	if((ptrS=fopen("servicios.txt","ab+")) == NULL){
		puts("Memoria insuficiente para crear archivo");
	}else{
		fwrite(&temp,sizeof(servicio),1,ptrS);//se escribe el nuevo servicio al archivo
		fclose(ptrS);//se cierra el archivo
		puts("Agregdo exitosamente");
	}
	system("pause");
}//fin de la funcion CargarServicio

/*Funcion CargarAnimal*/
void CargarAnimal(){
	animal temp; //se declara una variabe temporal de tipo animal
	/*Se procede a cargar los datos del animal a ser agregado*/
	fflush(stdin);
	printf("Codigo: ");
	gets(temp.codigo);
	fflush(stdin);
	printf("Sexo(m para macho y h para hembra): ");
	gets(temp.sexo);
	fflush(stdin);
	printf("Identificador: ");
	scanf("%d",&temp.identificador);
	fflush(stdin);
	printf("Fecha de ultima vacunacion:\n");
	printf("Dia: ");
	scanf("%d",&temp.vacunacion.dia);
	printf("Mes: ");
	scanf("%d",&temp.vacunacion.mes);
	printf("Ano: ");
	scanf("%d",&temp.vacunacion.ano);
	/*Se crea el archivo animales.txt o si ya existe se abre para ser actualizado*/
	if((ptrA=fopen("animales.txt","ab+")) == NULL){
		puts("Memoria insuficiente");
	}else{
		fwrite(&temp,sizeof(animal),1,ptrA); //se escribe los datos del nuevo animal en el archivo animales.txt
		puts("Agregado exitosamente");
		fclose(ptrA); //ser cierra el archivo animales.txt
	}
	system("pause");
}/*fin de la funcion CargarAnimal*/

/*Funcion ListarServicios*/
void ListarServicios(){
	/*Se abre el archivo servicios.txt*/
	if((ptrS=fopen("servicios.txt","r")) == NULL){
		puts("El archivo no existe");
	}else{
		servicio temp; //se declara una variable temporal de tipo servicio
		puts("\t\t\tLISTA DE SERVICIOS");
		fread(&temp,sizeof(servicio),1,ptrS);//se lee el primer servicio del archivo
		while(!feof(ptrS)){//se realiza hasta que sea el fin del archivo
			/*se procede a imprimir los servicios*/
			printf("CODIGO: %s\n",temp.codigo);
			printf("RAZA: %s\n",temp.raza);
			printf("RESPONSABLE: %s\n",temp.responsable);
			printf("CANTIDAD: %d\n",temp.cantidad);
			puts("");
			fread(&temp,sizeof(servicio),1,ptrS);//lee los datos del siguiente servicio
		}
		system("pause");
		fclose(ptrS);//cierra el archivo servicios.txt
	}
}

/*funcion ListarAnimales*/
void ListarAnimales(){
	/*Se abre el archivo animales.txt*/
	if((ptrA=fopen("animales.txt","r")) == NULL){
		puts("El archivo no existe");
	}else{
		animal temp; // se declara una variable temporal de tipo animal
		puts("\t\t\tLISTA DE ANIMALES");
		fread(&temp,sizeof(animal),1,ptrA); // se lee los datos del primer animal
		while(!feof(ptrA)){//se repoite hasta que sea el fin del archivo
			/*Se procede a imprimir los datos del animal*/
			printf("CODIGO: %s\n",temp.codigo);
			printf("SEXO: %s\n",temp.sexo);
			printf("IDETIFICADOR: %d\n",temp.identificador);
			printf("ULTIMA VACUNACION: %d/%d/%d\n",temp.vacunacion.ano,temp.vacunacion.mes,temp.vacunacion.dia);
			puts("");
			fread(&temp,sizeof(animal),1,ptrA);//se leen los datos del siguiente animal
		}
		system("pause");
		fclose(ptrA);//se cierra el archivo animales.txt
	}
	
}//fin de la funcion ListarAnimales
