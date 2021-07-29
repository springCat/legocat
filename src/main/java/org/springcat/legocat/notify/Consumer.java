package org.springcat.legocat.notify;

/**
 * @Description Message
 * @Author springCat
 * @Date 2021-7-29 12:49
 */
public interface Consumer<T> {

    void invoke(T message);
}
