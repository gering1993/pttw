import java.util.Arrays;

public class Register {
	
	private int[] values;
	private int[] sequence;
	private int size;
	
	public Register(int[] sequenceInput, int size){
		this.sequence = sequenceInput;
		this.values = new int[size];
		this.size = size;
		this.initialFill();
	}
	
	private void initialFill(){
		Arrays.fill(this.values, 0);
		this.values[0] = 1;
	}
	
	public void presentRegister(){
		System.out.println("\n\n=Register=");
		System.out.println("Size: "+size);
		System.out.print("Values: ");
		for (int i=0;i<values.length;i++){
			System.out.print(values[i]);
		}
		System.out.print("\nSequence: ");
		for (int i=0;i<sequence.length;i++){
			System.out.print(sequence[i]);
		}
	}
	
	public int[] generateRegisterCode(){
		System.out.println("\n\n");
		int codeLength = (int) (Math.pow(2,this.size)-1);
		int[] code = new int[codeLength];
		
		for (int i=0;i<codeLength;i++){
			code[i] = values[values.length-1];
			
			int sumToModulo=0;
			for (int k=0;k<sequence.length;k++){
				sumToModulo=sumToModulo+values[sequence[k]-1];
				//System.out.println("Doda³em "+values[sequence[k]-1]+" z pola "+sequence[k]+" w rejestrze");
				
			}
			
			for (int j=values.length-1;j>0;j--){
				values[j]=values[j-1];
			}

			values[0]=sumToModulo%2;
			//System.out.println("Wynik: "+values[0]);
			
		}
		
		System.out.println("Wygenerowany kod z rejestru:");
		for (int i=0;i<code.length;i++){			
			System.out.print(code[i]);
		}
		
		return code;
	}

}
