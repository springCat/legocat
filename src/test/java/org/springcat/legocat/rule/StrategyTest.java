package org.springcat.legocat.rule;

import cn.hutool.core.lang.Console;
import org.junit.Test;

public class StrategyTest {

    @Test
    public void test() {
        RuleFactory strategyFactory = RuleFactory.create()
                .setPackageName("org.springcat.legocat.rule")
                .init();

        RuleContext context = new RuleContext();
        context.put("name", "xiaoming");


        strategyFactory.execute("red",context);
        Console.log("--------------");

        strategyFactory.execute("redblue",context);
        Console.log("--------------");

        strategyFactory.execute("credbluered",context);
        Console.log("--------------");

    }
    
}