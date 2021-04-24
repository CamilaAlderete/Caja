import java.io.*;
import java.util.*;
/*
	Trabajo hecho por G16 Luis Pereira y Eric Ruiz Diaz con participacion de G03 Camila Alderete

*/
class TipoString{
	private String stringType;

	TipoString(int maxString){
		this.stringType= new String(new char[maxString]).replace('\0', ' ');
	}

	public void set(String var){
		this.stringType=var;
	}
	public void set(TipoString var){
		this.stringType=var.get();
	}
	public String get(){
		return this.stringType;
	}
}
class TipoBuffer{
	private TipoString bufType[];

	TipoBuffer(int maxString,int bufSize){
		for (int i=0; i<bufSize; i++) {
			this.bufType[i]=new TipoString(maxString);
		}
	}

	public void set(TipoString var,int k){
		this.bufType[k]=var;
	}

	public TipoString get(int k){
		return this.bufType[k];
	}
}
class TipoPath{
	private String PathType;

	TipoPath(int size){
		this.PathType = new String(new char[size]).replace('\0', ' ');
	}

	public void set(String var){
		this.PathType=var;
	}
	public String get(){
		return this.PathType;
	}
}
public class extsort{
	public static int FillBuffer(File inFile, TipoBuffer buffer, boolean moreData, TipoString nextWord){
		int k=0;

		while(moreData && (k< 2048)){
			nextWord.set(buffer.get(k));
			if(inFile.exists()){
				moreData = false;
			}else{
				moreData=true;
			}
			k++;
		}
		return k;
	}

	public static void QuickSort(TipoBuffer buf, int lower, int upper){
		int pivotIndex;

		if(lower<upper){
			pivotIndex = Partition(buf,lower,upper);
			QuickSort(buf,lower,pivotIndex -1 );
			QuickSort(buf,pivotIndex +1 ,upper);
		}
	}
    public static void Swap(TipoString first,TipoString second){
   		TipoString temp = new TipoString(128);
   		first.set(temp.get());
   		second.set(first.get());
   		temp.set(second.get());
    }

   public static int Partition(TipoBuffer buf, int lower, int upper){
   		int left,right;
   		TipoString pivot = new TipoString(128);
   		buf.set(pivot,lower);
   		left=lower;
   		right=upper;
   		while(left<right){
   			while( pivot.get().equalsIgnoreCase(buf.get(left).get()) && (left<upper)){
   				left++;
   			}
   			while( pivot.get().equalsIgnoreCase(buf.get(right).get()) ){
   				right--;
   			}
   			if(left<right){
   				Swap(buf.get(left),buf.get(right));
   			}
   		}

   		buf.set(buf.get(right),left);
   		buf.set(pivot,right);

   		return right;
    }

	public static int MakeSortedRuns(File datafile, TipoPath filename) throws IOException{
		File outFile;
		TipoString word = new TipoString(128);
		String extension;
		TipoPath outFileName= new TipoPath(128);
		int numWords, k , numFiles=0 , contador =0;
		TipoBuffer buffer = new TipoBuffer(128,2048);
		boolean moreData;
		FileReader lector = new FileReader(datafile);
		if(lector.read()>=0){
			moreData = false;
		}else{
			moreData = true;
		}

		while(moreData){
			contador++;
			numWords = FillBuffer(datafile, buffer, moreData, word);
			QuickSort(buffer,0,numWords-1);
			outFileName.set("ExtSortTemp.");
			extension=Integer.toString(numFiles);
			outFileName.get().concat(extension).concat("endl");

			outFile = new File(extension);
			FileWriter writer = new FileWriter(outFile);
			for (k=0; k<numWords; k++) {
				writer.write(buffer.get(k).get());
			}
			writer.close();
			numFiles++;
		}
		return numFiles;
		
	}
	
	public static void main(String args[])throws IOException{
		TipoPath file = new TipoPath(128);	
		int numRuns;

		System.out.println("Enter the name of the text file to be sorted: ");
		Scanner leer = new Scanner(System.in);

		file.set(leer.next());

		File fileName = new File(file.get());

		if(!fileName.exists()){
			System.out.println("Could not open the file named "+file.get());
		}

		numRuns = MakeSortedRuns(fileName,file);
	}
}