package com.fuzzy;


import com.fuzzy.rule.RuleGenerator;
import com.fuzzylite.Engine;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;
import com.fuzzylite.term.Gaussian;
import com.fuzzylite.term.Triangle;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;
import com.neural.data.Entry;
import org.springframework.stereotype.Component;

@Component
public class FuzzyEngine {

    private InputVariable income;
    private InputVariable age;
    private InputVariable loan;
    private InputVariable lti;
    private Engine engine;


    public FuzzyEngine() {
        engine = new Engine();
        engine.setName("credit-scoring");

        this.income = new InputVariable();
        income.setName("income");
        income.setRange(0.0, 10000.0);
        income.addTerm(new Gaussian("low", 0.0, 1500.0, 1.0));
        income.addTerm(new Gaussian("medium", 5000.0, 1500.0, 1.0));
        income.addTerm(new Gaussian("high", 10000.0, 1500.0, 1.0));
        engine.addInputVariable(income);

        this.age = new InputVariable();
        age.setName("age");
        age.setRange(0.0, 1000.0);
        age.addTerm(new Gaussian("low", 0.0, 150.0, 1.0));
        age.addTerm(new Gaussian("medium", 500.0, 150.0, 1.0));
        age.addTerm(new Gaussian("high", 1000.0, 150.0, 1.0));
        engine.addInputVariable(age);

        this.loan = new InputVariable();
        loan.setName("loan");
        loan.setRange(0.0, 1000.0);
        loan.addTerm(new Gaussian("low", 0.0, 150.0, 1.0));
        loan.addTerm(new Gaussian("medium", 500.0, 150.0, 1.0));
        loan.addTerm(new Gaussian("high", 1000.0, 150.0, 1.0));
        engine.addInputVariable(loan);

        this.lti = new InputVariable();
        lti.setName("lti");
        lti.setRange(0.0, 6.0);
        lti.addTerm(new Gaussian("low", 0.0, 1.0, 1.0));
        lti.addTerm(new Gaussian("medium", 3.0, 1.0, 1.0));
        lti.addTerm(new Gaussian("high", 6.0, 1.0, 1.0));
        engine.addInputVariable(lti);

        engine.addRuleBlock(RuleGenerator.generate(engine));
        engine.configure("Minimum", "Maximum", "Minimum", "Maximum", "Centroid");

        StringBuilder status = new StringBuilder();
        if (!engine.isReady(status)) {
            throw new RuntimeException(
                    "Engine not ready. " + "The following errors were encountered:\n" + status.toString());
        }
    }

    public String checkSolution(Entry entry) {

        OutputVariable result = new OutputVariable();
        result.setName("result");
        result.setRange(0.0, 1.0);
        result.addTerm(new Triangle("rejected", 0.0, 0.25, 0.5));
        result.addTerm(new Triangle("approve", 0.5, 0.75, 1.0));
        engine.addOutputVariable(result);

        income.setInputValue(entry.getIncome());
        age.setInputValue(entry.getAge());
        loan.setInputValue(entry.getLoan());
        lti.setInputValue(entry.getLti());

        engine.process();

        return entry.toString() + ", result: " + result.getOutputValue();
    }
}
