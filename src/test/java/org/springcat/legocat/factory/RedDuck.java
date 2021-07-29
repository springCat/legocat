package org.springcat.legocat.factory;

import cn.hutool.core.lang.Console;

/**
 * @Description YellowDuck
 * @Author springCat
 * @Date 2021-7-29 17:29
 */
public class RedDuck extends Duck {

    public RedDuck() {

    }

    public RedDuck(int sex) {
        this.sex = sex;
    }

    @Override
    public void bark() {
        Console.log("RedDuck:"+sex);
    }
}
