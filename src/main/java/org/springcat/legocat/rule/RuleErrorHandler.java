package org.springcat.legocat.rule;
/**
 * @Description ErrorHandler
 * @Author springCat
 * @Date 2021-7-30 10:22
 */
public interface RuleErrorHandler {

    void execute(Exception exception, RuleContext context);

    default void execute(Exception exception){
        execute(exception,new RuleContext());
    }
}
