package org.springcat.legocat.invoker;

import cn.hutool.core.lang.Dict;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * @Description Context
 * @Author springCat
 * @Date 2021-7-30 17:48
 */

public class Context<T> extends Dict {

    private T result;

    private boolean isFinish = false;

    private BiConsumer<Exception, Context<T>> errorHandler;

    private BiFunction<Exception, Context<T>,T> recover;

    public void setErrorHandler(BiConsumer<Exception,Context<T>> errorHandler){
        this.errorHandler = errorHandler;
    }

    public void setRecover(BiFunction<Exception, Context<T>,T> recover){
        this.recover = recover;
    }

    public void errorHandler(Exception exception,Context<T> context){
        if(errorHandler != null){
            errorHandler.accept(exception,context);
        }
    }

    public T recover(Exception exception, Context<T> context){
        if(recover != null){
            return recover.apply(exception,context);
        }
        return null;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void finish() {
        isFinish = true;
    }
}
