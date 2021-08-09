package org.springcat.legocat.rule.group;

import org.springcat.legocat.rule.BaseRuleA;
import org.springcat.legocat.rule.RuleI;
import org.springcat.legocat.rule.RuleContext;

/**
 * @Description AllExecuteRule
 * @Author springCat
 * @Date 2021-7-28 14:54
 */
public class AllExecuteRule extends BaseRuleA {

    @Override
    public boolean invoke(RuleContext context) {
        for (RuleI strategy : getStrategies()) {
            strategy.execute(context);
        }
        return true;
    }
}
