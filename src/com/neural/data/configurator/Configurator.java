package com.neural.data.configurator;

import com.neural.data.statistics.Entry;
import org.neuroph.core.learning.DataSetRow;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Configurator {

    private List<Double[]> data;
    private List<Entry<Double>> statistics = new ArrayList<>();

    private Integer partNumbers;

    public void setup(List<Double[]> data, Integer partNumbers) {
        this.data = data;
        this.partNumbers = partNumbers;

        this.prepareDataStatistics();
    }

    private void prepareDataStatistics() {
        for(int i = 0; i < data.get(0).length-1; i++) {
            statistics.add(this.getStatisticsForColumn(i));
        }
    }

    private Entry getStatisticsForColumn(Integer columnNumber) {

        Double min = 0., max = 0.;

        for(Double[] dataElement : data) {
            Double dataFromColumn = dataElement[columnNumber];

            if(dataFromColumn < min || min.equals(0.)) {
                min = dataFromColumn;
            }

            if(dataFromColumn > max) {
                max = dataFromColumn;
            }
        }

        Double part = (max - min) / this.partNumbers;

        Entry entry = new Entry(
                min, part, this.partNumbers, max
        );

        System.out.println(entry);
        return entry;
    }

    public List<DataSetRow> generateDataSets() {
        List<DataSetRow> result = new ArrayList<>();

        for(Double[] input : data) {

            double[] inputRecord = new double[]{
                    this.getPreparedValue(input[0],0),
                    this.getPreparedValue(input[1],1),
                    this.getPreparedValue(input[2],2),
                    this.getPreparedValue(input[3],3),
            };

            DataSetRow dataSetRow = new DataSetRow(inputRecord, new double[] {input[4]});
            result.add(dataSetRow);
        }

        return result;
    }

    public Double getPreparedValue(Double value, Integer columnNumber) {

        Entry<Double> columnStatistics = this.statistics.get(columnNumber);

        for(int i = 1; i <= partNumbers; i++) {

            Double lowerBound = -100000.;
            if(i != 1)
                lowerBound = columnStatistics.getMin() + columnStatistics.getPart() * (i-1);

            Double upperBound = 100000.;
            if(i != partNumbers)
                upperBound = columnStatistics.getMin() + columnStatistics.getPart() * (i);

            if(lowerBound < value && value <= upperBound) {
                return (double) i;
            }
        }

        return 0.;
    }


}
