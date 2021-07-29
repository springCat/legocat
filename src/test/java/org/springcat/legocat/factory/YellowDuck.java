package org.springcat.legocat.factory;

import cn.hutool.core.lang.Console;

/**
 * @Description YellowDuck
 * @Author springCat
 * @Date 2021-7-29 17:29
 */
public class YellowDuck extends Duck {

    public YellowDuck() {

    }
    public YellowDuck(int sex) {
        this.sex = sex;
    }

    @Override
    public void bark() {
        Console.log("YellowDuck"+sex);
    }
}
