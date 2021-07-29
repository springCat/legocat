package org.springcat.legocat.strategy;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springcat.legocat.objects.ObjectFactory;
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
public class StrategyFactory extends ObjectFactory<BaseStrategyI> {

    private static ExecutorService WORKER_POOL =  ForkJoinPool.commonPool();

    public static void setExecutePool(ExecutorService pool) {
        WORKER_POOL = pool;
    }

    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void init(){
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(getPackageName(), Strategy.class);
        for (Class<?> aClass : classes) {
            handleStrategy(aClass);
        }
    }

    public BaseStrategyI handleStrategy(Class<?> aClass){

        Strategy strategy = AnnotationUtil.getAnnotation(aClass, Strategy.class);
        Assert.notEmpty(strategy.key());

        BaseStrategyI baseStrategy = get(strategy.key());

        //already handled
        if(ObjectUtil.isNotEmpty(baseStrategy)){
            return baseStrategy;
        }

        //handle AtomicStrategyA
        if(AtomicStrategyA.class.isAssignableFrom(aClass)) {
            return register(strategy.key(), (Class<BaseStrategyI>) aClass);
        }

        //handle GroupStrategyA
        if(GroupStrategyA.class.isAssignableFrom(aClass)) {
            Class<? extends BaseStrategyI>[] strategyClasses = strategy.strategies();
            Assert.notEmpty(strategyClasses);
            BaseStrategyI[] subStrategies = new BaseStrategyI[strategyClasses.length];
            for (int i = 0; i < strategyClasses.length; i++) {
                BaseStrategyI o = handleStrategy(strategyClasses[i]);
                Assert.notNull(o);
                subStrategies[i] = o;
            }
            GroupStrategyA instance = (GroupStrategyA) ReflectUtil.newInstance(aClass);
            Assert.notNull(instance);
            ReflectUtil.setFieldValue(instance, "strategies", subStrategies);

            register(strategy.key(),instance);
            return instance;
        }


        return null;
    }

    public void execute(String key, StrategyContext context){
        BaseStrategyI baseStrategyA = get(key);
        if(ObjectUtil.isEmpty(baseStrategyA)){
            return;
        }
        baseStrategyA.invoke(context);
    }

    public void executeAsync(String key, StrategyContext context){
        WORKER_POOL.execute(() -> execute( key,  context));
    }

}
