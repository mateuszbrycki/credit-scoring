package com.fuzzy.rule;

import com.fuzzylite.Engine;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;

/**
 * Created by Mateusz on 29.06.2016.
 */
public class RuleGenerator {

    public static RuleBlock generate(Engine engine) {

        RuleBlock ruleBlock = new RuleBlock();
        ruleBlock.addRule(Rule.parse(
                "if dochod is wysoki and dochodDodatkowy " + "is sredni and koszta is sredni and iloscOsob is niski "
                        + "and raty is sredni then zgoda is zatwierdzono",
                engine)); // 1

        return ruleBlock;
    }
}
