import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    private static Float theta = 0.3f;
    private static Integer epoh = 500;

    public static void main(String[] args) throws IOException {

    	File file = new File("data.txt");
		ArrayList<ArrayList<Double>> dataFromFile = Main.readFile(file);

    	ArrayList<ArrayList<Double>> trainingSet = new ArrayList<ArrayList<Double>>();
		Random rand = new Random();
		Integer trainingSetSize = dataFromFile.size() / 2;
    	for(int i = 0; i < trainingSetSize; i++) {

			Integer number = rand.nextInt(((dataFromFile.size()-1))) ;
			ArrayList<Double> temp = dataFromFile.get(number);
    		trainingSet.add(temp);
			dataFromFile.remove(temp);
    	}
    	
    	NeuralNetwork network = new NeuralNetwork(2, theta);
		System.out.println("Training set size: " + trainingSet.size());
    	network.train(trainingSet, Main.epoh, Main.theta);

		/*
		* Test network
		*/
		for(ArrayList<Double> data : dataFromFile) {
				Integer[] result = network.neuralRespond(data);

			for(Double item : data) {
				System.out.print(item + " ");
			}

			System.out.print(" : ");

			for(Integer i = 0; i < result.length; i++) {
				System.out.print(" " + result[i]);
			}
			System.out.println();
		}

    }
    
    private static ArrayList<ArrayList<Double>> readFile(File fin) throws IOException  {
    	FileInputStream fis = new FileInputStream(fin);
   	 
    	//Construct BufferedReader from InputStreamReader
    	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		ArrayList<ArrayList<Double>> dataFromFile = new ArrayList<ArrayList<Double>>();

    	String line = null;
    	while ((line = br.readLine()) != null) {
    		ArrayList<Double> newArray = new ArrayList<Double>();
    		
    		String[] splitted = line.split(",");
    		
    		if(!splitted[0].isEmpty()) {
	    		newArray.add(Double.parseDouble(splitted[1]));
	    		newArray.add(Double.parseDouble(splitted[2]));
	    		newArray.add(Double.parseDouble(splitted[3]));
	    		newArray.add(Double.parseDouble(splitted[4]));
	    		dataFromFile.add(newArray);
    		}
    		   		
    	}
     
    	br.close();

		return dataFromFile;
    }
}