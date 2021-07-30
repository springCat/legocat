package org.springcat.legocat.strategy.group;

import org.springcat.legocat.strategy.BaseStrategyA;

/**
 * @Description GroupStrategyA
 * @Author springCat
 * @Date 2021-7-28 14:48
 */
public abstract class GroupStrategyA extends BaseStrategyA {

    protected BaseStrategyA[] strategies;

    public BaseStrategyA[] getStrategies() {
        return strategies;
    }

    public void setStrategies(BaseStrategyA[] strategies) {
        this.strategies = strategies;
    }
}
