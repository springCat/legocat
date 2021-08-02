package org.springcat.legocat.notify;

import org.springcat.legocat.state.StateA;
import org.springcat.legocat.strategy.ErrorHandler;

/**
 * @Description Message
 * @Author springCat
 * @Date 2021-7-29 12:49
 */
public abstract class ConsumerA<T> {

    private ErrorHandler errorHandler;

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public abstract void invoke(T message);

    public void execute(T message){
        try {
            invoke(message);
        }catch (Exception e){
            errorHandler.invoke(e);
        }
    }

}
