package org.springcat.legocat.state;

import org.junit.Test;

/**
 * @Description StateTest
 * @Author springCat
 * @Date 2021-7-29 15:20
 */
public class StateTest {

    @Test
    public void test(){
        StateTransformer<Msg> stateTransformer = new StateTransformer<Msg>(First.class);
        Msg msg = new Msg();
        msg.setEnd(3);
        stateTransformer.start(msg);
    }
}
