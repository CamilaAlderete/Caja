FILE* abrirArchivo(char nombreArchivo[]);
void cargaCamion();
void cargaSalida();
void actualizarServ(int idCamion, long int desplazar);
void listaCamion();
void listaSalida();
void procesoFinMes();
void informeMantenimiento();
FILE* abrirArchivo(char nombreArchivo[]){
    FILE *ptrArchivo;
    ptrArchivo = fopen(nombreArchivo, "ab"); //comprobamos si existe el archivo
    if(ptrArchivo != NULL){
        return ptrArchivo;
    }else{
        ptrArchivo = fopen(nombreArchivo, "ab+"); //sino existe lo creamos
        return ptrArchivo;
    }
}
void cargaCamion(){   //funcion para cargar los datos de un vehiculo
    Camiones camion;
    char nombreArchivo[] = "camiones.dat";     //leemos los datos introducidos por el usuario
    FILE *ptrArchivo;
    ptrArchivo = abrirArchivo(nombreArchivo);   //abrimos el archivo
    printf("Ingrese el identificador del camion:\n");
    scanf("%d", &camion.idCamion);
    printf("Ingrese la marca del camion:\n");
    fflush(stdin);
    gets(camion.marca);
    printf("Ingrese modelo del camion:\n");
    fflush(stdin);
    gets(camion.modelo);
    camion.cantServicios = 0; //la cantidad de servicios siempre es 0 al cargar un nuevo camion
    fwrite(&camion, sizeof(Camiones), 1, ptrArchivo);       //guardamos los datos en el archivo
    printf("La carga del camion se realizo correctamente\n");
    fclose(ptrArchivo); //cerramos el archivo
}
void cargaSalida(){
    listaCamion();
    Salidas salida;
    char nombreArchivo[] = "salidas.dat";     //leemos los datos introducidos por el usuario
    FILE *ptrArchivo;
    ptrArchivo = abrirArchivo(nombreArchivo);   //abrimos el archivo
    printf("Ingrese el identificador del camion:\n");
    scanf("%d", &salida.idCamion);
    printf("Ingrese la cantidad de personales:\n");
    scanf("%d", &salida.cantPer);
    printf("Ingrese la fecha de salida:\n");
    do{                                     //validamos el dia
        printf("Dia:\n");
        scanf("%d", &salida.fSalida.dia);
    }while(!(salida.fSalida.dia > 0 && salida.fSalida.dia <= 31));
    do{                 //validamos el mes
        printf("\nMes:\n");
        scanf("%d", &salida.fSalida.mes);
    }while(!(salida.fSalida.mes > 0 && salida.fSalida.mes <= 12));
    printf("\nAnho:\n");
    scanf("%d", &salida.fSalida.anho);
    printf("Ingrese la hora de salida\n");
    do{
        printf("Hora:\n");      //validamos la hora
        scanf("%d", &salida.hSalida.hora);
    }while(!(salida.hSalida.hora >= 0 && salida.hSalida.hora <= 23));
     do{
        printf("Minuto:\n");    //validamos los minutos
        scanf("%d", &salida.hSalida.minuto);
    }while(!(salida.hSalida.minuto >= 0 && salida.hSalida.minuto <= 59));
    fwrite(&salida, sizeof(Salidas), 1, ptrArchivo);       //guardamos los datos en el archivo
    printf("La carga de la salida se realizo correctamente\n");
    fclose(ptrArchivo); //cerramos el archivo
}
void actualizarServ(int idCamion, long int desplazar){  //funcion para actualizar la cantidad de servicios
    FILE* ptrArchivo;
    Camiones camion;
    ptrArchivo = fopen("camiones.dat", "rb+");      //abrimos el archivo
    if(ptrArchivo != NULL){
        fread(&camion, sizeof(Camiones), 1, ptrArchivo);    //nos desplazamos hasta el registro que queremos actualizar
        while(!feof(ptrArchivo)){
            if(camion.idCamion == idCamion){
                desplazar = ftell(ptrArchivo);
                break;
            }
            fread(&camion, sizeof(Camiones), 1, ptrArchivo);
        }
        camion.cantServicios++;
        fseek(ptrArchivo, desplazar - sizeof(Camiones), SEEK_SET);
        fwrite(&camion, sizeof(Camiones), 1, ptrArchivo);       //escribimos los nuevos datos
    }
    fclose(ptrArchivo);
}

void listaCamion(){
    FILE *ptrArchivo;
    Camiones camion;
    ptrArchivo = fopen("camiones.dat", "rb");  //abrimos el archivo
    printf("----------------------------------------------------------------------------------\n"); //imprimimos una cabecera
    printf("%43s\n", "CAMIONES");
    printf("----------------------------------------------------------------------------------\n");
    printf("\t%12s\t%8s\t%11s\t%14s\n", "IdCamion", "Marca", "Modelo", "C.Servicios");
    if(ptrArchivo != NULL){
        fread(&camion, sizeof(Camiones), 1, ptrArchivo);
        while(!feof(ptrArchivo)){       //leemos del archivo e imprimimos
            printf("\t%10d\t%10s\t%10s\t%10d\n", camion.idCamion, camion.marca, camion.modelo, camion.cantServicios);
            fread(&camion, sizeof(Camiones), 1, ptrArchivo);
        }
    }else{
        printf("Error al abrir el archivo\n");
    }
    fclose(ptrArchivo); //cerramos el archivo
}
void listaSalida(){
    FILE *ptrArchivo;
    Salidas salida;
    ptrArchivo = fopen("salidas.dat", "rb");  //abrimos el archivo
    printf("----------------------------------------------------------------------------------\n"); //imprimimos una cabecera
    printf("%43s\n", "SALIDAS");
    printf("----------------------------------------------------------------------------------\n");
    printf("\t%10s\t%10s\t%10s\t%10s\n", "IdCamion", "Personales", "Fecha", "Hora");
    if(ptrArchivo != NULL){
        fread(&salida, sizeof(Salidas), 1, ptrArchivo);
        while(!feof(ptrArchivo)){       //leemos del archivo e imprimimos
            printf("\t%10d\t%10d\t%5d/%d/%d\t%8d:%d\n", salida.idCamion, salida.cantPer, salida.fSalida.dia, salida.fSalida.mes, salida.fSalida.anho, salida.hSalida.hora, salida.hSalida.minuto);
            fread(&salida, sizeof(Salidas), 1, ptrArchivo);
        }
    }else{
        printf("Error al abrir el archivo\n");
    }
    fclose(ptrArchivo); //cerramos el archivo
}

void procesoFinMes(){
    int mesAnt;
    do{
        printf("Ingrese el mes anterior:\n");
        scanf("%d", &mesAnt);
    }while(!(mesAnt > 0 && mesAnt < 13));
    FILE *ptrCamion;
    FILE *ptrSalida;
    Camiones camion;
    Salidas salida;
    ptrCamion = fopen("camiones.dat", "rb+");       //abrimos los archivos
    ptrSalida = fopen("salidas.dat", "rb");
    if(ptrCamion != NULL && ptrSalida != NULL){
        fread(&camion, sizeof(Camiones), 1, ptrCamion);
        while(!feof(ptrCamion)){
            printf("Procesando camion:%d\n", camion.idCamion);          //procesamos los datos
            fread(&salida, sizeof(Salidas), 1, ptrSalida);
            while(!feof(ptrSalida)){
                if(salida.fSalida.mes == mesAnt && salida.idCamion == camion.idCamion){
                    actualizarServ(camion.idCamion, ftell(ptrCamion));  //actualizamos los datos si es necesario
                }
                fread(&salida, sizeof(Salidas), 1, ptrSalida);
            }
            rewind(ptrSalida);      //reubicamos el puntero del archivo de salidas
            fread(&camion, sizeof(Camiones), 1, ptrCamion);
        }
        printf("Proceso completo\n");
    }
    fclose(ptrCamion);
    fclose(ptrSalida);
}
void informeMantenimiento(){    //funcion para imprimir el informe
    FILE *ptrArchivo;
    int cant = 0;
    Camiones camion;
    ptrArchivo = fopen("camiones.dat", "rb");
    printf("----------------------------------------------------------------------------------\n"); //imprimimos una cabecera
    printf("%20s\n", "INFORME DE MANTENIMIENTO");
    printf("----------------------------------------------------------------------------------\n");
    if(ptrArchivo != NULL){
        fread(&camion, sizeof(Camiones), 1, ptrArchivo);
        while(!feof(ptrArchivo)){           //recorremos el archivo y buscamos las coincidencias
            if(camion.cantServicios > 100){
                printf("El camion con id: %d necesita mantenimiento\n", camion.idCamion);
                cant++;
            }
            fread(&camion, sizeof(Camiones), 1, ptrArchivo);
        }
        if(cant == 0){
            printf("Ningun camion necesita mantenimiento\n");
        }
    }else{
        printf("Error al abrir el archivo\n");
    }
    fclose(ptrArchivo);
}
