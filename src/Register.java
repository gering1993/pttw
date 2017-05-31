import java.util.Arrays;
import java.util.stream.IntStream;

public class Register {
	
	private int[] values;
	private int[] sequence;
	private int size;
	private int[] code;
	private int codeTheoryLength;
	private boolean codeML;
	private int[] initialValues;
	
	public Register(int[] sequenceInput, int size, int[] dataPartFromInput){
		this.sequence = sequenceInput;
		this.values = new int[size];
		this.size = size;
		//this.initialFill();
		this.fillRegister(dataPartFromInput);
		this.code = generateRegisterCode();
	}
	
	private void fillRegister(int[] dataPartFromInput){
		for (int i=0; i<dataPartFromInput.length;i++){
			this.values[this.values.length-(i+1)] = dataPartFromInput[i];
		}
		this.initialValues = this.values;
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
	
	public int[] generateRegisterCode(){
		codeTheoryLength = (int) (Math.pow(2,this.size)-1);
		int[] code = new int[codeTheoryLength];
		
		
		for (int i=0;i<codeTheoryLength;i++){
			int newFirstBit=this.calculateOutputBit();	//oblicza nowy pierwszy bit rejestru
			code[i] = values[values.length-1];			//"wyrzuca" ostatni bit i zapisuje w tablicy z wygenerowanym kodem
			this.shiftRegister();						//przesuwa rejestr w prawo
			values[0]=newFirstBit;						//wstawia nowy bit na pierwsze miejsce
			//System.out.println("bp");
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
//		if(values[0]==1 && IntStream.of(values).sum()==1){
//			return true;
//		}else{
//			return false;
//		}
		return compareArrays(values, initialValues);
		
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
	

	private boolean compareArrays(int[] array1, int[] array2) {
        boolean b = true;
        if (array1 != null && array2 != null){
          if (array1.length != array2.length)
              b = false;
          else
              for (int i = 0; i < array2.length; i++) {
                  if (array2[i] != array1[i]) {
                      b = false;    
                  }                 
            }
        }else{
          b = false;
        }
        return b;
    }
}
