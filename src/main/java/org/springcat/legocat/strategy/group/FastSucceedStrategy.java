package org.springcat.legocat.strategy.group;

import org.springcat.legocat.strategy.BaseStrategyI;
import org.springcat.legocat.common.ConcurrentContext;

/**
 * @Description AllStrategy
 * @Author springCat
 * @Date 2021-7-28 14:54
 */
public class FastSucceedStrategy extends GroupStrategyA {

    @Override
    public boolean invoke(ConcurrentContext context) {
        for (BaseStrategyI strategy : getStrategies()) {
            if(strategy.execute(context)){
                return true;
            }
        }
        return false;
    }
}
