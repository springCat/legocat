package org.springcat.legocat.notify;

import org.junit.Test;

/**
 * @Description NotifyTest
 * @Author springCat
 * @Date 2021-7-29 13:40
 */
public class NotifyTest {

    @Test
    public void test(){
        Notify.register(Happy.class,new Happy1Consumer());
        Notify.register(Happy.class,new Happy2Consumer());
        Notify.register(Sad.class,new SadConsumer());

        Notify.send(new Happy("msg"));
        Notify.send(new Sad("msg"));
    }
}
