import java.util.Arrays;

public class Generator {
	
	private Register register1;
	private Register register2;
	
	public Generator(ConfigInterpreter configClass){
		register1 = new Register(configClass.getRegister1Sequence(), configClass.getRegisterLength());
		register2 = new Register(configClass.getRegister2Sequence(), configClass.getRegisterLength());
		
		register1.presentRegister();
		register2.presentRegister();
		
		register1.printRegisterCode();
		register2.printRegisterCode();
		
	}
	
}
