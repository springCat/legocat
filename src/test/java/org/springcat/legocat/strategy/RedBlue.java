package org.springcat.legocat.strategy;

import org.springcat.legocat.strategy.group.FastSucceedStrategy;

/**
 * @Description RedBlue
 * @Author springCat
 * @Date 2021-7-28 18:10
 */
@Strategy(key = "redblue",strategies = {Red.class,Blue.class})
public class RedBlue extends FastSucceedStrategy{


}
