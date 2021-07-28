package org.springcat.legocat.strategy;

import org.springcat.legocat.strategy.assemble.AllStrategy;

/**
 * @Description RedBlue
 * @Author springCat
 * @Date 2021-7-28 18:10
 */
@Strategy(key = "redbluered",strategies = {Red.class,Blue.class,RedBlue.class})
public class RedBlueRed extends AllStrategy{


}
