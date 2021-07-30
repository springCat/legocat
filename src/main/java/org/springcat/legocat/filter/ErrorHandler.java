package org.springcat.legocat.filter;

/**
 * @Description ErrorHandler
 * @Author springCat
 * @Date 2021-7-30 10:22
 */
public interface ErrorHandler {

    void invoke(Exception exception);
}
