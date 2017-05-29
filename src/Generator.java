import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generator {
	
	private Register register1;
	private Register register2;
	private int[][] arrayOfCodes;
	private List<int[]> listOfCodes = new ArrayList<int[]>();
	
	public Generator(ConfigInterpreter configClass){
		register1 = new Register(configClass.getRegister1Sequence(), configClass.getRegisterLength());
		register2 = new Register(configClass.getRegister2Sequence(), configClass.getRegisterLength());
		
		register1.presentRegister();
		register2.presentRegister();
		
		register1.printRegisterCode();
		register2.printRegisterCode();
	}
	
	public void generateAllCodes(){
		listOfCodes.add(register1.getRegisterCode());
		listOfCodes.add(register2.getRegisterCode());
		for (int i=0;i<register1.getCodeLength();i++){
			listOfCodes.add(getRegistersSum(register1,register2));
			register1.roundShiftCode();
		}
	}

	private int[] getRegistersSum(Register registerA, Register registerB) {
		int[] sumRegister = new int[registerA.getCodeLength()];
		for (int i=0;i<registerA.getCodeLength();i++){
			sumRegister[i]=(registerA.getRegisterCode()[i]+registerB.getRegisterCode()[i])%2;
		}
		return sumRegister;
	}
	
	public void printAllCodes(){
		System.out.println("\nAll codes:");
		int codeNum=0;
		for(int[] code : listOfCodes){
			codeNum++;
			System.out.print("Code no. "+codeNum+": ");
			for (int i=0; i<code.length;i++){
				System.out.print(code[i]);
			}
			System.out.println("");
		}
	}
	
}
