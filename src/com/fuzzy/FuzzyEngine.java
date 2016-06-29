package com.fuzzy;


import com.fuzzy.rule.RuleGenerator;
import com.fuzzylite.Engine;
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
    private OutputVariable result;

    private Engine engine;

    public FuzzyEngine() {
        engine = new Engine();
        engine.setName("credit-scoring");

        this.income = new InputVariable();
        income.setName("income");
        income.setRange(20542.3650727607, 68427.163111046);
        income.addTerm(new Triangle("low", 20542.3650727607, 28504.58, 36504.));
        income.addTerm(new Triangle("medium", 36504., 42465.6, 52465.6));
        income.addTerm(new Triangle("high", 52465.6, 60562.587, 68427.163111046));
        engine.addInputVariable(income);

        this.age = new InputVariable();
        age.setName("age");
        age.setRange(18., 63.);
        age.addTerm(new Triangle("low", 18., 24., 32.));
        age.addTerm(new Triangle("medium", 32., 39., 47.));
        age.addTerm(new Triangle("high", 47., 57., 63.));
        engine.addInputVariable(age);

        this.loan = new InputVariable();
        loan.setName("loan");
        loan.setRange(15.5, 10871.2);
        loan.addTerm(new Triangle("low", 15.5, 24., 3633.56));
        loan.addTerm(new Triangle("medium", 3633.56, 3642.6, 7252.65));
        loan.addTerm(new Triangle("high", 7252.6, 9487.57, 10871.6));
        engine.addInputVariable(loan);

        this.lti = new InputVariable();
        lti.setName("lti");
        lti.setRange(6.22332096099854E-4, 0.190751580661163);
        lti.addTerm(new Triangle("low", 6.22332096099854E-4, 0.0156589879, 0.06399868));
        lti.addTerm(new Triangle("medium",  0.06399868, 0.10024578, 0.12737509));
        lti.addTerm(new Triangle("high", 0.12737509, 0.1576895, 0.190751580661163));
        engine.addInputVariable(lti);

        result = new OutputVariable();
        result.setName("result");
        result.setRange(0., 1.0);
        result.addTerm(new Triangle("rejected", 0, 0.25, 0.5));
        result.addTerm(new Triangle("approve", 0.5, 0.75, 1.0));
        engine.addOutputVariable(result);

        engine.addRuleBlock(RuleGenerator.generate(engine));
        engine.configure("Minimum", "Maximum", "Minimum", "Maximum", "Centroid");

        StringBuilder status = new StringBuilder();
        if (!engine.isReady(status)) {
            throw new RuntimeException(
                    "Engine not ready. " + "The following errors were encountered:\n" + status.toString());
        }
    }

    public String checkSolution(Entry entry) {

        income.setInputValue(entry.getIncome());
        age.setInputValue(entry.getAge());
        loan.setInputValue(entry.getLoan());
        lti.setInputValue(entry.getLti());

        engine.process();

        return entry.toString() + ", result: " + result.getOutputValue();
    }
}
