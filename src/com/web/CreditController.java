package com.web;

import com.fuzzy.FuzzyEngine;
import com.neural.data.Entry;
import com.neural.layer.NeuralEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credit")
public class CreditController {

    @Autowired
    private NeuralEngine multiLayerBackPropagation;

    @Autowired
    private FuzzyEngine fuzzyEngine;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Response<Double> creditScoring(@RequestBody Entry entry) {

        Double neuralResponse = multiLayerBackPropagation.getRespond(entry);
        Double fuzzyResponse = fuzzyEngine.getSolution(entry);

        return new Response<>(neuralResponse, fuzzyResponse);
    }
}
