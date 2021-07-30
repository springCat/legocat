package org.springcat.legocat.state;

import cn.hutool.core.lang.Singleton;
import org.springcat.legocat.strategy.ErrorHandler;

/**
 * @Description StateTransformer
 * @Author springCat
 * @Date 2021-7-29 14:22
 */
public class StateTransformer<T>{

    private ErrorHandler errorHandler;

    private Class<? extends StateA> startState;

    public StateTransformer(Class<? extends StateA> startState) {
        this.startState = startState;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void start(T param){
        Class<? extends StateA> cls = startState;
        while (cls != null){
            StateA<T> state = Singleton.get(cls);
            cls = state.execute(param);
        }
    }

}
