package org.springcat.legocat.rule;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.thread.GlobalThreadPool;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.log.Log;
import org.springcat.legocat.common.ConcurrentContext;
import org.springcat.legocat.common.ConcurrentErrorHandler;
import java.util.*;
import java.util.concurrent.ExecutorService;

/**
 * @Description RuleFactory
 * @Author springCat
 * @Date 2021-7-28 12:32
 */
public class RuleFactory {

    private Map<String, RuleI> map = new HashMap<>();

    private ExecutorService WORKER_POOL = GlobalThreadPool.getExecutor();

    private String packageName;

    private ConcurrentErrorHandler concurrentErrorHandler = (e, context) -> Log.get().error(e);

    public static RuleFactory create(){
        return new RuleFactory();
    }
    public RuleFactory setExecutePool(ExecutorService pool) {
        this.WORKER_POOL = pool;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public RuleFactory setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public RuleFactory setConcurrentErrorHandler(ConcurrentErrorHandler concurrentErrorHandler) {
        this.concurrentErrorHandler = concurrentErrorHandler;
        return this;
    }

    public RuleFactory init(){
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(getPackageName(), Rule.class);
        for (Class<?> aClass : classes) {
            handleStrategy(aClass);
        }
        return this;
    }

    public RuleI handleStrategy(Class<?> aClass){

        Rule strategy = AnnotationUtil.getAnnotation(aClass, Rule.class);
        Assert.notEmpty(strategy.key());

        RuleI ruleI = get(strategy.key());

        //already handled
        if(ObjectUtil.isNotEmpty(ruleI)){
            return ruleI;
        }

        //handle AtomicStrategyI
        if(ObjectUtil.isEmpty(strategy.strategies())) {
            return register(strategy.key(),aClass,null,concurrentErrorHandler);
        }

        //handle BaseRuleA
        Class<? extends RuleI>[] strategyClasses = strategy.strategies();
        Assert.notEmpty(strategyClasses);
        RuleI[] subStrategies = new RuleI[strategyClasses.length];
        for (int i = 0; i < strategyClasses.length; i++) {
            RuleI o = handleStrategy(strategyClasses[i]);
            Assert.notNull(o);
            subStrategies[i] = o;
        }
        return register(strategy.key(),aClass,subStrategies,concurrentErrorHandler);
    }

    public void execute(String key, ConcurrentContext context){
        RuleI baseStrategyA = get(key);
        if(ObjectUtil.isEmpty(baseStrategyA)){
            return;
        }
        baseStrategyA.execute(context);
    }

    public void executeAsync(String key, ConcurrentContext context){
        WORKER_POOL.execute(() -> execute( key,  context));
    }

    private RuleI register(String type, Class<?> aClass, RuleI[] strategies,ConcurrentErrorHandler concurrentErrorHandler){
        RuleI instance = (RuleI) ReflectUtil.newInstance(aClass);
        Assert.notNull(instance);


        if(ObjectUtil.isNotEmpty(strategies)){
            ReflectUtil.setFieldValue(instance, "strategies", strategies);
        }

        if(ObjectUtil.isNotEmpty(concurrentErrorHandler)){
            ReflectUtil.setFieldValue(instance, "concurrentErrorHandler", concurrentErrorHandler);
        }


        map.put(type, instance);
        return instance;
    }

    public RuleI get(String type){
        return map.get(type);
    }
}
