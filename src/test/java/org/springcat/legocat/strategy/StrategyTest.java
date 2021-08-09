package org.springcat.legocat.strategy;

import cn.hutool.core.lang.Console;
import cn.hutool.log.Log;
import org.junit.Test;
import org.springcat.legocat.common.ConcurrentContext;

public class StrategyTest {

    @Test
    public void test() {
        StrategyFactory strategyFactory = StrategyFactory.create()
                .setPackageName("org.springcat.legocat.strategy")
                .init();

        ConcurrentContext context = new ConcurrentContext();
        context.put("name", "xiaoming");


        strategyFactory.execute("red",context);
        Console.log("--------------");

        strategyFactory.execute("redblue",context);
        Console.log("--------------");

        strategyFactory.execute("redbluered",context);
        Console.log("--------------");

        strategyFactory.executeAsync("redbluered",context);
        Console.log("--------------");
    }
    
}