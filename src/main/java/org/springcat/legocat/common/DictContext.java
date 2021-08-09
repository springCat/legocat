package org.springcat.legocat.common;

import cn.hutool.core.lang.Dict;

/**
 * @Description DictContext
 * @Author springCat
 * @Date 2021-7-30 17:48
 */

public class DictContext<T> extends Dict{

    private T result;

    private boolean isFinish = false;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void finish() {
        isFinish = true;
    }
}
