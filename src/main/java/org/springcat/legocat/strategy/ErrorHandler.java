package org.springcat.legocat.strategy;

/**
 * @Description ErrorHandler
 * @Author springCat
 * @Date 2021-7-30 10:22
 */
public interface ErrorHandler {

    void invoke(Exception exception);
}
