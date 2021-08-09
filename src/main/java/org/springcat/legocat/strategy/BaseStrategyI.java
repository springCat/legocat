package org.springcat.legocat.strategy;

import cn.hutool.core.util.ObjectUtil;
import org.springcat.legocat.common.ConcurrentContext;
import org.springcat.legocat.common.ConcurrentErrorHandler;
import org.springcat.legocat.common.ErrorHandler;

/**
 * @Description Strategy
 * @Author springCat
 * @Date 2021-7-28 12:30
 */
public interface BaseStrategyI {

    BaseStrategyI[] getStrategies();

    boolean invoke(ConcurrentContext context);

    default boolean execute(ConcurrentContext context){
        try {
            return invoke(context);
        }catch (Exception e){
            if(ObjectUtil.isNotEmpty(context.getConcurrentErrorHandler())) {
                context.errorHandler(e, context);
            }
        }
        return false;
    }
}
