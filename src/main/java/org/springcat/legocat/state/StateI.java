package org.springcat.legocat.state;

/**
 * @Description StateI
 * @Author springCat
 * @Date 2021-7-29 14:22
 */
public interface StateI<T> {

  Class<? extends StateI> invoke(T param);
}
