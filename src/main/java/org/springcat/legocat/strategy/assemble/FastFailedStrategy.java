package org.springcat.legocat.strategy.assemble;

import cn.hutool.core.lang.Dict;
import org.springcat.legocat.strategy.BaseStrategyI;

/**
 * @Description AllStrategy
 * @Author springCat
 * @Date 2021-7-28 14:54
 */
public class FastFailedStrategy extends AssembleStrategyA{

    @Override
    public boolean invoke(Dict context) {
        for (BaseStrategyI strategy : strategies) {
            if(!strategy.invoke(context)){
                return false;
            }
        }
        return true;
    }
}