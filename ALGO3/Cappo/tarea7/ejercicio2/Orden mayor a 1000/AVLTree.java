/*
	Integrantes:
	G16 - Luis Pereira y Eric Ruiz Diaz

	Con la participacion y creditos respectivos a G03 Camila Alderete

*/

import java.util.ArrayList;


  
public class AVLTree  
{  

	class Nodo  
	{  
	    int dato, height;  
	    Nodo izq, der;  
	  
	    Nodo(int d)  
	    {  
	        dato = d;  
	        height = 1;  
	    }  
	}  

    Nodo raiz;  
  
  
    int height(Nodo N)  
    {  
        if (N == null)  
            return 0;  
        return N.height;  
    }  
  
  
    int max(int a, int b)  
    {  
        return (a > b) ? a : b;  
    }  
  
   
    Nodo rightRotate(Nodo y)  
    {  
        Nodo x = y.izq;  
        Nodo T2 = x.der;  
  
       //rotacion
        x.der = y;  
        y.izq = T2;  
  
        // Update heights  
        y.height = max(height(y.izq), height(y.der)) + 1;  
        x.height = max(height(x.izq), height(x.der)) + 1;  
  
       
        return x;  
    }  
  
  
    Nodo leftRotate(Nodo x)  
    {  
        Nodo y = x.der;  
        Nodo T2 = y.izq;  
  
     
        y.izq = x;  
        x.der = T2;  
  
      
        x.height = max(height(x.izq), height(x.der)) + 1;  
        y.height = max(height(y.izq), height(y.der)) + 1;  
  
       
        return y;  
    }  
  
    
    int getBalance(Nodo N)  
    {  
        if (N == null)  
            return 0;  
        return height(N.izq) - height(N.der);  
    }  
  
    Nodo insertar(Nodo node, int dato)  
    {  
       
        if (node == null)  
            return (new Nodo(dato));  
  
        if (dato < node.dato)  
            node.izq = insertar(node.izq, dato);  
        else if (dato > node.dato)  
            node.der = insertar(node.der, dato);  
        else 
            return node;  
  
       
        node.height = 1 + max(height(node.izq),  
                            height(node.der));  
  
      
        int balance = getBalance(node);  
  
      
        if (balance > 1 && dato < node.izq.dato)  
            return rightRotate(node);  
  
        // Right Right Case  
        if (balance < -1 && dato > node.der.dato)  
            return leftRotate(node);  
  
        // Left Right Case  
        if (balance > 1 && dato > node.izq.dato)  
        {  
            node.izq = leftRotate(node.izq);  
            return rightRotate(node);  
        }  
  
        // Right Left Case  
        if (balance < -1 && dato < node.der.dato)  
        {  
            node.der = rightRotate(node.der);  
            return leftRotate(node);  
        }  
  
        /* return the (unchanged) node pointer */
        return node;  
    }  
  
   
    Nodo minValueNode(Nodo node)  
    {  
        Nodo current = node;  
  
       
        while (current.izq != null)  
        current = current.izq;  
  
        return current;  
    }  
  
    Nodo deleteNode(Nodo raiz, int dato)  
    {  
       
        if (raiz == null)  
            return raiz;  
  
      
        if (dato < raiz.dato)  
            raiz.izq = deleteNode(raiz.izq, dato);  
  
        else if (dato > raiz.dato)  
            raiz.der = deleteNode(raiz.der, dato);  
  
       
        else
        {  
  
            // node with only one child or no child  
            if ((raiz.izq == null) || (raiz.der == null))  
            {  
                Nodo temp = null;  
                if (temp == raiz.izq)  
                    temp = raiz.der;  
                else
                    temp = raiz.izq;  
  
                // No child case  
                if (temp == null)  
                {  
                    temp = raiz;  
                    raiz = null;  
                }  
                else // One child case  
                    raiz = temp; // Copy the contents of  
                                // the non-empty child  
            }  
            else
            {  
  
                // node with two children: Get the inorder  
                // successor (smallest in the der subtree)  
                Nodo temp = minValueNode(raiz.der);  
  
                // Copy the inorder successor's data to this node  
                raiz.dato = temp.dato;  
  
                // Delete the inorder successor  
                raiz.der = deleteNode(raiz.der, temp.dato);  
            }  
        }  
  
        // If the tree had only one node then return  
        if (raiz == null)  
            return raiz;  
  
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE  
        raiz.height = max(height(raiz.izq), height(raiz.der)) + 1;  
  
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether  
        // this node became unbalanced)  
        int balance = getBalance(raiz);  
  
        
        // Left Left Case  
        if (balance > 1 && getBalance(raiz.izq) >= 0)  
            return rightRotate(raiz);  
  
        // Left Right Case  
        if (balance > 1 && getBalance(raiz.izq) < 0)  
        {  
            raiz.izq = leftRotate(raiz.izq);  
            return rightRotate(raiz);  
        }  
  
        // Right Right Case  
        if (balance < -1 && getBalance(raiz.der) <= 0)  
            return leftRotate(raiz);  
  
        // Right Left Case  
        if (balance < -1 && getBalance(raiz.der) > 0)  
        {  
            raiz.der = rightRotate(raiz.der);  
            return leftRotate(raiz);  
        }  
  
        return raiz;  
    }  

    void busqueda(int dato){
    	busqueda(raiz,dato);
    }

     void busqueda(Nodo raiz, int dato) { 
  
       
        if (raiz != null && raiz.dato == dato) { 
           //System.out.println("Encontrado");
        }else if (raiz ==null) {
        	//System.out.println("No encontrado");
        }else{

	        if (dato < raiz.dato) 
	            busqueda(raiz.izq, dato); 
	        else if (dato > raiz.dato) 
	            busqueda(raiz.der, dato); 
	  

        } 

    } 
  
    /*void preOrder(Nodo node)  
    {  
        if (node != null)  
        {  
            System.out.print(node.dato + " ");  
            preOrder(node.izq);  
            preOrder(node.der);  
        }  
    }  

    public static void preorder(Nodo raiz, ArrayList list){
    	if (raiz != null){
	    	list.add(raiz.dato);
	    	preorder(raiz.izq,list);
	    	preorder(raiz.der,list);
	    }
    }*/

    
  
    /*public static void main(String[] args)  
    {  
        AVLTree tree = new AVLTree();  
  
  		int a[]={9,5,10,0,6,11,-1,1,2};

  		for (int i=0 ; i<9; i++) {
  			tree.raiz = tree.insertar(tree.raiz, a[i]);	
  		}
       
        
        ArrayList<Integer> list = new ArrayList<Integer>();
		

        AVLTree.preorder(tree.raiz,list);

       
        for (int obj : list ) {
        	System.out.println(obj+"");
        }
 
  
       
        System.out.println("Preorder traversal of "+  
                            "constructed tree is : ");  
        tree.preOrder(tree.raiz);  
  
        tree.raiz = tree.deleteNode(tree.raiz, 10);  
  
        System.out.println("");  
        System.out.println("Preorder traversal after "+  
                        "deletion of 10 :");  
        tree.preOrder(tree.raiz); 

        

    } */ 
} 