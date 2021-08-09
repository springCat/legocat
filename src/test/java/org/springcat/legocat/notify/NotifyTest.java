package org.springcat.legocat.notify;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.log.Log;
import org.junit.Test;

/**
 * @Description NotifyTest
 * @Author springCat
 * @Date 2021-7-29 13:40
 */
public class NotifyTest {

    @Test
    public void test(){
        Notify notify = Notify.create()
                .register(Happy.class, happy ->{
                    Console.log("happy1:"+happy.getMsg());
                })
                .register(Happy.class, happy ->{
                    Console.log("happy2:"+happy.getMsg());
                })
                .register(Sad.class, sad ->{
                    Console.log("sad:"+sad.getMsg());
                })
                .setErrorHandler((e,context) -> {
                    Log.get().error(e);
                });

        notify.send(new Happy("msg"));
        notify.send(new Sad("msg"));

        Console.log("--------------");
        notify.sendAsync(new Happy("msg"));
    }
}
