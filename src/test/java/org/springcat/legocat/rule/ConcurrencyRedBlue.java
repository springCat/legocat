package org.springcat.legocat.rule;

import org.springcat.legocat.rule.group.ConcurrencyRule;

/**
 * @Description ConcurrencyRedBlue
 * @Author springCat
 * @Date 2021-7-29 9:01
 */
@Rule(key = "credbluered",strategies = {Red.class,Blue.class,RedBlue.class})
public class ConcurrencyRedBlue extends ConcurrencyRule {
}
