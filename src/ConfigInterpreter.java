import javax.management.relation.RelationServiceNotRegisteredException;

public class ConfigInterpreter {
	
	private int register1Length;
	private int register2Length;
	
	private String register1Raw;
	private String register2Raw;
	
	private int[] register1Sequence;
	private int[] register2Sequence;
	
	private int registerLength;
	
	public ConfigInterpreter(String configLine){
	register1Raw = configLine.split(" ")[0];
	register2Raw = configLine.split(" ")[1];
	registerLength = Integer.parseInt(configLine.split(" ")[2]);
	
	register1Length = register1Raw.split(",").length;
	register2Length = register2Raw.split(",").length;
	
	register1Sequence = new int[register1Length];
	register2Sequence = new int[register2Length];
	
	for (int i = 0; i<register1Length; i++){
		register1Sequence[i] = Integer.parseInt(register1Raw.substring(1, register1Raw.length()-1).split(",")[i]);
	}
	for (int i = 0; i<register2Length; i++){
		register2Sequence[i] = Integer.parseInt(register2Raw.substring(1, register2Raw.length()-1).split(",")[i]);
	}
	
	//System.out.println("Konfiguracja kodera:\n"+register1Raw+" "+register2Raw);
	}

	public int getRegisterLength() {
		return registerLength;
	}

	public int[] getRegister1Sequence() {
		return register1Sequence;
	}

	public int[] getRegister2Sequence() {
		return register2Sequence;
	}

}
