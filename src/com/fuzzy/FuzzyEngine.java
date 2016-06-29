package com.fuzzy;


import com.fuzzy.rule.RuleGenerator;
import com.fuzzylite.Engine;
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
    private OutputVariable result;

    private Engine engine;

    public FuzzyEngine() {
        engine = new Engine();
        engine.setName("credit-scoring");

        this.income = new InputVariable();
        income.setName("income");
        income.setRange(20542.3650727607, 68427.163111046);
        income.addTerm(new Gaussian("low", 20542.3650727607, 11971.199509571325, 1.0));
        income.addTerm(new Gaussian("medium", 44484.76409190335, 11971.199509571325, 1.0));
        income.addTerm(new Gaussian("high", 68427.163111046, 11971.199509571325, 1.0));
        engine.addInputVariable(income);

        this.age = new InputVariable();
        age.setName("age");
        age.setRange(18.1300383633934, 63.108049491886);
        age.addTerm(new Gaussian("low", 18.1300383633934, 11.24450278212315, 1.0));
        age.addTerm(new Gaussian("medium", 40.6190439276397, 11.24450278212315, 1.0));
        age.addTerm(new Gaussian("high", 63.108049491886, 11.24450278212315, 1.0));
        engine.addInputVariable(age);

        this.loan = new InputVariable();
        loan.setName("loan");
        loan.setRange(15.4985984378272, 10871.1867897838);
        loan.addTerm(new Gaussian("low", 15.4985984378272, 2713.9220478364932, 1.0));
        loan.addTerm(new Gaussian("medium", 5443.3426941108136, 2713.9220478364932, 1.0));
        loan.addTerm(new Gaussian("high", 10871.1867897838, 2713.9220478364932, 1.0));
        engine.addInputVariable(loan);

        this.lti = new InputVariable();
        lti.setName("lti");
        lti.setRange(6.22332096099854E-4, 0.190751580661163);
        lti.addTerm(new Gaussian("low", 6.22332096099854E-4, 0.0475323121412657865, 1.0));
        lti.addTerm(new Gaussian("medium",  0.095686956378631427, 0.0475323121412657865, 1.0));
        lti.addTerm(new Gaussian("high", 0.190751580661163, 0.0475323121412657865, 1.0));
        engine.addInputVariable(lti);

        result = new OutputVariable();
        result.setName("result");
        result.setRange(0., 1.0);
        result.addTerm(new Triangle("rejected", 0, 0.25, 0.499999999));
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
