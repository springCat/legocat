package org.springcat.legocat.state;

import cn.hutool.core.lang.Singleton;
import org.springcat.legocat.common.ErrorHandler;
import org.springcat.legocat.invoker.Invoker;

/**
 * @Description StateTransformer
 * @Author springCat
 * @Date 2021-7-29 14:22
 */
public class StateTransformer<T>{

    private ErrorHandler<T> errorHandler;

    public ErrorHandler<T> getErrorHandler() {
        return errorHandler;
    }

    public StateTransformer<T> setErrorHandler(ErrorHandler<T> errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    public static <T> StateTransformer<T> create(){
        return new StateTransformer<T>();
    }

    public void start(Class<? extends StateI> startState,T param){
        Class<? extends StateI> cls = startState;

        try {
            while (cls != null){
                StateI<T> state = Singleton.get(cls);
                cls = state.invoke(param);
            }
        }catch (Exception e){
            getErrorHandler().execute(e);
        }

    }

}
