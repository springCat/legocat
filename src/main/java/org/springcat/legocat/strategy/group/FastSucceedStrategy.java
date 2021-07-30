package org.springcat.legocat.strategy.group;

import org.springcat.legocat.strategy.BaseStrategyA;
import org.springcat.legocat.strategy.StrategyContext;

/**
 * @Description AllStrategy
 * @Author springCat
 * @Date 2021-7-28 14:54
 */
public class FastSucceedStrategy extends GroupStrategyA {

    @Override
    public boolean invoke(StrategyContext context) {
        for (BaseStrategyA strategy : strategies) {
            if(strategy.invoke(context)){
                return true;
            }
        }
        return false;
    }
}
