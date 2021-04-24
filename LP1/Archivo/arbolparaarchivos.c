Nodo* crearnodo(palabra dato){

	Nodo* t;
	t=(Nodo*)malloc(sizeof(Nodo));
	t->der=t->izq=NULL;
	t->D=dato;
	return t;
}


void insertar_en_arbol(Nodo** raiz,palabra AUX){
    int r;// para la comparacion de palabras en la raiz y el nuevo nodo;
   if((*raiz)){  
    r=strcmp(AUX.p_correcta,(*raiz)->D.p_correcta); 
   }
    if(!(*raiz)){

    	*raiz=crearnodo(AUX);
    }
    else if(r<0){
    	insertar_en_arbol(&((*raiz)->izq),AUX);
    }
    else if(r>0){
      insertar_en_arbol(&((*raiz)->der),AUX);
    }

}
  

void visualizar(Nodo*raiz){
  if(raiz){
  	 visualizar(raiz->izq);
  	 printf("%s %s \n",raiz->D.p_correcta,raiz->D.p_incorrecta);
  	 visualizar(raiz->der);
   }
}

int buscar_en_arbol(Nodo*raiz,palabra NUEVO){
    int r;
    if(raiz){
      r=strcmp(NUEVO.p_correcta,raiz->D.p_correcta);
    }

   if(!raiz){
        return 0;
    }
    else if(r==0){
        return 1;        
    }
    else if(r<0){
      return buscar_en_arbol(raiz->izq,NUEVO);
    }
    else if(r>0){
      return buscar_en_arbol(raiz->der,NUEVO);
    }
}
