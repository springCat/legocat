package org.springcat.legocat.strategy;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.thread.GlobalThreadPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.log.Log;
import org.springcat.legocat.common.ConcurrentContext;
import org.springcat.legocat.common.ConcurrentErrorHandler;
import org.springcat.legocat.strategy.group.GroupStrategyA;
import org.springcat.legocat.strategy.atomic.AtomicStrategyI;

import java.util.*;
import java.util.concurrent.ExecutorService;

/**
 * @Description StrategyFactory
 * @Author springCat
 * @Date 2021-7-28 12:32
 */
public class StrategyFactory{

    private Map<String, BaseStrategyI> map = new HashMap<>();

    private ExecutorService WORKER_POOL = GlobalThreadPool.getExecutor();

    private String packageName;

    private ConcurrentErrorHandler concurrentErrorHandler = (e, context) -> Log.get().error(e);

    public static StrategyFactory create(){
        return new StrategyFactory();
    }
    public StrategyFactory setExecutePool(ExecutorService pool) {
        this.WORKER_POOL = pool;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public StrategyFactory setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public ConcurrentErrorHandler getConcurrentErrorHandler() {
        return concurrentErrorHandler;
    }

    public StrategyFactory setConcurrentErrorHandler(ConcurrentErrorHandler concurrentErrorHandler) {
        this.concurrentErrorHandler = concurrentErrorHandler;
        return this;
    }

    public StrategyFactory init(){
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(getPackageName(), Strategy.class);
        for (Class<?> aClass : classes) {
            handleStrategy(aClass);
        }
        return this;
    }

    public BaseStrategyI handleStrategy(Class<?> aClass){

        Strategy strategy = AnnotationUtil.getAnnotation(aClass, Strategy.class);
        Assert.notEmpty(strategy.key());

        BaseStrategyI baseStrategy = get(strategy.key());

        //already handled
        if(ObjectUtil.isNotEmpty(baseStrategy)){
            return baseStrategy;
        }

        //handle AtomicStrategyI
        if(AtomicStrategyI.class.isAssignableFrom(aClass)) {
            return register(strategy.key(),aClass,null);
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
            return register(strategy.key(),aClass,subStrategies);
        }

        return null;
    }

    public void execute(String key, ConcurrentContext context){
        BaseStrategyI baseStrategyA = get(key);
        if(ObjectUtil.isEmpty(baseStrategyA)){
            return;
        }
        context.setConcurrentErrorHandler(concurrentErrorHandler);
        baseStrategyA.execute(context);
    }

    public void executeAsync(String key, ConcurrentContext context){
        WORKER_POOL.execute(() -> execute( key,  context));
    }

    private BaseStrategyI register(String type, Class<?> aClass, BaseStrategyI[] strategies){
        BaseStrategyI instance = (BaseStrategyI) ReflectUtil.newInstance(aClass);
        Assert.notNull(instance);


        if(ObjectUtil.isNotEmpty(strategies)){
            ReflectUtil.setFieldValue(instance, "strategies", strategies);
        }

        map.put(type, instance);
        return instance;
    }

    public BaseStrategyI get(String type){
        return map.get(type);
    }
}
