package org.springcat.legocat.notify;

import cn.hutool.core.lang.Console;

/**
 * @Description Happy
 * @Author springCat
 * @Date 2021-7-29 13:39
 */
public class Happy2Consumer implements Consumer<Happy>{

    @Override
    public void invoke(Happy message) {
        Console.log("happy2:"+message.getMsg());
    }
}