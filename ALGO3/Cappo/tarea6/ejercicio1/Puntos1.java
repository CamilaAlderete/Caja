
/*
	G16
	Luis Pereira
	Eric Ruiz Diaz
	
	Trabajo hecho con la participacion y cooperacion de G03
*/
public class Puntos1 implements Comparable<Puntos1>{
	public int x;
	public int y;
	
		
	Puntos1(int px,int py){
		this.x=px;
		this.y=py;
		
	}

	@Override
	public int compareTo(final Puntos1 punto1){
				
		
		if ( (this.x - punto1.x)==0) {
			return  punto1.y -this.y;//  ascendente
			//return this.y - punto1.y;//descendente
		}
		return this.x - punto1.x;

	}
}
