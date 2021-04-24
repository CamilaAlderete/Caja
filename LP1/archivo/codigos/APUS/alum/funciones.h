typedef struct
{
    char apellido[25];
    char nombre[25];
    int ci;
    float promedio;
    struct alumno* sig;
} alumno;

alumno* Cabeza, *aux;
alumno ALU;
FILE* archivo;

void CrearArchivo(void);
void PasarAL(void);
void PasarLA(void);
void AgregarNuevo(alumno, alumno*);
void ImprimirLista(alumno*);
int Existe(alumno, alumno*);


void CrearArchivo()
{
    system("cls");
    archivo=fopen("alumnos.dat", "r");
    if(archivo!=NULL)
        puts("El archivo ya existe!");
    else
    {
        archivo=fopen("alumnos.dat", "w");
        puts("Se ha creado un nuevo archivo");
    }
    puts("\n");
    system("pause");
    fclose(archivo);
}


void PasarAL()
{
    system("cls");
    archivo=fopen("alumnos.dat", "r");
    if(archivo!=NULL)
    {
        fread(&ALU, sizeof(alumno), 1, archivo);
        while(!feof(archivo))
        {
            AgregarNuevo(ALU, Cabeza);
            fread(&ALU, sizeof(alumno), 1, archivo);
        }
        puts("Paso de archivo a lista exitoso");
    }
    else
        puts("No existe el archivo");
    system("pause");
    fclose(archivo);
}


void AgregarNuevo(alumno ALU, alumno* NodoActual)
{
    if(!Existe(ALU, Cabeza))
    {
        if(Cabeza==NULL || strcmp(ALU.apellido, (Cabeza)->apellido)<0)
        {
            aux=Cabeza;
            Cabeza=(alumno*)calloc(1, sizeof(alumno));
            strcpy((Cabeza)->apellido, ALU.apellido);
            strcpy((Cabeza)->nombre, ALU.nombre);
            (Cabeza)->ci=ALU.ci;
            (Cabeza)->promedio=ALU.promedio;
            (Cabeza)->sig=aux;
        }
        else
        {
            while((NodoActual)->sig!=NULL && strcmp(ALU.apellido, NodoActual->apellido)>0)
            {
                aux=NodoActual;
                NodoActual=(NodoActual)->sig;
            }
            if((NodoActual)->sig==NULL)
            {
                (NodoActual)->sig=(alumno*)calloc(1, sizeof(alumno));
                NodoActual=(NodoActual)->sig;
                strcpy((NodoActual)->apellido, ALU.apellido);
                strcpy((NodoActual)->nombre, ALU.nombre);
                (NodoActual)->ci=ALU.ci;
                (NodoActual)->promedio=ALU.promedio;
            }
            else
            {
                (aux)->sig=(alumno*)calloc(1, sizeof(alumno));
                aux=(aux)->sig;
                strcpy((aux)->apellido, ALU.apellido);
                strcpy((aux)->nombre, ALU.nombre);
                (aux)->ci=ALU.ci;
                (aux)->promedio=ALU.promedio;
                (aux)->sig=NodoActual;
            }
        }
    }
}


void ImprimirLista(alumno* NodoActual)
{
    system("cls");
    puts("NOMBRE\t  APELLIDO\tCI\t\tPROMEDIO");
    while(NodoActual!=NULL)
    {
        printf("%s\t  %s\t\t%d\t  %.2f\%\n", (NodoActual)->nombre, (NodoActual)->apellido, (NodoActual)->ci, (NodoActual)->promedio);
        NodoActual=(NodoActual)->sig;
    }
    system("pause");
}


void PasarLA()
{
    alumno* NodoActual;
    NodoActual=Cabeza;
    system("cls");
    archivo=fopen("alumnos.dat", "a");
    if(archivo!=NULL)
    {
        while(NodoActual!=NULL)
        {
            fwrite(NodoActual, sizeof(alumno), 1, archivo);
            NodoActual=(NodoActual)->sig;
        }
        puts("Paso de datos de lista a archivo exitoso");
    }
    else
        puts("Archivo no fue creado todavia");
    printf("\n");
    system("pause");
    fclose(archivo);
}


int Existe(alumno ALU, alumno* NodoActual)
{
    while(NodoActual!=NULL)
    {
        if((NodoActual)->ci==ALU.ci)
            return 1;
        NodoActual=(NodoActual)->sig;
    }
    return 0;
}
