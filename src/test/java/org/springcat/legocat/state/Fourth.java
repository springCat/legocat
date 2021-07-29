package org.springcat.legocat.state;

import cn.hutool.core.lang.Console;

/**
 * @Description Third
 * @Author springCat
 * @Date 2021-7-29 15:18
 */
public class Fourth implements State<Msg> {

    @Override
    public Class<? extends State> invoke(Msg param) {
        Console.log("4");
        return null;
    }
}
