package org.springcat.legocat.strategy.group;

import org.springcat.legocat.strategy.BaseStrategyI;
import org.springcat.legocat.strategy.StrategyContext;

/**
 * @Description AllStrategy
 * @Author springCat
 * @Date 2021-7-28 14:54
 */
public class FastFailedStrategy extends GroupStrategyA {

    @Override
    public boolean invoke(StrategyContext context) {
        for (BaseStrategyI strategy : strategies) {
            if(!strategy.invoke(context)){
                return false;
            }
        }
        return true;
    }
}