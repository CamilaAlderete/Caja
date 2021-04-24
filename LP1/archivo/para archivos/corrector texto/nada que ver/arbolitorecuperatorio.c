Nodo *buscar(Nodo *raiz, int dato){

      if(!raiz)
       return 0;
     else if( dato==raiz->dato)
      return raiz;
     else if(dato<raiz->dato)
      return buscar(raiz->izq,dato);
     else if(dato>raiz->dato)
       return buscar(raiz->dcho,dato);
}

void insertar(Nodo** raiz, int dato){
      if(!(*raiz))
       (*raiz)=crearnodo(dato);
      else if(dato<(*raiz)->dato)
        insertar(&((*raiz)->izq),dato);
      else if(dato>(*raiz)->dato)
        insertar(&((*raiz)->dcho),dato);
}
  
Nodo* crearnodo(int dato){

      Nodo * t;
      t=(Nodo*)malloc(sizeof(Nodo));
      t->dato=dato; 
      t->izq=t->dcho=NULL;
}