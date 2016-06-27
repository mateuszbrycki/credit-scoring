import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;


public class Neuron {
   
    private List<Float> weight = new ArrayList<Float>();
    private Integer size;
    private Float learningFactor;
   
    public Neuron(Integer n, Float learningFactor) {
        this.size = n;
        this.learningFactor = learningFactor;
       
        Random rand = new Random();
       
        for(int i = 0; i < n; i++) {
            Float tempWeight = rand.nextFloat()*(1.0f + 1.0f)-1.0f;
            weight.add(tempWeight);
        }
    }

    public Float feed(List<Double> x) {
        float distance = 0;

        for (int i = 0; i < this.size; i++) {
            distance += Math.pow((weight.get(i) - x.get(i)), 2);
        }

        return new BigDecimal(Math.sqrt(distance)).floatValue();
    }

/*    public Float feed(ArrayList<Double> x) {
        return this.distance(this.weight, x);
    }*/

    private Float distance(List<Float> first, List<Double> second) {
        Double diffSquareSum = 0.;

        for (int i = 0; i < first.size(); i++) {
            diffSquareSum += (first.get(i) - second.get(i)) * (first.get(i) - second.get(i));
        }
        Double sqrt = Math.sqrt(diffSquareSum);

        return new BigDecimal(sqrt).floatValue();
    }
    
    public void updateWeight(ArrayList<Double> el, Float learningRate) {
    	List<Float> newWeight = new ArrayList<Float>();
    	
    	for(Integer i = 0; i < weight.size(); i++) {
    		Float weightElem = weight.get(i);
    		Float newWeightElem = weightElem + (learningRate * ((new BigDecimal(el.get(i)).floatValue()) - weightElem));
    		newWeight.add(newWeightElem);
    	}

        this.weight = newWeight;

    }
   
   
}