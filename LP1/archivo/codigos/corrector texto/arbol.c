#include <stdlib.h>
#include <stdio.h>
#include "arbol.h"
#include <string.h>

nodo* buscar(nodo *raiz,char* dato){
   int a;
  
   
     if(raiz==NULL){
      puts("\nretorna NULL...");
      return NULL;
     }else{
        
        a=strcmp(dato,raiz->dato.p_incorrecta);
        printf("la palabra %s esta siendo comparada con raiz->p_incorrecta %s\n",dato,raiz->dato.p_incorrecta);
       
        if( a==0){
          puts("\nretorna raiz...");
         return raiz;
        }else if(a<0){
         return buscar(raiz->izq,dato);
        }else if(a>0){
          return buscar(raiz->dcho,dato);
        }
    }

}
nodo* crearnodo(registro dato){
     
      nodo * t;
      t=(nodo*)malloc(sizeof(nodo));

      //t->dato.p_correcta=(char*)malloc( (strlen(dato.p_correcta)+1)*sizeof(char) );
      strcpy(t->dato.p_correcta,dato.p_correcta);

      //t->dato.p_incorrecta=(char*)malloc( (strlen(dato.p_incorrecta)+1)*sizeof(char) ); 
      strcpy(t->dato.p_incorrecta,dato.p_incorrecta);
      
      t->izq=t->dcho=NULL;
      

     
      return t;
}

void insertar(nodo** raiz, registro dato){
      
      int a;
      //char *b=*raiz->dato->p_correcta;
      
      if((*raiz)==NULL){
       *raiz=crearnodo(dato);////////////////

       printf("%s\n",(*raiz)->dato.p_correcta);
      
      }else{
          
        a=strcmp(dato.p_correcta,(*raiz)->dato.p_correcta);
    
        if(a<0){
          insertar(&((*raiz)->izq),dato);
        }else if(a>0){
          insertar(&((*raiz)->dcho),dato);
        }
      }
}

void InOrden(nodo*raiz)
{
    if(raiz!=NULL)
    {
        InOrden(raiz->izq);
        printf("%s\t", raiz->dato.p_correcta);
        InOrden(raiz->dcho);
    }
}

/*nodo* buscar(nodo *raiz,char* dato,int n_caracteres_copiados){
   int a;
  
   
     if(raiz==NULL){
      puts("\nretorna NULL...");
      return NULL;
     }else{
        puts("err antes strcmp");
        a=strncmp(dato,raiz->dato.p_incorrecta,n_caracteres_copiados);
        printf("Palabra siendo comparada con raiz:..%s\n",raiz->dato.p_incorrecta);
        puts("err despues strcmp");
        if( a==0){
          puts("\nretorna raiz...");
         return raiz;
        }else if(a<0){
         return buscar(raiz->izq,dato,n_caracteres_copiados);
        }else if(a>0){
          return buscar(raiz->dcho,dato,n_caracteres_copiados);
        }
    }

}*/