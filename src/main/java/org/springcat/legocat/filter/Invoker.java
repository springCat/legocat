package org.springcat.legocat.filter;

import cn.hutool.core.lang.Dict;

/**
 * @Description Invoker
 * @Author springCat
 * @Date 2021-7-30 9:15
 */
public interface Invoker<T> {

    T execute(Dict context);

}
