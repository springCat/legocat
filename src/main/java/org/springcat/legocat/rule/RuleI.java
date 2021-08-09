package org.springcat.legocat.rule;

/**
 * @Description Rule
 * @Author springCat
 * @Date 2021-7-28 12:30
 */
public interface RuleI {

    default RuleI[] getStrategies(){
        return null;
    }

    boolean invoke(RuleContext context);

    boolean execute(RuleContext context);
}
