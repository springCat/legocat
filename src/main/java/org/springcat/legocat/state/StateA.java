package org.springcat.legocat.state;

import cn.hutool.core.util.ObjectUtil;
import org.springcat.legocat.strategy.ErrorHandler;

/**
 * @Description StateA
 * @Author springCat
 * @Date 2021-7-29 14:22
 */
public abstract class StateA<T> {

    private ErrorHandler errorHandler;

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void errorHandler(Exception exception){
        if(ObjectUtil.isNotEmpty(errorHandler)) {
            errorHandler.invoke(exception);
        }
    }

    public Class<? extends StateA> execute(T param){
        try {
            return invoke(param);
        }catch (Exception e){
            errorHandler.invoke(e);
        }
        return null;
    }

    public abstract Class<? extends StateA> invoke(T param);

}
