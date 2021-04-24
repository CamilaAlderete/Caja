import java.util.*;

class Problema4{

  
       void agregar_adyacentes(ArrayList<Vertice>vertices){
         arista adyacentes[][]=new arista[vertices.size()][vertices.size()];
         int index;// para guardar los indices
         Vertice auxiliar;
         for(int i=0;i<vertices.size();i++){
          		auxiliar=vertices.get(i);
          		index=vertices.indexOf(auxiliar);
        		cargar_lista(adyacentes,index,vertices); // se llama a  la funcion y envia las listas y el indice del vertice analizado 
           }
          // imprime_Matriz(vertices,adyacentes);
          //imprimir_adyacentes(adyacentes,vertices);
          Dijkstra(adyacentes,vertices);
        }     

        /****************************************************/
        /*se usa una matriz de adyacencia de nxn

            Ejemplo:

            vertices: A,B,C,D,E : numero de vertices :5 ... matriz de 5x5

            Matriz de adyacencia:
            A | 0,B,C,D,E|
            B | A,0,C,D,E|
            C | A,B,0,D,E|
            D | A,B,C,0,E|
            E | A,B,C,D,0|                          */
        /****************************************************/    
        // falta inicializar los vertices adyacentes como si fueran aristas
      
       void cargar_lista(arista adyacentes[][],int INDEX,ArrayList<Vertice>vertices){
            Iterator<Vertice> i=vertices.iterator();
            int cont=0; // un contador para contar las posiciones de los vertices recorridos dentro de la lista (siempre empieza con el primer vertice)
            Vertice aux; // variable auxiliar que guardan los vertices
            arista  A; // variable para inicializar las aristas
            int c=0; // variable para mover las columnas de la matriz de adyacencia
            // INDEX indica la fila de la matriz que esta siendo inicializada
            while(i.hasNext()){  // se recorre la lista de vertices 
             aux=i.next();
              A=new arista(aux,INDEX,vertices,cont+1); // se inicializa la variable arista enviando los vertices adyacentes al constructor
              adyacentes[INDEX][c]=A;
              c++;
              cont++;   							
            }
        }   
       
        void Dijkstra(arista adyacentes[][],ArrayList<Vertice>vertices){
          int tiempo[]=new int[vertices.size()]; // guarda los menores tiempos en recorrer cada vertice desde el vertice 0
          boolean visitado[]=new boolean[vertices.size()];

          for (int i = 0; i <vertices.size(); i++) {
            tiempo[i] = Integer.MAX_VALUE;
            visitado[i] = false;
          }       
          // siempre se buscara el camino mas corto entre el primer(posicion de la casa) que es el vertice 0
          tiempo[0]=0; // el tiempo de desplazamiento al vertice 0 
          arista AUX; //
          int T=0;

          for(int j=0;j< vertices.size()-1;j++){
            T=distancia_minima(tiempo,visitado,vertices.size());
            visitado[T] = true;
            for(int k=0;k<vertices.size();k++){
              AUX=adyacentes[T][k];

              if(!visitado[k]){
                 if(AUX.tiempo>0 && tiempo[T]!=Integer.MAX_VALUE){
                   if(tiempo[T]+AUX.tiempo<tiempo[k]){
                     tiempo[k]=tiempo[T]+AUX.tiempo;
                   }
                 }
              }
            }
          }  

          // se imprime el resultado
          for(int v=0;v<vertices.size();v++){
             System.out.println(v+":"+tiempo[v]);
          }

        }
        
       int distancia_minima(int tiempo[],boolean visitado[],int TAM){
         int min = Integer.MAX_VALUE; // se inicializa todo con infinito
         int indice=0; // indice del vertice con menor tiempo
 
         for (int i=0;i<TAM;i++){
           if (visitado[i]==false && tiempo[i]<= min){
               min = tiempo[i];//
               indice = i;// se guarda el indice
            }
          }
          return indice;
        }

        
        void imprime_Matriz(ArrayList<Vertice> vertices,arista adyacentes[][]){
          
          arista AUX;

          for(int f=0;f<vertices.size();f++){
             
            for(int c=0;c<vertices.size();c++){
              AUX= adyacentes[f][c];
               System.out.print(AUX.tiempo+"\t"); 
               
            }
            System.out.println(" ");
          }
        }
       
 
       void imprimir_vertices(ArrayList<Vertice>vertices){
          System.out.println("entra en imprimir");
          Vertice AUX;
          Iterator<Vertice>i= vertices.iterator();
          System.out.println("vertices:");
          while(i.hasNext()){
            AUX=i.next();
           System.out.println("nvertice:"+AUX.V+"linea:"+AUX.linea+"es casa:"+AUX.casa+"es facultad:"+AUX.F);
          }
        }    

        void imprimir_adyacentes(arista adyacentes[][],ArrayList<Vertice> vertices){

        	Iterator<Vertice>i=vertices.iterator();
          Vertice AUX;
          arista aux;
          int indice=0;//indice del vertice analizado
      
          while(i.hasNext()){ 
            AUX=i.next();
            indice=vertices.indexOf(AUX);
            System.out.println("vertice:"+AUX.V);
            System.out.println("adyacentes:");
            for(int j=0;j<vertices.size();j++){
              aux=adyacentes[indice][j];
              System.out.println("nvertice:"+aux.V+"distancia:"+aux.distancia+"velocidad:"+aux.velocidad+"tiempo:"+aux.tiempo);    
            }
          }
        }

       
 		public static void main(String []args){
   
          ArrayList<Vertice>vertices= new ArrayList<Vertice>();
          int X=0,Y=0;
          int CONTCASO=0; // contador auxiliar 
          int contV=0,contL=0; // contadores de numero de vertices y de lineas de tres respectivamente 
          int casos=0;// numero de casos del problema 
          String siguiente_caso; // bandera String que indica que se va a ingresar otro caso  de prueba
            boolean pos_iniciales=false; // bandera que indica si las posiciones de la casa y la facultad ya se ingresaron
            Scanner reader= new Scanner(System.in); 
          Vertice v;  
     
         casos= reader.nextInt();     
         
         while(CONTCASO!=casos){    
              X= reader.nextInt();
              Y=reader.nextInt(); 
              contV++;
            // pregunta si ya se ingresaron las posicones de la casa y de la facultad       
            if(contV>2 && pos_iniciales==false){
                contL++; // se activa el contador de lineas de trenes
                pos_iniciales=true; //se marca como que ya se ingresaron las posiciones iniciales  
            }
       
              if(X==-1 && Y==-1){
               contV--;// se disminuye el contador , coordenadas (-1,-1) no representan vertice, si no el fin de una linea de tren
               contL++; // se activa el contador de linea de tren , indica que los vertices siguientes pertenecen a otra linea de tren   
              } 
       
            else{
                v=new Vertice(X,Y,contV,contL); // se llama a constructor para inicializar los vertices
                  vertices.add(v);// se agregan los vertices a la lista
              }
              siguiente_caso=reader.nextLine(); //para evitar un salto de linea
              reader.skip("\n");
              System.out.println("salir?");
              siguiente_caso=reader.nextLine();// ingresar espacio para salir o enter para continuar cargando vertices
              if(siguiente_caso.equals(" ")){ //se espera que se ingrese un espacio para pasar al siguiente caso
                CONTCASO++;
              }
           
          }       
          Problema4 funcion= new Problema4();

              //funcion.imprimir_vertices(vertices);
             funcion.agregar_adyacentes(vertices);
      }
  }

   class Vertice{
      int  V; // vertice
   	  int x,y; // coordenadas de cada vertice
      int linea; // indica de que linea de tren es , hay distintas lineas
      boolean casa;// bandera que indica si el vertice es la posicion de la casa ,
      boolean F; // bandera que indica si el vertice es la posicion de la facultad,

        Vertice(){

        }
       // metodo  donde se inicializa los datos de cada vertice
   	    Vertice (int X,int Y,int nvertice,int nlinea){ 
   	   		this.x=X;
   	   		this.y=Y;
        	this.V=nvertice;
   	   		if(nvertice==1){
                this.casa=true; // si el es el primer vertice ingresado siempre es la posicion de la casa
    	   	}
    	   	else if(nvertice==2){
    	   		this.F=true; // si es el segundo vertice ingresado siempre es la posicion de la facultad
    	   	}

    	   if(nlinea!=0){  // si se detecta que se activo el contador de linea se define el vertice como de linea de tren
    	   	this.linea= nlinea; 
    	   }
   	   }
    }
   
   class arista{
   	   int V; //numero de vertice
   	   double distancia; // distancia entre los vertices
   	   int tiempo; // el tiempo que toma en llegar al otro vertice
   	   double velocidad;// la velocidad dependiendo si el camino es caminando o en tren
   	   boolean camina; // variable que indica si el camino  es en tren o caminando,'true' caminando y 'false' es en tren 
       
       arista( Vertice V, int INDEX,ArrayList<Vertice> vertices,int nvertice){
            Vertice VERTICE;
       	    int D=0;
       	    int potencias=0,p1=0,p2=0;
       	   

       	    VERTICE= vertices.get(INDEX);
       	    this.V= nvertice;
       	    // se determina los desplazamientos y la velocidad
       	    if(INDEX==0){ // si el vertice adyacente es el vertice que indica la posicion de la casa , el desplazamiento es caminando
              this.camina=true;
              // la velocidad cuando el desplazamiento es caminando es de 10 km/h
              this.velocidad=500/3;  
       	    }
       	    else if(V.linea!=VERTICE.linea){ // si el desplazamiento es entre dos lineas de trenes diferentes
       	    	this.camina=true;
       	    	this.velocidad=500/3;
       	    }
       	    else if(INDEX==1){ // si el vertice adyacente es el vertice que indica la posicion de la facultad
               this.camina=true;
       	       this.velocidad=500/3;
       	    }
       	    else if( V.linea == VERTICE.linea){ // el desplazamiento entre trenes de una misma linea
               this.camina=false;
               this.velocidad=2000/3; // el metro viaja a 40 km/h
            }        
     

       	    // se halla la distancia entre los dos vertices adyacentes, aplicando la formula de distancia entre dos puntos
            p1=(int)Math.pow(VERTICE.x-V.x,2); //(x2-x1)^2
            p2=(int)Math.pow(VERTICE.y-V.y,2);// (y2-y1)^2
            potencias=p1+p2;
            D=(int)Math.sqrt(potencias);
            this.distancia=D;

            // se halla el tiempo del desplazamiento usando la formula de V=S/t
            int T=0;
            T= (int)Math.round(D/this.velocidad); // se guarda el tiempo redondeado al numero mas cercano
            this.tiempo=T;  
       }

   }
