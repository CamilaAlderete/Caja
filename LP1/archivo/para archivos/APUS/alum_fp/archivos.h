struct nodo{
    char nombre[20];
    char apellido[20];
    int cedula;
    float promedio;
    float asistencia;
    struct nodo *ptrSiguiente;
}; /*Definicion de la estructura para almacenar los datos del alumno*/

typedef struct nodo NodoLista; //sinonimo de struct nodo lista
typedef NodoLista *ptrNodoLista; //sinonimo de NodoLista

FILE *seccionA; //apuntador al archivo A
FILE *seccionB; //apuntador al archivo B



void AgregarA(); //prototipo de funcion agregarA
void AgregarB(); //prototipo de funcion agregarB


void AgregarA(){
    NodoLista alumno; //define un temporal alumno y carga los datos del alumno
    fflush(stdin);
    printf("Nombre: ");
    gets(alumno.nombre);
    fflush(stdin);
    printf("Apellido: ");
    gets(alumno.apellido);
    fflush(stdin);
    printf("Cedula: ");
    scanf("%d",&alumno.cedula);
    fflush(stdin);
    printf("Promedio: ");
    scanf("%f",&alumno.promedio);
    fflush(stdin);
    printf("Asistencia: ");
    scanf("%f",&alumno.asistencia);
    if((seccionA=fopen("datosA.txt","ab+")) == NULL){//crea el archivo y si ya existe lo abre
        puts("No hay memoria suficiente");
    }else{
        fwrite(&alumno,sizeof(NodoLista),1,seccionA); //carga los datos del alumno a el archivo datosA.txt
        fclose(seccionA);//cierra el archivo
    }
    puts("Agregado exitosamente!");
    system("pause");
}

void AgregarB(){
    NodoLista alumno;//define un temporal alumno y carga los datos del alumno
    fflush(stdin);
    printf("Nombre: ");
    gets(alumno.nombre);
    fflush(stdin);
    printf("Apellido: ");
    gets(alumno.apellido);
    fflush(stdin);
    printf("Cedula: ");
    scanf("%d",&alumno.cedula);
    fflush(stdin);
    printf("Promedio: ");
    scanf("%f",&alumno.promedio);
    fflush(stdin);
    printf("Asistencia: ");
    scanf("%f",&alumno.asistencia);
    if((seccionB=fopen("datosB.txt","ab+")) == NULL){//crea el archivo y si ya existe lo abre
        puts("No hay memoria suficiente");
    }else{
        fwrite(&alumno,sizeof(NodoLista),1,seccionB);//carga los datos del alumno a el archivo datosA.txt
        fclose(seccionB);// cierra el archivo
    }
    puts("Agregado exitosamente!");
    system("pause");
}
