import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
	
	private List<Neuron> neurons = new ArrayList<Neuron>();
	private Integer size;
	private Float learningRate;
	
	public NeuralNetwork(Integer size, Float learningRate) {
		this.learningRate = learningRate;
		this.size = size;

		for(int i = 0; i < size; i++) {
			Neuron neuron = new Neuron(4, this.learningRate);
			neurons.add(neuron);
		}

	}

	public void train(ArrayList<ArrayList<Double>> learningSet, Integer epohNumber, Float learningRate) {

		for(int i = 0; i < epohNumber; i++) {
			for(ArrayList<Double>  setElement : learningSet) {
				Float[] s = this.neuralFeed(setElement);
				Integer winnerId = this.findWinner(s);
				this.learnWinner(winnerId, setElement,learningRate); 
			}
		}

	}	
	
	private Float[] neuralFeed(ArrayList<Double> setElement) {
		Float[] s = new Float[size];

		int i = 0;
		for(Neuron neuron : neurons) {
			s[i++] = neuron.feed(setElement);
		}

		return s;
	}
	
	public Integer[] neuralRespond(ArrayList<Double> setElement) {
		Integer[] respond = new Integer[this.size];		
		Float[] s = neuralFeed(setElement);
		
		Integer winnerId = this.findWinner(s);
		fillWithZeros(respond);
		respond[winnerId] = 1;
		
		return respond;
	}
	
	private void fillWithZeros(Integer[] s) {
		for(Integer i = 0; i < s.length; i++) {
			s[i] = 0;
		}
	}
	
	private Integer findWinner(Float[] s) {
		
		return this.maxIndex(s);
	}
	
	private static Integer maxIndex(Float[] list) {
		Integer maxIndex = 0;
		Float max = null;
		/*for (Float x : list) {
			if ((x != null) && ((max == null) || ( x > max ))) {
		    	max = x;
		    	maxIndex = i;
			}
		    i++;
		}*/

		for (int i = 0; i < list.length; i++) {
			if (list[i] > list[maxIndex]) {
				maxIndex = i;
			}
		}

		return maxIndex;
	}
	
	void learnWinner(Integer winnerId, ArrayList<Double> el, Float learningRate) {

		Neuron winner = neurons.get(winnerId);
		winner.updateWeight(el, learningRate);

		neurons.set(winnerId, winner);

	}

}
