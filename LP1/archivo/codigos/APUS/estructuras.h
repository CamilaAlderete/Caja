typedef struct camiones{
    int idCamion;
    char marca[15];
    char modelo[15];
    int cantServicios;
}Camiones;
typedef struct fecha{
    int dia;
    int mes;
    int anho;
}Fecha;
typedef struct hora{
    int hora;
    int minuto;
}Hora;
typedef struct salidas{
    int idCamion;
    int cantPer;
    Fecha fSalida;
    Hora hSalida;
}Salidas;
