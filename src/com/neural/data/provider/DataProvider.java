package com.neural.data.provider;


import org.neuroph.core.data.DataSet;

public interface DataProvider {

    DataSet getDataFromFile() throws Exception;
}
