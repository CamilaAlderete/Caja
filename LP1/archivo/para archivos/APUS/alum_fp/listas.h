ptrNodoLista ptrA=NULL,ptrB=NULL; //se definen los apuntadores a las lista de cada seccion
int cantSeleccionados=0,A=0,B=0; //contadores para la cantidad de seleccionados por seccion y para el total


void insertar(ptrNodoLista *ptrS, NodoLista nodo);//prototipo de funcion insertar
void Seleccion();//prototipo de funcion seleccion
void Informe(); //prototipo de funcion informe


void insertar( ptrNodoLista *ptrS, NodoLista nodo )
{
   ptrNodoLista ptrNuevo;
   ptrNodoLista ptrAnterior;
   ptrNodoLista ptrActual;
   ptrNuevo = malloc( sizeof( NodoLista ) );

   if ( ptrNuevo != NULL ) {
      strcpy(ptrNuevo->nombre,nodo.nombre);
      strcpy(ptrNuevo->apellido,nodo.apellido);
      ptrNuevo->cedula=nodo.cedula;
      ptrNuevo->promedio=nodo.promedio;
      ptrNuevo->asistencia=nodo.asistencia;

      ptrNuevo->ptrSiguiente = NULL;

      ptrAnterior = NULL;
      ptrActual = *ptrS;


      while ( ptrActual != NULL && nodo.apellido < ptrActual->apellido ) {
         ptrAnterior = ptrActual;
         ptrActual = ptrActual->ptrSiguiente;
      }


      if ( ptrAnterior == NULL ) {
         ptrNuevo->ptrSiguiente = *ptrS;
         *ptrS = ptrNuevo;
      }
      else {
         ptrAnterior->ptrSiguiente = ptrNuevo;
         ptrNuevo->ptrSiguiente = ptrActual;
      }

   }
   else {
      printf( "No se inserto. No hay memoria disponible.\n");
   }

}

void Seleccion(){
    ptrA=NULL;
    ptrB=NULL;

    //primeramente se extren los seleccionados de la seccionA
    if((seccionA=fopen("datosA.txt","r")) == NULL){//abre el archivo para extraer los datos
        puts("El archivo no existe");
    }else{
        NodoLista aux;//declara temporal para depositar los datos extraidos del alumno
        fread(&aux,sizeof(NodoLista),1,seccionA);//extrae los datos del alumno y guarda en el temporal "aux"
        while(!feof(seccionA)){//mientras que no sea fin del archivo
            if(aux.promedio > 3.5 && aux.asistencia >=75){//si el promedio del alumno es mayor a 3.5 y su asistencia es mayor o igual a 75
                    insertar(&ptrA,aux);//inserta en la lista de seleccionados de A
                    cantSeleccionados++;//aumenta la cantidad total de seleccionados
                    A++;//aumenta la cantidad de seleccionados en A
            }
            fread(&aux,sizeof(NodoLista),1,seccionA);//extrae los datos del siguiente alumno


        }
        fclose(seccionA);//cierra archivo datosA.txt
    }
    //luego se procede a realizar la seleccion en la seccion B
    if((seccionB=fopen("datosB.txt","r")) == NULL){//abre el archivo para extraer los datos
        puts("El archivo no existe");
    }else{
        NodoLista aux;//declara temporal para depositar los datos extraidos del alumno
        fread(&aux,sizeof(NodoLista),1,seccionB);//extrae los datos del alumno y guarda en el temporal "aux"
        while(!feof(seccionB)){//mientras que no sea fin del archivo
            if(aux.promedio > 3.5 && aux.asistencia >=75){//si el promedio del alumno es mayor a 3.5 y su asistencia es mayor o igual a 75
                    insertar(&ptrB,aux);//inserta en la lista de seleccionados de B
                    cantSeleccionados++;//aumenta la cantidad total de seleccionados
                    B++;//aumenta la cantidad de seleccionados en B
            }

            fread(&aux,sizeof(NodoLista),1,seccionB);//extrae los datos del siguiente alumno


        }
        fclose(seccionB);//cierra archivo datosA.txt
    }
    puts("Seleccion realizada con exito");
    system("pause");
}

void Informe(){
    ptrNodoLista c;//se declara un apuntador temporal
    puts("\t\t\tAlumnos seleccionados\n");
    puts("\t\t\t Seccion A");
    puts("Nombre \t Apellido \t Cedula \t Promedio \t Asistencia " );
    c=ptrA;//se carga el apuntador de A al temporal c
    while(c != NULL){//imprime mientras que c sea distinto a NULL
        printf("%s \t %s \t %d \t %.2f \t\t %.2f\n",c->nombre,c->apellido,c->cedula,c->promedio,c->asistencia);
        c=c->ptrSiguiente;//pasar al siguiente nodo
    }
    printf("\nCantidad seleccionados en A: %d\n\n",A); //imprime la cantidad de seleccionados en A
    //se procede a imprimir los seleccionados de B
    puts("\t\t\t Seccion B");
    puts("Nombre \t Apellido \t Cedula \t Promedio \t Asistencia " );

    c=ptrB;//se carga el apuntador a B en c
    while(c != NULL){//imprime mientras que c sea distinto de NULL
        printf("%s \t %s \t %d \t\t %.2f \t %.2f\n",c->nombre,c->apellido,c->cedula,c->promedio,c->asistencia);
        c=c->ptrSiguiente;//pasar al siguiente nodo
    }
    printf("\nCantidad seleccionados en B: %d\n\n",B);//imprime la cantidad de seleccionados en B
    printf("\nCantidad total de seleccionados: %d\n\n",cantSeleccionados);//imprime la cantidad total de seleccionados
    system("pause");
}
