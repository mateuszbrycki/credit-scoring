package com.neural.data.provider;

import org.neuroph.core.learning.DataSet;

public interface DataProvider {

    DataSet getDataFromFile() throws Exception;
}
