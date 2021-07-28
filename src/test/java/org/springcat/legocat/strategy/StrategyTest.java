package org.springcat.legocat.strategy;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Dict;
import org.junit.Test;

public class StrategyTest {

    @Test
    public void test() {
        StrategyFactory strategyFactory = new StrategyFactory();
        strategyFactory.setPackageName("org.springcat.legocat.strategy");
        strategyFactory.init();

        Dict context = Dict.create().set("name", "xiaoming");

        strategyFactory.execute("red",context);
        Console.log("--------------");
        strategyFactory.execute("redblue",context);
        Console.log("--------------");
        strategyFactory.execute("redbluered",context);
        Console.log("--------------");
    }
    
}