import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class InputReader {
	
	private String pathToFile;
	
	public InputReader(String path){
		this.pathToFile = path;
	}
	
	public int[] readInputFileToArray(){
		
		StringBuilder mySB = new StringBuilder();
		try {
			Scanner myScanner = new Scanner(new FileReader(pathToFile));
			while(myScanner.hasNext()){
				mySB.append(myScanner.next());
			}
			myScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String outString = mySB.toString();
		String[] inputDataString = outString.split("");
		
		int[] inputDataInt = new int[inputDataString.length];
		
		for (int i=0;i<inputDataInt.length;i++){
			inputDataInt[i] = Integer.parseInt(inputDataString[i]);
		}
		return inputDataInt;
	}

}
