package org.springcat.legocat.notify;

import cn.hutool.core.map.multi.CollectionValueMap;
import cn.hutool.core.map.multi.SetValueMap;
import cn.hutool.core.util.ObjectUtil;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

/**
 * @Description Notify
 * @Author springCat
 * @Date 2021-7-29 11:03
 */
public class Notify{

    private static CollectionValueMap<Class<?>, Consumer> tableMap = new SetValueMap();

    private static ExecutorService WORKER_POOL =  ForkJoinPool.commonPool();

    public static void setExecutePool(ExecutorService pool) {
        WORKER_POOL = pool;
    }

    public static void register(Class<?> messageType, Consumer consumer){
        tableMap.putValue(messageType,consumer);
    }

    public static void send(Object message){
        Collection<Consumer> consumers = tableMap.get(message.getClass());
        if(ObjectUtil.isEmpty(consumers)){
            return;
        }
        for (Consumer consumer : consumers) {
            consumer.invoke(message);
        }
    }

    public static void sendAsync(Object message){
        Collection<Consumer> consumers = tableMap.get(message.getClass());
        if(ObjectUtil.isEmpty(consumers)){
            return;
        }
        for (Consumer consumer : consumers) {
            WORKER_POOL.execute(() -> consumer.invoke(message));;
        }
    }


}
