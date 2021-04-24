typedef struct
{
    char Chapa[11];
    char Marca[21];
    int Anio;
    int Transferencias;
    struct vehiculo *sig;
} vehiculo;


typedef struct
{
    char Chapa[11];
    int CI;
    char Nombre[21];
    char Apellido[21];
    int Monto;
    struct persona *sig;
} persona;


vehiculo *CabezaVehiculo=NULL, Vehiculo;
persona *CabezaPersona=NULL, Persona;
FILE* archivo;
int bandera=0;


void CargarVehiculos(void);
void CargarTransferencias(void);
void CargarArchivoTransferencias(void);
void CargarArchivoVehiculos(void);
void LeerVehiculo(vehiculo *);
void LeerPersona(persona *);
void AgregarNodoVehiculo(vehiculo **, vehiculo);
void AgregarNodoPersona(persona **, persona);
void AsignarVehiculo(vehiculo **, vehiculo);
void AsignarPersona(persona **, persona);
void ImprimirVehiculos(vehiculo *);
void ImprimirTransferencias(persona *);
int Existe(vehiculo *, char *);
void GuardarArchivos(void);
void IncrementarTransferencias(vehiculo *, char *);



void CargarVehiculos ()
{
    system("cls");
    CargarArchivoVehiculos();
    puts("Cargue todos los vehiculos necesarios\nPresione Ctrl+Z para volver atras\n");
    int i=2;
    puts ("Vehiculo 1");
    LeerVehiculo(&Vehiculo);
    while(!feof(stdin))
    {
        AgregarNodoVehiculo(&CabezaVehiculo, Vehiculo);
        printf ("\nVehiculo %d\n", i);
        LeerVehiculo(&Vehiculo);
        i++;
    }
}



void CargarTransferencias ()
{
    system("cls");
    CargarArchivoTransferencias();
    int i=2;
    bandera=1;
    puts("Cargue todas las personas necesarias que realizaron transferencias\nPresione Ctrl+Z para volver atras\n");
    puts ("Persona 1");
    LeerPersona(&Persona);
    while(!feof(stdin))
    {
        AgregarNodoPersona(&CabezaPersona, Persona);
        printf ("\nPersona %d\n", i);
        LeerPersona(&Persona);
        i++;
    }
}



void LeerVehiculo(vehiculo *Vehiculo)
{
    printf ("Chapa: ");
    fflush(stdin);
    scanf("%[^\n]", Vehiculo->Chapa);
    if(feof(stdin))
        return;
    printf ("Marca: ");
    fflush(stdin);
    scanf("%[^\n]", Vehiculo->Marca);
    if(feof(stdin))
        return;
    printf ("Anho: ");
    scanf("%d", &(Vehiculo)->Anio);
    if(feof(stdin))
        return;
    printf ("Cantidad de transferencias: ");
    scanf("%d", &(Vehiculo)->Transferencias);
}



void LeerPersona(persona *Persona)
{
    printf ("Nombre: ");
    fflush(stdin);
    scanf("%[^\n]", Persona->Nombre);
    if(feof(stdin))
        return;
    printf ("Apellido: ");
    fflush(stdin);
    scanf("%[^\n]", Persona->Apellido);
    if(feof(stdin))
        return;
    printf ("CI: ");
    scanf("%d", &(Persona)->CI);
    if(feof(stdin))
        return;
    printf ("Chapa del auto: ");
    fflush(stdin);
    scanf("%[^\n]", Persona->Chapa);
    if(feof(stdin))
        return;
    printf ("Monto de la transferencia: ");
    scanf("%d", &(Persona)->Monto);
}



void AgregarNodoVehiculo(vehiculo **Cabeza, vehiculo Vehiculo)
{
    vehiculo *Aux, *NodoActual;
    NodoActual=*Cabeza;
    if(!Existe(*Cabeza, Vehiculo.Chapa))
    {
        if(*Cabeza==NULL || strcmp(Vehiculo.Chapa, (*Cabeza)->Chapa)<0)
        {
            Aux=*Cabeza;
            *Cabeza=(vehiculo *)calloc(1, sizeof(vehiculo));
            strcpy((*Cabeza)->Chapa, Vehiculo.Chapa);
            strcpy((*Cabeza)->Marca, Vehiculo.Marca);
            (*Cabeza)->Anio=Vehiculo.Anio;
            (*Cabeza)->Transferencias=Vehiculo.Transferencias;
            (*Cabeza)->sig=Aux;
        }
        else
        {
            while(NodoActual->sig!=NULL && strcmp(Vehiculo.Chapa, NodoActual->Chapa)>0)
            {
                Aux=NodoActual;
                NodoActual=NodoActual->sig;
            }
            if(NodoActual->sig==NULL)
                AsignarVehiculo(&NodoActual, Vehiculo);
            else
            {
                AsignarVehiculo(&Aux, Vehiculo);
                Aux->sig=NodoActual;
            }
        }
    }
}



void AgregarNodoPersona(persona **Cabeza, persona Persona)
{
    persona *Aux, *NodoActual;
    NodoActual=*Cabeza;
    if(Existe(CabezaVehiculo, Persona.Chapa))
    {
        if(*Cabeza==NULL)
        {
            (*Cabeza)=(persona *)calloc(1, sizeof(persona));
            strcpy((*Cabeza)->Chapa, Persona.Chapa);
            strcpy((*Cabeza)->Apellido, Persona.Apellido);
            strcpy((*Cabeza)->Nombre, Persona.Nombre);
            (*Cabeza)->CI=Persona.CI;
            (*Cabeza)->Monto=Persona.Monto;
        }
        else
        {
            while(NodoActual->sig!=NULL)
                NodoActual=NodoActual->sig;
            AsignarPersona(&NodoActual, Persona);
        }
        IncrementarTransferencias(CabezaVehiculo, Persona.Chapa);
    }
}



void AsignarVehiculo(vehiculo **NodoActual, vehiculo Vehiculo)
{
    (*NodoActual)->sig=(vehiculo *)calloc(1, sizeof(vehiculo));
    *NodoActual=(*NodoActual)->sig;
    strcpy((*NodoActual)->Chapa, Vehiculo.Chapa);
    strcpy((*NodoActual)->Marca, Vehiculo.Marca);
    (*NodoActual)->Anio=Vehiculo.Anio;
    (*NodoActual)->Transferencias=Vehiculo.Transferencias;
}



void AsignarPersona(persona **NodoActual, persona Persona)
{
    (*NodoActual)->sig=(persona *)calloc(1, sizeof(persona));
    *NodoActual=(*NodoActual)->sig;
    strcpy((*NodoActual)->Chapa, Persona.Chapa);
    strcpy((*NodoActual)->Apellido, Persona.Apellido);
    strcpy((*NodoActual)->Nombre, Persona.Nombre);
    (*NodoActual)->CI=Persona.CI;
    (*NodoActual)->Monto=Persona.Monto;
}



void CargarArchivoVehiculos()
{
    archivo=fopen("vehiculos.dat", "r");
    if(archivo!=NULL)
    {
        fread(&Vehiculo, sizeof(vehiculo), 1, archivo);
        while(!feof(archivo))
        {
            AgregarNodoVehiculo(&CabezaVehiculo, Vehiculo);
            fread(&Vehiculo, sizeof(vehiculo), 1, archivo);
        }
        fclose(archivo);
    }
}



void CargarArchivoTransferencias()
{
    if(!bandera)
    {
        archivo=fopen("transferencias.dat", "r");
        if(archivo!=NULL)
        {
            fread(&Persona, sizeof(persona), 1, archivo);
            while(!feof(archivo))
            {
                AgregarNodoPersona(&CabezaPersona, Persona);
                fread(&Persona, sizeof(persona), 1, archivo);
            }
            fclose(archivo);
        }
    }
}



void ImprimirVehiculos(vehiculo *NodoActual)
{
    system("cls");
    puts("CHAPA\tMARCA\tANHO\tTRANSFERENCIAS");
    while(NodoActual!=NULL)
    {
        printf("%s\t%s\t%d\t%d\n", (NodoActual)->Chapa, (NodoActual)->Marca, (NodoActual)->Anio, (NodoActual)->Transferencias);
        NodoActual=(NodoActual)->sig;
    }
    system("pause");
}



void ImprimirTransferencias(persona *NodoActual)
{
    system("cls");
    puts("NOMBRE\tAPELLIDO\tCI\tCHAPA\tMONTO");
    while(NodoActual!=NULL)
    {
        printf("%s\t%s\t%d\t%s\t%d\n", (NodoActual)->Nombre, (NodoActual)->Apellido, (NodoActual)->CI, (NodoActual)->Chapa, (NodoActual)->Monto);
        NodoActual=(NodoActual)->sig;
    }
    system("pause");
}



void GuardarArchivos()
{
    vehiculo *NodoVehiculo;
    NodoVehiculo=CabezaVehiculo;
    archivo=fopen("vehiculos.dat", "w");
    if(archivo!=NULL)
    {
        while(NodoVehiculo!=NULL)
        {
            fwrite(NodoVehiculo, sizeof(vehiculo), 1, archivo);
            NodoVehiculo=(NodoVehiculo)->sig;
        }
        fclose(archivo);
    }

    persona *NodoPersona;
    NodoPersona=CabezaPersona;
    archivo=fopen("transferencias.dat", "w");
    if(archivo!=NULL)
    {
        while(NodoPersona!=NULL)
        {
            fwrite(NodoPersona, sizeof(persona), 1, archivo);
            NodoPersona=(NodoPersona)->sig;
        }
        fclose(archivo);
    }
}



int Existe(vehiculo *NodoActual, char Chapa[])
{
    while(NodoActual!=NULL)
    {
        if(!strcmp(NodoActual->Chapa, Chapa))
            return 1;
        NodoActual=(NodoActual)->sig;
    }
    return 0;
}



void IncrementarTransferencias(vehiculo *NodoActual, char Chapa[])
{
    if(bandera)
    {
        while(NodoActual!=NULL)
        {
            if(!strcmp(NodoActual->Chapa, Chapa))
            {
                NodoActual->Transferencias+=1;
                return;
            }
            NodoActual=(NodoActual)->sig;
        }
    }
}



void Informe()
{
    system("cls");
    vehiculo *VehiculoActual;
    persona *PersonaActual;
    int CantVehiculos=0, MontoTotal=0;
    VehiculoActual=CabezaVehiculo;
    puts("\t\t\tDIRECCION DE CONTROL DE AUTOMOTORES");
    while(VehiculoActual!=NULL)
    {
        if(VehiculoActual->Transferencias > 0)
        {
            printf("\nChapa:%s\t\tMarca:%s\tAnho:%d\n", VehiculoActual->Chapa, VehiculoActual->Marca, VehiculoActual->Anio);
            PersonaActual=CabezaPersona;
            while(PersonaActual!=NULL)
            {
                if(!strcmp(VehiculoActual->Chapa, PersonaActual->Chapa))
                    printf("Titular:%s %s\t\t\t\t%d\n", PersonaActual->Nombre, PersonaActual->Apellido, PersonaActual->Monto);
                PersonaActual=PersonaActual->sig;
            }
            printf("Total de transferencias=%d\n", VehiculoActual->Transferencias);
        }
        VehiculoActual=VehiculoActual->sig;
    }
    VehiculoActual=CabezaVehiculo;
    while(VehiculoActual!=NULL)
    {
        if(VehiculoActual->Transferencias > 0)
            CantVehiculos++;
        VehiculoActual=VehiculoActual->sig;
    }
    PersonaActual=CabezaPersona;
    while(PersonaActual!=NULL)
    {
        MontoTotal+=PersonaActual->Monto;
        PersonaActual=PersonaActual->sig;
    }
    printf("\nVEHICULOS=%d\t\t\t\tTOTAL=%d\n", CantVehiculos, MontoTotal);
    system("pause");
}
