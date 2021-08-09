package org.springcat.legocat.state;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import org.junit.Test;
import org.springcat.legocat.common.ErrorHandler;


/**
 * @Description StateTest
 * @Author springCat
 * @Date 2021-7-29 15:20
 */
public class StateTest {

    @Test
    public void test(){
        StateTransformer<Msg> stateTransformer = new StateTransformer<Msg>(First.class);
        stateTransformer.setErrorHandler((error, context) -> Console.log(error));
        Msg msg = new Msg();
        msg.setEnd(3);
        stateTransformer.start(msg);
    }
}
