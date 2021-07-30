package org.springcat.legocat.strategy;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springcat.legocat.factory.ObjectFactory;
import org.springcat.legocat.strategy.group.GroupStrategyA;
import org.springcat.legocat.strategy.atomic.AtomicStrategyA;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

/**
 * @Description StrategyFactory
 * @Author springCat
 * @Date 2021-7-28 12:32
 */
public class StrategyFactory extends ObjectFactory<BaseStrategyA> {

    private static ExecutorService WORKER_POOL =  ForkJoinPool.commonPool();

    private String packageName;

    private ErrorHandler errorHandler;

    public static void setExecutePool(ExecutorService pool) {
        WORKER_POOL = pool;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void init(){
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(getPackageName(), Strategy.class);
        for (Class<?> aClass : classes) {
            handleStrategy(aClass);
        }
    }

    public BaseStrategyA handleStrategy(Class<?> aClass){

        Strategy strategy = AnnotationUtil.getAnnotation(aClass, Strategy.class);
        Assert.notEmpty(strategy.key());

        BaseStrategyA baseStrategy = get(strategy.key());

        //already handled
        if(ObjectUtil.isNotEmpty(baseStrategy)){
            return baseStrategy;
        }

        //handle AtomicStrategyA
        if(AtomicStrategyA.class.isAssignableFrom(aClass)) {
            return register(strategy.key(), newInstance(aClass,errorHandler,null));
        }

        //handle GroupStrategyA
        if(GroupStrategyA.class.isAssignableFrom(aClass)) {
            Class<? extends BaseStrategyA>[] strategyClasses = strategy.strategies();
            Assert.notEmpty(strategyClasses);
            BaseStrategyA[] subStrategies = new BaseStrategyA[strategyClasses.length];
            for (int i = 0; i < strategyClasses.length; i++) {
                BaseStrategyA o = handleStrategy(strategyClasses[i]);
                Assert.notNull(o);
                subStrategies[i] = o;
            }
            BaseStrategyA instance = newInstance(aClass,errorHandler,subStrategies);
            register(strategy.key(),newInstance(aClass,errorHandler,subStrategies));
            return instance;
        }


        return null;
    }

    private BaseStrategyA newInstance(Class<?> aClass,ErrorHandler errorHandler,BaseStrategyA[] strategies){
        BaseStrategyA instance = (BaseStrategyA) ReflectUtil.newInstance(aClass);
        Assert.notNull(instance);

        if(ObjectUtil.isNotEmpty(errorHandler)){
            ReflectUtil.setFieldValue(instance, "errorHandler", errorHandler);
        }

        if(ObjectUtil.isNotEmpty(strategies)){
            ReflectUtil.setFieldValue(instance, "strategies", strategies);
        }

        return instance;
    }

    public void execute(String key, StrategyContext context){
        BaseStrategyA baseStrategyA = get(key);
        if(ObjectUtil.isEmpty(baseStrategyA)){
            return;
        }
        baseStrategyA.execute(context);
    }

    public void executeAsync(String key, StrategyContext context){
        WORKER_POOL.execute(() -> execute( key,  context));
    }

}
