package org.springcat.legocat.strategy;

import cn.hutool.core.lang.Console;
import cn.hutool.log.Log;
import org.junit.Test;

public class StrategyTest {

    @Test
    public void test() {
        StrategyFactory strategyFactory = StrategyFactory.create()
                .setPackageName("org.springcat.legocat.strategy")
                .setErrorHandler(e -> Log.get().error(e))
                .init();

        StrategyContext context = StrategyContext.create();
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