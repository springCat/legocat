package org.springcat.legocat.notify;

import cn.hutool.core.map.multi.CollectionValueMap;
import cn.hutool.core.thread.GlobalThreadPool;
import cn.hutool.core.util.ObjectUtil;
import org.springcat.legocat.common.ErrorHandler;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/**
 * @Description Notify
 * @Author springCat
 * @Date 2021-7-29 11:03
 */
public class Notify{

    private CollectionValueMap<Class<?>, Consumer> tableMap = new CollectionValueMap<>();

    private ExecutorService WORKER_POOL = GlobalThreadPool.getExecutor();

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

    public <T> Notify register(Class<T> messageType, Consumer<T> consumer){
        tableMap.putValue(messageType,consumer);
        return this;
    }

    public void send(Object message){
        Collection<Consumer> consumers = tableMap.get(message.getClass());
        if(ObjectUtil.isEmpty(consumers)){
            return;
        }

        for (Consumer consumer : consumers) {
            try {
                consumer.accept(message);
            }catch (Exception e){
                errorHandler.execute(e);
            }
        }
    }

    public void sendAsync(Object message){
        Collection<Consumer> consumers = tableMap.get(message.getClass());
        if(ObjectUtil.isEmpty(consumers)){
            return;
        }
        for (Consumer consumer : consumers) {
            WORKER_POOL.execute(() -> {
                try {
                    consumer.accept(message);
                }catch (Exception e){
                    errorHandler.execute(e);
                }
            });
        }
    }

}
