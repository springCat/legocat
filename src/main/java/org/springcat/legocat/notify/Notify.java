package org.springcat.legocat.notify;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.multi.CollectionValueMap;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springcat.legocat.strategy.BaseStrategyA;
import org.springcat.legocat.strategy.ErrorHandler;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

/**
 * @Description Notify
 * @Author springCat
 * @Date 2021-7-29 11:03
 */
public class Notify{

    private CollectionValueMap<Class<?>, ConsumerA> tableMap = new CollectionValueMap<>();

    private ExecutorService WORKER_POOL =  ForkJoinPool.commonPool();

    private ErrorHandler errorHandler;

    public void setExecutePool(ExecutorService pool) {
        this.WORKER_POOL = pool;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public Notify setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    public static Notify create(){
        return new Notify();
    }

    public Notify register(Class<?> messageType, Class<? extends ConsumerA> cls){

        ConsumerA instance = (ConsumerA) ReflectUtil.newInstance(cls);
        Assert.notNull(instance);

        if(ObjectUtil.isNotEmpty(errorHandler)){
            ReflectUtil.setFieldValue(instance, "errorHandler", errorHandler);
        }
        tableMap.putValue(messageType,instance);

        return this;
    }

    public void send(Object message){
        Collection<ConsumerA> consumers = tableMap.get(message.getClass());
        if(ObjectUtil.isEmpty(consumers)){
            return;
        }
        for (ConsumerA consumer : consumers) {
            consumer.execute(message);
        }
    }

    public void sendAsync(Object message){
        Collection<ConsumerA> consumers = tableMap.get(message.getClass());
        if(ObjectUtil.isEmpty(consumers)){
            return;
        }
        for (ConsumerA consumer : consumers) {
            WORKER_POOL.execute(() -> consumer.execute(message));;
        }
    }


}
