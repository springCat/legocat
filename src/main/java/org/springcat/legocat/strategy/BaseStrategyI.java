package org.springcat.legocat.strategy;

import cn.hutool.core.lang.Dict;

/**
 * @Description Strategy
 * @Author springCat
 * @Date 2021-7-28 12:30
 */
public interface BaseStrategyI {
    boolean invoke(Dict context);
}
