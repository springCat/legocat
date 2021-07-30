package org.springcat.legocat.strategy;

import cn.hutool.core.lang.Console;
import cn.hutool.log.Log;
import org.junit.Test;

public class StrategyTest {

    @Test
    public void test() {
        StrategyFactory strategyFactory = new StrategyFactory();
        strategyFactory.setPackageName("org.springcat.legocat.strategy");
        strategyFactory.setErrorHandler(e -> Log.get().error(e));
        strategyFactory.init();

        StrategyContext context = new StrategyContext();
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