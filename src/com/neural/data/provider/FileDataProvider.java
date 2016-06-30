package com.neural.data.provider;

import com.neural.data.configurator.Configurator;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataProvider implements DataProvider {

    @Autowired
    private Configurator configurator = new Configurator();

    private File file;

    private List<String> fileLines = new ArrayList<>();

    public FileDataProvider(File file) {
        this.file = file;
    }

    public DataSet getDataFromFile() throws Exception{
        FileInputStream fis = new FileInputStream(this.file);

        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        DataSet dataFromFile = new DataSet(4, 1);

        String line = null;
        while ((line = br.readLine()) != null) {
            fileLines.add(line);
        }

        br.close();

        List<Double[]> preparedLines = this.prepareLines();

        configurator.setup(preparedLines, 3);
        List<DataSetRow> dataSet = configurator.generateDataSets();

        for(DataSetRow setElement : dataSet) {

            System.out.println(setElement);
            dataFromFile.addRow(setElement);
        }

        return dataFromFile;
    }

    private List<Double[]> prepareLines() {

        List<Double[]> result = new ArrayList<>();

        for(String line : fileLines) {

            String[] splitted = line.split(",");
            Double[] input = new Double[]{
                    Double.parseDouble(splitted[1]),
                    Double.parseDouble(splitted[2]),
                    Double.parseDouble(splitted[3]),
                    Double.parseDouble(splitted[4]),
                    Double.parseDouble(splitted[5])
            };

            result.add(input);
        }

        return result;
    }
}
