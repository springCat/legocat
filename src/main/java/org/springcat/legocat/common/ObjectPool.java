package org.springcat.legocat.common;


import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description ObjectPool
 * @Author springCat
 * @Date 2021-8-9 17:00
 */
public class ObjectPool<K,V> {

    private Map<K,V> map = new ConcurrentHashMap<>();

    public static <T> T create(){
        ObjectPool objectPool = new ObjectPool<>();
        return (T) objectPool;
    }

    public ObjectPool<K,V> put(Class<? extends V> clazz, Object... params){
        V value = ReflectUtil.newInstance(clazz,params);
        String className = StrUtil.lowerFirst(ClassUtil.getClassName(clazz, true));
        map.put((K) className,value);
        return this;
    }

    public ObjectPool<K,V> put(K key, Class<? extends V> clazz, Object... params){
        V value = ReflectUtil.newInstance(clazz,params);
        map.put(key,value);
        return this;
    }

    public ObjectPool<K,V> put(K key, V value){
        map.put(key,value);
        return this;
    }

    public Optional<V> get(K key){
        return Optional.ofNullable(map.get(key));
    }

}
