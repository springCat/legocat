package org.springcat.legocat.strategy.assemble;

import org.springcat.legocat.strategy.BaseStrategyI;

/**
 * @Description AssembleStrategyA
 * @Author springCat
 * @Date 2021-7-28 14:48
 */
public abstract class AssembleStrategyA implements BaseStrategyI {

    protected BaseStrategyI[] strategies;

    public BaseStrategyI[] getStrategies() {
        return strategies;
    }

    public void setStrategies(BaseStrategyI[] strategies) {
        this.strategies = strategies;
    }
}
