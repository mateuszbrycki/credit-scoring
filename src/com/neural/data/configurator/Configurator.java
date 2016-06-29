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

    public void setup(List<Double[]> data) {
        this.data = data;

        //TODO mbrycki scale data columns

        this.prepareDataStatistics();
    }

    private void prepareDataStatistics() {
        for(int i = 0; i < data.get(0).length; i++) {
            statistics.add(this.getStatisticsForColumn(i));
        }
    }

    private Entry getStatisticsForColumn(Integer columnNumber) {

        Double min = 0., max = 0., firstBound, secondBound;

        for(Double[] dataElement : data) {
            Double dataFromColumn = dataElement[columnNumber];

            if(dataFromColumn < min) {
                min = dataFromColumn;
            }

            if(dataFromColumn > max) {
                max = dataFromColumn;
            }
        }

        Double part = (max - min) / 3;

        firstBound = min + part;
        secondBound = max - part;

        return new Entry(
                min, firstBound, secondBound, max
        );
    }

    public List<DataSetRow> generateDataSets() {
        List<DataSetRow> result = new ArrayList<>();

        for(Double[] input : data) {

            double[] inputRecord = new double[]{
                    this.getValuePreparedValue(input[0],0),
                    this.getValuePreparedValue(input[1],1),
                    this.getValuePreparedValue(input[2],2),
                    this.getValuePreparedValue(input[3],3),
            };

            DataSetRow dataSetRow = new DataSetRow(inputRecord, new double[] {input[4]});
            result.add(dataSetRow);
        }

        return result;
    }

    public Double getValuePreparedValue(Double value, Integer columnNumber) {

        Entry<Double> columnStatistics = this.statistics.get(columnNumber);

        if(value < columnStatistics.getFirstBound()) {
            return 1.;
        }

        if(value > columnStatistics.getFirstBound() && value < columnStatistics.getSecondBound()) {
            return 2.;
        }

        if(value > columnStatistics.getSecondBound()) {
            return 3.;
        }

        return 0.;
    }


}
