
import java.nio.channels.ReadableByteChannel;

public class Main {

	public static void main(String[] args) {

		System.out.println("=== PTTW ===");
		System.out.println("Bart�omiej �widerek");
		System.out.println("Rafa� Trojak \n");
		Reader myCoder = new Reader();
		
		ConfigInterpreter myConfig = new ConfigInterpreter(myCoder.ReadConfigFile());
		Generator myGenerator = new Generator(myConfig);
	}
	


}