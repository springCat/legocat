package org.springcat.legocat.rule;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.Log;
import org.springcat.legocat.common.ConcurrentContext;
import org.springcat.legocat.common.ConcurrentErrorHandler;

/**
 * @Description BaseRuleA
 * @Author springCat
 * @Date 2021-7-28 14:48
 */
public abstract class BaseRuleA implements RuleI {


    protected RuleI[] strategies;

    protected ConcurrentErrorHandler concurrentErrorHandler;

    public ConcurrentErrorHandler getConcurrentErrorHandler() {
        return concurrentErrorHandler;
    }

    public void setConcurrentErrorHandler(ConcurrentErrorHandler concurrentErrorHandler) {
        this.concurrentErrorHandler = concurrentErrorHandler;
    }

    public RuleI[] getStrategies() {
        return strategies;
    }

    public void setStrategies(RuleI[] strategies) {
        this.strategies = strategies;
    }


    public boolean execute(ConcurrentContext context){
        try {
            return invoke(context);
        }catch (Exception e){
            if(ObjectUtil.isNotEmpty(getConcurrentErrorHandler())) {
                getConcurrentErrorHandler().execute(e, context);
            }
        }
        return false;
    }
}
