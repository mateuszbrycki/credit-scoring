package com.neural;

import com.neural.layer.MultiLayerBackPropagation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeuralApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(NeuralApplication.class, args);
		MultiLayerBackPropagation m = new MultiLayerBackPropagation();
	}
}
