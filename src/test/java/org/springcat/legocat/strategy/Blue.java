package org.springcat.legocat.strategy;

import cn.hutool.core.lang.Console;
import org.springcat.legocat.common.ConcurrentContext;
import org.springcat.legocat.strategy.atomic.AtomicStrategyI;

/**
 * @Description Red
 * @Author springCat
 * @Date 2021-7-28 16:12
 */
@Strategy(key = "blue")
public class Blue extends AtomicStrategyI {

    @Override
    public boolean invoke(ConcurrentContext context) {
        Console.log("blue:"+context.get("name"));

        throw new RuntimeException("exception test");
//        return true;
    }
}
