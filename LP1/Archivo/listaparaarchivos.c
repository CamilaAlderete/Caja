void insertarOrden(Nodo** cabeza,AUTO entrada){
 	puts("insertando\n");
    Nodo* nuevo;
    nuevo=crearnodo(entrada);
    int r=0;
    if((*cabeza)){
     r=strcmp(entrada.chapa,(*cabeza)->dato.chapa);
    }

    if((*cabeza)==NULL){
        puts("entra\n");
        (*cabeza)=nuevo;
    }
    else if(r>0){

        nuevo->siguiente=*cabeza;
        *cabeza=nuevo;
    }
    else{
        r=strcmp(entrada.chapa,(*cabeza)->dato.chapa);
        Nodo*anterior,*p;
        anterior=p=*cabeza;
        
        while((p->siguiente!=NULL) && (r<0)){
            anterior=p;
            p=p->siguiente;
        }

       if(r>0)
          anterior=p;
          nuevo->siguiente=anterior->siguiente;
          anterior->siguiente=nuevo;
    }
     printf("%s",(*cabeza)->dato.chapa);

   puts("saliendo\n");
  }


   Nodo* crearnodo(AUTO x){
      puts("crea el nodo\n");
      Nodo* a;
      a=(Nodo*)malloc(sizeof(Nodo));
      strcpy(a->dato.chapa,x.chapa);
      strcpy(a->dato.marca,x.marca);
      a->dato.modelo=x.modelo;
      a->siguiente=NULL;
      return a;
   }

   void recorrer(Nodo *cabeza){

       Nodo* aux;
       aux=cabeza;
       while(aux!=NULL){
       	printf("%s\n",aux->dato.chapa);
       	aux=aux->siguiente;
       }
   }

   Nodo* buscar(Nodo **cabeza,TRANSFERENCIA T){

       Nodo*nuevo;
       nuevo=*cabeza;
     
       while(nuevo!=NULL){
       	   if(strcmp(nuevo->dato.chapa,T.chapa)==0)
       	   	 return nuevo;
       	   else{
              nuevo=nuevo->siguiente;
       	   	}
       }


    }