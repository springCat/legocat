package org.springcat.legocat.strategy;

import cn.hutool.core.util.ObjectUtil;

/**
 * @Description Strategy
 * @Author springCat
 * @Date 2021-7-28 12:30
 */
public abstract class BaseStrategyA {

    private ErrorHandler errorHandler;

    public void errorHandler(Exception exception){
        if(ObjectUtil.isNotEmpty(errorHandler)) {
            errorHandler.invoke(exception);
        }
    }

    public boolean execute(StrategyContext context){
        try {
            return invoke(context);
        }catch (Exception e){
            errorHandler.invoke(e);
        }
        return false;
    }

    public abstract boolean invoke(StrategyContext context);
}
