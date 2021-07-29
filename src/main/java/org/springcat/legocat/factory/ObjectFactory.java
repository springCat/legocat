package org.springcat.legocat.factory;

import cn.hutool.core.lang.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description ObjectFactory
 * @Author springCat
 * @Date 2021-7-29 17:01
 */
public class ObjectFactory<T> {

    private Map<String, T> map = new HashMap<>();

    public T register(String type,T obj){
        map.put(type, obj);
        return obj;
    }

    public T register(String type,Class<? extends T> cls, Object... params){
        T obj = Singleton.get(cls, params);
        return register(type,obj);
    }

    public T get(String type){
        return map.get(type);
    }
}
