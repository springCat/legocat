package org.springcat.legocat.rule;

import cn.hutool.core.lang.Console;

/**
 * @Description Red
 * @Author springCat
 * @Date 2021-7-28 16:12
 */
@Rule(key = "blue")
public class Blue extends BaseRuleA {

    @Override
    public boolean invoke(RuleContext context) {
        Console.log("blue:"+context.get("name"));
        throw new RuntimeException("exception test");
    }
}
