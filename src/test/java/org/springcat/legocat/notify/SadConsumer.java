package org.springcat.legocat.notify;

import cn.hutool.core.lang.Console;

/**
 * @Description SadConsumer
 * @Author springCat
 * @Date 2021-7-29 13:39
 */
public class SadConsumer implements Consumer<Sad> {

    @Override
    public void invoke(Sad message) {
        Console.log("sad:"+message.getMsg());
    }
}
