package org.springcat.legocat.state;

import cn.hutool.core.lang.Console;

/**
 * @Description Third
 * @Author springCat
 * @Date 2021-7-29 15:18
 */
public class Fourth extends StateA<Msg> {

    @Override
    public Class<? extends StateA> invoke(Msg param) {
        Console.log("4");
        return null;
    }
}
