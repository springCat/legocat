package org.springcat.legocat.rule;

import org.springcat.legocat.rule.group.AllExecuteRule;

/**
 * @Description RedBlue
 * @Author springCat
 * @Date 2021-7-28 18:10
 */
@Rule(key = "redblue",strategies = {Red.class,Blue.class})
public class RedBlue extends AllExecuteRule {


}
