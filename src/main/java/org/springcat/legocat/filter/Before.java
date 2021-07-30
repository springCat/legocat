package org.springcat.legocat.filter;

import cn.hutool.core.lang.Dict;

/**
 * @Description Before
 * @Author springCat
 * @Date 2021-7-30 8:56
 */
public interface Before {

    boolean execute(Dict context);
}
