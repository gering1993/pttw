import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Autocorelation {
	
	private List<int[]> listOfCodes = new ArrayList<int[]>();
	private List<int[]> listOfAutocorelations = new ArrayList<int[]>();

	public Autocorelation(List<int[]> listOfCodesFromGenerator){
		this.listOfCodes = listOfCodesFromGenerator;
	}
	
	public void calcAutocorelations(){
		for (int[] code : listOfCodes){
			int[] fixedCode = code;
			int[] movingCode = code;
			int[] autoCorelation = new int[code.length];
			for (int i=0; i<code.length;i++){
				rightShiftCode(movingCode);
				autoCorelation[i] = calcBitAnd(fixedCode, movingCode);
			}
			listOfAutocorelations.add(autoCorelation);
		}
	}

	private int calcBitAnd(int[] fixedCode, int[] movingCode) {
		int bitAndSum=0;
		for (int i=0; i<fixedCode.length; i++){
			bitAndSum+=fixedCode[i]*movingCode[i];
		}
		return bitAndSum;
	}

	private void rightShiftCode(int[] movingCode) {
		for (int i=movingCode.length-1;i>0;i--){
			movingCode[i]=movingCode[i-1];
		}
		movingCode[0]=0;
	}
	
	public void printAutocorelations(){
		int i = 0;
		for (int[] autoCorelation : listOfAutocorelations){
			i++;
			System.out.println("Code "+i+" autocorelation:"+Arrays.toString(autoCorelation));
		}
	}
	
	public void generateAutocorelationCharts(){
		int autoCorelationNumber=1;
		for (int[] autoCorelation : listOfAutocorelations){
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for (int i=autoCorelation.length-1;i>0;i--){
				dataset.setValue(autoCorelation[i], "Autokorelacja", Integer.toString(-i));
			}
			for (int i=0;i<autoCorelation.length;i++){
				dataset.setValue(autoCorelation[i], "Autokorelacja", Integer.toString(i));
			}
			
			JFreeChart myChart = ChartFactory.createLineChart("Autokorelacja kodu nr"+autoCorelationNumber, "X", "Y", dataset);	
			try {
				OutputStream out = new FileOutputStream("C:/Chart"+autoCorelationNumber+".png");
				ChartUtilities.writeChartAsPNG(out, myChart, 800, 600);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			autoCorelationNumber++;
		}
	}
}
