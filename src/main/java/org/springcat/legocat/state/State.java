package org.springcat.legocat.state;

/**
 * @Description State
 * @Author springCat
 * @Date 2021-7-29 14:22
 */
public interface State<T> {

    Class<? extends State> invoke(T param);

}
