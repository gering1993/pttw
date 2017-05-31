import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		System.out.println("=== PTTW ===");
		System.out.println("Bart�omiej �widerek");
		System.out.println("Rafa� Trojak ");
		System.out.println("Path to config file must be: C:/config.txt");
		System.out.println("Run as administrator!!!");
		System.out.println("Example config file: ");
		System.out.println("[1,3] [2,3] 3");
		
		ConfigReader myCoder = new ConfigReader("C:/config.txt");
		
		ConfigInterpreter myConfig = new ConfigInterpreter(myCoder.ReadConfigFile());
		
		// Tworzy obiekt rejestru przyk�adowego i zape�nia go sekwencj� 10000(...)
		int [] initialData = new int[myConfig.getRegisterLength()]; 
		Arrays.fill(initialData, 0);
		initialData[initialData.length-1] = 1;
		
		/*
		// Generacja kod�w
		Generator myGenerator = new Generator(myConfig, initialData);
		myGenerator.generateAllCodes();
		myGenerator.printAllCodes();
		
		// Obliczenie autokorelacji, generacja i zapis wykres�w
		Autocorelation myAutocorelation = new Autocorelation(myGenerator.getListOfAllCodes());	// Tworzy obiekt do obliczenia autokorelacji
		myAutocorelation.calcAutocorelations();													// Oblicza autokorelacje
		myAutocorelation.printAutocorelations();												// Wyrzuca list� obliczonych autokorelacji na konsol�
		myAutocorelation.generateAutocorelationCharts();										// Generuje wykresy i zapisuje na dysku w PNG
		*/
		// Czytanie z pliku wej�ciowego
		InputReader myInputReader = new InputReader("C:/in.txt");								// Obiekt Readera
		int[] inputDataArray = myInputReader.readInputFileToArray();							// Czyta do tablicy dane wej�ciowe
		
		// Generuje kody dla ca�ego zbioru danych wej�ciowych
		for (int i=0;i<inputDataArray.length;i+=myConfig.getRegisterLength()){
			System.out.println("Batch no "+i);
			int[] dataBatch; 																	// Tablica na porcje danych z inputu
			//System.out.println("i: "+i+", inputDataArray.length: "+inputDataArray.length);
			if(i+myConfig.getRegisterLength()-1 <= inputDataArray.length){
				System.out.println("Batch with 5 bits");
				dataBatch = Arrays.copyOfRange(inputDataArray, i, i+myConfig.getRegisterLength());
			}
			else{
				System.out.println("Batch with less bits");
				dataBatch = new int[myConfig.getRegisterLength()];
				Arrays.fill(dataBatch, 0);
				int[] dataBatchValidPart = Arrays.copyOfRange(inputDataArray, i, inputDataArray.length);
				for (int j=0;j<dataBatchValidPart.length;j++){
					dataBatch[j] = dataBatchValidPart[j];
				}
			}
			System.out.println("Creating generator");
			Generator mySerialGenerator = new Generator(myConfig, dataBatch);
			System.out.println("Generating codes");
			mySerialGenerator.generateAllCodes();
			System.out.println("Creating output path");
			String filePath = "C:/out"+i+".txt";
			System.out.println("Saving to file");
			mySerialGenerator.saveFirstCodeToFile(Integer.toString(i), filePath);
		}
		
		System.out.println("Done");
	}
}