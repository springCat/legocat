package org.springcat.legocat.strategy;

/**
 * @Description Strategy
 * @Author springCat
 * @Date 2021-7-28 12:30
 */
public interface BaseStrategyI {
    boolean invoke(StrategyContext context);
}
