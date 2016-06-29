package com.neural.layer;

import com.fuzzy.FuzzyEngine;
import com.neural.data.Entry;
import com.neural.data.configurator.Configurator;
import com.neural.data.provider.FileDataProvider;
import com.neural.data.provider.DataProvider;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MultiLayerBackPropagation {

    private DataProvider dataProvider = new FileDataProvider(new File("data.txt"));

    public MultiLayerBackPropagation() throws Exception {

        DataSet trainingSet = dataProvider.getDataFromFile();

        // create multi layer perceptron
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 4, 2, 1);

        // learn the training set
        System.out.println("Before learning");
        myMlPerceptron.learn(trainingSet);

        System.out.println("After learning");
        testNeuralNetwork(myMlPerceptron, trainingSet);

        // save trained neural network
        myMlPerceptron.save("myMlPerceptron.nnet");

        NeuralNetwork loadedMlPerceptron = NeuralNetwork.load("myMlPerceptron.nnet");

        System.out.println("Testing loaded neural network");
        testNeuralNetwork(loadedMlPerceptron, this.getTestSet());


        System.out.println("Testing fuzzy numbers");
        testFuzzyEngine();

    }

    public static int testNeuralNetwork(NeuralNetwork nnet, DataSet testSet) {

        int result = 0;

        for (DataSetRow dataRow : testSet.getRows()) {
            nnet.setInput(dataRow.getInput());
            nnet.calculate();
            double[] networkOutput = nnet.getOutput();
            result = (int) Math.round(networkOutput[0]);

            System.out.print("Result: " + result + ", ");
            System.out.print("Input: " + Arrays.toString(dataRow.getInput()) + ", ");
            System.out.println(" Output: " + Arrays.toString(networkOutput));
        }

        return result;
    }

    private DataSet getTestSet() {

        Configurator configurator = new Configurator();

        DataSet testSet = new DataSet(4);

        List<Double[]> preparedLines = new ArrayList<>();

        preparedLines.add(new Double[] { 69516.1275728606,23.1621044706553,3503.17615632626,0.0503937183879316, 0. });
        preparedLines.add(new Double[] { 44311.4492623135,28.0171668957919,5522.78669325514,0.124635659297928, 1.});
        preparedLines.add(new Double[] { 43756.0566049069,63.9717958411202,1622.72259832146,0.0370856682304293,  0. });
        preparedLines.add(new Double[] { 69436.5795515478,56.1526170284487,7378.83359873059,0.106267239060253, 0. });
        preparedLines.add(new Double[] { 43662.0926880278,25.2526092599146,7269.59689733384,0.166496758395806, 1.});
        preparedLines.add(new Double[] { 44241.2830004469,19.9825388290919,8733.17929515131,0.19739887053147, 1.});

        configurator.setup(preparedLines, 3);
        List<DataSetRow> dataSet = configurator.generateDataSets();

        for(DataSetRow setElement : dataSet) {

            System.out.println(setElement);
            testSet.addRow(setElement);
        }

        return testSet;
    }

    private void testFuzzyEngine() {
        FuzzyEngine fuzzyEngine = new FuzzyEngine();
        System.out.println(fuzzyEngine.checkSolution(new Entry(69516.1275728606,23.1621044706553,3503.17615632626,0.0503937183879316)));
        System.out.println(fuzzyEngine.checkSolution(new Entry(44311.4492623135,28.0171668957919,5522.78669325514,0.124635659297928)));
        System.out.println(fuzzyEngine.checkSolution(new Entry(43756.0566049069,63.9717958411202,1622.72259832146,0.0370856682304293)));
        System.out.println(fuzzyEngine.checkSolution(new Entry(69436.5795515478,56.1526170284487,7378.83359873059,0.106267239060253)));
        System.out.println(fuzzyEngine.checkSolution(new Entry(43662.0926880278,25.2526092599146,7269.59689733384,0.166496758395806)));
        System.out.println(fuzzyEngine.checkSolution(new Entry(44241.2830004469,19.9825388290919,8733.17929515131,0.19739887053147)));
    }
}