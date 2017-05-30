
public class Main {

	public static void main(String[] args) {

		System.out.println("=== PTTW ===");
		System.out.println("Bart³omiej Œwiderek");
		System.out.println("Rafa³ Trojak ");
		System.out.println("Path to config file must be: C:/GoldCoderConfig.txt");
		System.out.println("Run as administrator!");
		System.out.println("Example config file: ");
		System.out.println("[1,3] [2,3] 3");
		
		ConfigReader myCoder = new ConfigReader("C:/config.txt");
		
		ConfigInterpreter myConfig = new ConfigInterpreter(myCoder.ReadConfigFile());
		Generator myGenerator = new Generator(myConfig);
		myGenerator.generateAllCodes();
		myGenerator.printAllCodes();
		
		Autocorelation myAutocorelation = new Autocorelation(myGenerator.getListOfAllCodes());
		myAutocorelation.calcAutocorelations();
		myAutocorelation.printAutocorelations();
		myAutocorelation.generateAutocorelationCharts();
		
		InputReader myInputReader = new InputReader("C:/in.txt");
		myInputReader.readInputFileToArray();
		
		System.out.println("Done");
	}
}