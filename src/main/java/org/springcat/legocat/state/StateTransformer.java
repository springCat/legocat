package org.springcat.legocat.state;

import cn.hutool.core.lang.Singleton;
import org.springcat.legocat.common.ErrorHandler;

/**
 * @Description StateTransformer
 * @Author springCat
 * @Date 2021-7-29 14:22
 */
public class StateTransformer<T>{

    private ErrorHandler errorHandler;

    private Class<? extends StateI> startState;

    public StateTransformer(Class<? extends StateI> startState) {
        this.startState = startState;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void start(T param){
        Class<? extends StateI> cls = startState;

        try {
            while (cls != null){
                StateI<T> state = Singleton.get(cls);
                cls = state.invoke(param);
            }
        }catch (Exception e){
            errorHandler.execute(e);
        }

    }

}
