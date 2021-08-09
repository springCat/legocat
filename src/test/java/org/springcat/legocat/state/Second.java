package org.springcat.legocat.state;

import cn.hutool.core.lang.Console;

/**
 * @Description First
 * @Author springCat
 * @Date 2021-7-29 15:08
 */
public class Second implements StateI<Msg> {

    @Override
    public Class<? extends StateI> invoke(Msg param) {
        Console.log("2");
        if(param.getEnd() == 3){
            return Third.class;
        }
        return Fourth.class;

    }
}
