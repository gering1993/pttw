
import java.nio.channels.ReadableByteChannel;

public class Main {

	public static void main(String[] args) {

		System.out.println("=== PTTW ===");
		System.out.println("Bart�omiej �widerek");
		System.out.println("Rafa� Trojak ");
		System.out.println("Path top config file must be: C:/GoldCoderConfig.txt");
		System.out.println("Example config file: ");
		System.out.println("[1,3] [2,3] 3");
		Reader myCoder = new Reader();
		
		ConfigInterpreter myConfig = new ConfigInterpreter(myCoder.ReadConfigFile());
		Generator myGenerator = new Generator(myConfig);
		myGenerator.generateAllCodes();
		myGenerator.printAllCodes();
	}
}