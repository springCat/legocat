package org.springcat.legocat.notify;

import cn.hutool.core.lang.Console;

/**
 * @Description Happy
 * @Author springCat
 * @Date 2021-7-29 13:39
 */
public class Happy1Consumer extends ConsumerA<Happy> {

    @Override
    public void invoke(Happy message) {
        Console.log("happy1:"+message.getMsg());
    }
}
