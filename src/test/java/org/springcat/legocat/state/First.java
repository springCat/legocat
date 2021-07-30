package org.springcat.legocat.state;

import cn.hutool.core.lang.Console;

/**
 * @Description First
 * @Author springCat
 * @Date 2021-7-29 15:08
 */
public class First extends StateA<Msg> {

    @Override
    public Class<? extends StateA> invoke(Msg param) {
        Console.log("1");
        return Second.class;
    }
}
