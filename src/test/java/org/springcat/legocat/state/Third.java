package org.springcat.legocat.state;

import cn.hutool.core.lang.Console;

/**
 * @Description Third
 * @Author springCat
 * @Date 2021-7-29 15:18
 */
public class Third implements State<Msg> {

    @Override
    public Class<? extends State> invoke(Msg param) {
        Console.log("3");
        return null;
    }
}
