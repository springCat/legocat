package org.springcat.legocat.strategy;

import org.springcat.legocat.strategy.group.ConcurrencyStrategy;

/**
 * @Description ConcurrencyRedBlue
 * @Author springCat
 * @Date 2021-7-29 9:01
 */
@Strategy(key = "credbluered",strategies = {Red.class,Blue.class,RedBlue.class})
public class ConcurrencyRedBlue extends ConcurrencyStrategy {
}
