
import java.nio.channels.ReadableByteChannel;

public class Main {

	public static void main(String[] args) {

		System.out.println("=== PTTW ===");
		System.out.println("Bart³omiej Œwiderek");
		System.out.println("Rafa³ Trojak \n");
		Reader myCoder = new Reader();
		
		ConfigInterpreter myConfig = new ConfigInterpreter(myCoder.ReadConfigFile());
		Generator myGenerator = new Generator(myConfig);
	}
	


}