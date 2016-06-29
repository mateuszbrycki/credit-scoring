package com.fuzzy.rule;

import com.fuzzylite.Engine;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;

import java.util.ArrayList;
import java.util.List;

public class RuleGenerator {

    private static String[] steps = {
            "", "low", "medium", "high"
    };

    private static String[] results = {
            "rejected", "approve"
    };

    public static RuleBlock generate(Engine engine) {

        RuleBlock ruleBlock = new RuleBlock();
        List<String> rules = RuleGenerator.prepareRules();

        for(String rule : rules) {
            ruleBlock.addRule(Rule.parse(rule, engine));
        }

        return ruleBlock;
    }

    private static List<String> prepareRules() {
        List<String> result = new ArrayList<>();

        result.add(prepareString(3, 3, 3, 2, 0));
        result.add(prepareString(1, 3, 2, 3, 0));
        result.add(prepareString(3, 3, 3, 3, 1));
        result.add(prepareString(2, 2, 2, 3, 0));
        result.add(prepareString(3, 1, 3, 3, 1));
        result.add(prepareString(1, 3, 1, 1, 0));
        result.add(prepareString(2, 1, 2, 2, 0));
        result.add(prepareString(1, 1, 1, 2, 1));
        result.add(prepareString(2, 3, 2, 2, 0));
        result.add(prepareString(1, 2, 1, 1, 0));
        result.add(prepareString(3, 1, 2, 2, 0));
        result.add(prepareString(1, 1, 1, 3, 1));
        result.add(prepareString(1, 1, 2, 3, 1));

        return result;
    }

    private static String prepareString(Integer income, Integer age, Integer loan, Integer lti, Integer result) {
        return "if income is " + steps[income] +
                " and age is " + steps[age] +
                " and loan is " + steps[loan] +
                " and lti is " + steps[lti] +
                " then result is " + results[result];
    }
}
