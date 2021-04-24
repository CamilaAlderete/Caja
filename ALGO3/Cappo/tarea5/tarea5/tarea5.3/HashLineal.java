/*
	Eric Ruiz Diaz
	Ruben Izembrandt
	G09
*/
public class HashLineal{
	public String tabla[]=new String[1000];
	public static int entradas_exitosa=0;
	public static int entradas_no_exitosa=0;
	public static int elementos=0;
	HashLineal(){
		for(int i=0;i<1000;i++){
			this.tabla[i]="void";
		}
	}
	public void insertar(int key,String s){
		if(this.tabla[key].equals("void")){
			tabla[key]=s;
			entradas_exitosa++;
			elementos++;
			//System.out.println("primer if");
		}else{
			int j=key;
			while(j<1000){
				j++;
				if(this.tabla[j].equals("void")){
					tabla[key]=s;				
					elementos++;
					//System.out.println("segundo if");
					break;
				}
				entradas_no_exitosa++;
			}
		}
	}
	
}