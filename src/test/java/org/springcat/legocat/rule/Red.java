package org.springcat.legocat.rule;

import cn.hutool.core.lang.Console;

/**
 * @Description Red
 * @Author springCat
 * @Date 2021-7-28 16:12
 */
@Rule(key = "red")
public class Red extends BaseRuleA  {

    @Override
    public boolean invoke(RuleContext context) {
        Console.log("red:"+context.get("name"));
        return true;
    }
}
