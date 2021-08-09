package org.springcat.legocat.rule;

import org.springcat.legocat.rule.group.AllExecuteRule;

/**
 * @Description RedBlue
 * @Author springCat
 * @Date 2021-7-28 18:10
 */
@Rule(key = "redbluered",strategies = {Red.class,Blue.class,RedBlue.class,ConcurrencyRedBlue.class})
public class RedBlueRed extends AllExecuteRule {


}
