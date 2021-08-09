package org.springcat.legocat.common;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description DictContext
 * @Author springCat
 * @Date 2021-7-30 17:48
 */

public class ConcurrentContext<T> extends ConcurrentHashMap<String,Object> {

    private T result;

    private boolean isFinish = false;

    private ConcurrentErrorHandler<T> concurrentErrorHandler;

    public void setConcurrentErrorHandler(ConcurrentErrorHandler<T> concurrentErrorHandler){
        this.concurrentErrorHandler = concurrentErrorHandler;
    }

    public ConcurrentErrorHandler<T> getConcurrentErrorHandler() {
        return concurrentErrorHandler;
    }

    public void errorHandler(Exception exception, ConcurrentContext<T> context){
        if(concurrentErrorHandler != null){
            concurrentErrorHandler.execute(exception,context);
        }
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
