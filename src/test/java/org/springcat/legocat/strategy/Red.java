package org.springcat.legocat.strategy;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import org.springcat.legocat.strategy.single.AtomicStrategyA;

/**
 * @Description Red
 * @Author springCat
 * @Date 2021-7-28 16:12
 */
@Strategy(key = "red")
public class Red extends AtomicStrategyA {

    @Override
    public boolean invoke(Dict context) {
        Console.log("red:"+context.getStr("name"));
        return true;
    }
}
