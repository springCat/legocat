package org.springcat.legocat.common;
/**
 * @Description ErrorHandler
 * @Author springCat
 * @Date 2021-7-30 10:22
 */
public interface ConcurrentErrorHandler{

    void execute(Exception exception, ConcurrentContext context);

    default void execute(Exception exception){
        execute(exception,new ConcurrentContext());
    }
}
