//Este trabajo fue realizado por Eric Ruiz Diaz y Luis Pereira G16
//Con colaboracion de G03 Camila Alderete

public class matrix{
	
	private int [][] math;
	private int rows;
	private int columns;
	private static matrix m;
	private static matrix s;

	matrix(int fila, int columna){
		math=new int[fila][columna];
		rows=fila;
		columna=rows;
	}

	public int getValor(int fila, int columna){
		return math[fila][columna];
	}

	public int getRows(){
		return this.rows;
	}

	public int getColumns(){
		return this.columns;
	}
	public void setValor(int fila,int columna, int valor){
		this.math[fila][columna]=valor;
	}

	public matrix multiplicar(matrix B){
		matrix C=new matrix(this.getRows(),B.getColumns());
		if(this.getColumns() != B.getRows()){
			System.out.println("Dimensiones incompatibles");
		}else{
			for(int i=0; i<this.getRows(); i++){
				for(int j=0; j<B.getColumns(); j++){
					C.setValor(i,j,0);
					for(int k=0; k<this.getColumns();k++){
						C.setValor(i,j,C.getValor(i,j) + (this.getValor(i,k) * B.getValor(k,j)));
					}
				}
			}
		}
		return C;
	}

	public void matrix_chain_order(String p){
		
		int n=p.length();
		m=new matrix(n,n);
		s=new matrix(n,n);
		int j,q;
		for(int i=0; i<n; i++){
			m.setValor(i,i,0);	
		}

		for(int l=1; l<n; l++){
			for(int i=0;i<n-l+1;i++){
				j=i+l-1;
				m.setValor(i,j,Integer.MAX_VALUE);
				for(int k=0; k<j-2; k++){
						q=m.getValor(i,k)+m.getValor(k+1,j);
						if(q<m.getValor(i,j)){
							m.setValor(i,j,q);
						}
						s.setValor(i,j,k);
				}
			}
		}
	}

	public void print_matrix_parents(int i,int j){
		if(i==j){
			System.out.println("A"+i);
		}else{
			System.out.println("(");
			print_matrix_parents(i,s.getValor(i,j));
			print_matrix_parents(s.getValor(i,j)+1,j);
			System.out.println(")");
		}
	}
}
/*
PRINT-OPTIMAL-PARENS(s, i, j)
1 if i = j
2 	then print "A"i
3 else print "("
4 	PRINT-OPTIMAL-PARENS(s, i, s[i, j])
5 	PRINT-OPTIMAL-PARENS(s, s[i, j] + 1, j)
6	print ")" 

*/