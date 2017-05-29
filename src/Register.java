import java.util.Arrays;
import java.util.stream.IntStream;

public class Register {
	
	private int[] values;
	private int[] sequence;
	private int size;
	private int[] code;
	private int codeTheoryLength;
	private boolean codeML;
	
	public Register(int[] sequenceInput, int size){
		this.sequence = sequenceInput;
		this.values = new int[size];
		this.size = size;
		this.initialFill();
		this.code = generateRegisterCode2();
	}
	
	private void initialFill(){
		Arrays.fill(this.values, 0);
		this.values[0] = 1;
	}
	
	public void presentRegister(){
		System.out.println("\n\n=Register=");
		if (!codeML){
			System.out.println("This register will not produce ML code!");
		}
		System.out.println("Size: "+size);
		System.out.print(""
				+ "Sequence: ");
		for (int i=0;i<sequence.length;i++){
			System.out.print(sequence[i]);
		}
		System.out.println("\nCode theoretical length: "+this.codeTheoryLength);
	}
	
	public int[] generateRegisterCode2(){
		codeTheoryLength = (int) (Math.pow(2,this.size)-1);
		int[] code = new int[codeTheoryLength];
		
		
		for (int i=0;i<codeTheoryLength;i++){
			int newFirstBit=this.calculateOutputBit();	//oblicza nowy pierwszy bit rejestru
			code[i] = values[values.length-1];			//"wyrzuca" ostatni bit i zapisuje w tablicy z wygenerowanym kodem
			this.shiftRegister();						//przesuwa rejestr w prawo
			values[0]=newFirstBit;						//wstawia nowy bit na pierwsze miejsce
		} 
		this.codeML=isRegisterInInintialState();
		codeML=isRegisterInInintialState();
		return code;
	}
	
	public void printRegisterCode(){
		if(codeML){
		System.out.println("\n\nRegister code generated:");
		for (int i=0;i<code.length;i++){			
			System.out.print(code[i]);
		}
		System.out.println("");
		}
		else{
			System.out.println("\n\nRegister code generated:");
			System.out.println("Not an ML code! Code was not generated");
		}
	}
	
	private void shiftRegister(){
		for (int j=values.length-1;j>0;j--){
			values[j]=values[j-1];
		}
	}
	
	private int calculateOutputBit(){
		int sumToModulo=0;
		for (int k=0;k<sequence.length;k++){
			sumToModulo=sumToModulo+values[sequence[k]-1];
			//System.out.println("Doda³em "+values[sequence[k]-1]+" z pola "+sequence[k]+" w rejestrze");
		}
		return sumToModulo%2;
	}
	
	private boolean isRegisterInInintialState(){
		if(values[0]==1 && IntStream.of(values).sum()==1){
			return true;
		}else{
			return false;
		}
	}
	
	public int getRegisterSize(){
		return size;
	}
	
	public int[] getRegisterCode(){
		return code;
	}

	public void roundShiftCode() {
		int tmpValue = code[code.length-1];
		for (int j=code.length-1;j>0;j--){
			code[j]=code[j-1];
		}
		code[0] = tmpValue;
	}
	
	public int getCodeLength(){
		return codeTheoryLength;
	}
}
