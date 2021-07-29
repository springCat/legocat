package org.springcat.legocat.state;

import cn.hutool.core.lang.Singleton;

/**
 * @Description StateTransformer
 * @Author springCat
 * @Date 2021-7-29 14:22
 */
public class StateTransformer<T>{

    private Class<? extends State> start;

    public StateTransformer(Class<? extends State> start) {
        this.start = start;
    }

    public void start(T param){
        Class<? extends State> cls = start;
        while (cls != null){
            State<T> state = Singleton.get(cls);
            cls = state.invoke(param);
        }
    }

}
