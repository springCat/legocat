package org.springcat.legocat.state;

import cn.hutool.core.lang.Console;
import org.junit.Test;
/**
 * @Description StateTest
 * @Author springCat
 * @Date 2021-7-29 15:20
 */
public class StateTest {

    @Test
    public void test(){
        StateTransformer<Msg> stateTransformer = StateTransformer.create();
        stateTransformer.setErrorHandler((error, context) -> Console.log(error));

        Msg msg = new Msg();
        msg.setEnd(3);
        stateTransformer.start(First.class,msg);
    }
}
