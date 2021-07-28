package org.springcat.legocat.strategy;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springcat.legocat.strategy.assemble.AssembleStrategyA;
import org.springcat.legocat.strategy.single.AtomicStrategyA;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description StrategyFactory
 * @Author springCat
 * @Date 2021-7-28 12:32
 */
public class StrategyFactory {

    private String packageName;

    private Map<String, BaseStrategyI> map = new HashMap<>();

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

    private BaseStrategyI handleStrategy(Class<?> aClass){
        Map<String, Object> annotationValueMap = AnnotationUtil.getAnnotationValueMap(aClass, Strategy.class);
        String key = (String) annotationValueMap.get("key");
        Assert.notEmpty(key);

        return map.computeIfAbsent(key, k -> {
            //handle AtomicStrategyA
            if(AtomicStrategyA.class.isAssignableFrom(aClass)) {
                return (BaseStrategyI) Singleton.get(aClass);
            }

            //handle AssembleStrategyA
            if(AssembleStrategyA.class.isAssignableFrom(aClass)) {
                Class<? extends BaseStrategyI>[] strategyClasses = (Class<? extends BaseStrategyI>[]) annotationValueMap.get("strategies");
                Assert.notEmpty(strategyClasses);
                BaseStrategyI[] subStrategies = new BaseStrategyI[strategyClasses.length];
                for (int i = 0; i < strategyClasses.length; i++) {
                    BaseStrategyI o = handleStrategy(strategyClasses[i]);
                    Assert.notNull(o);
                    subStrategies[i] = o;
                }
                AssembleStrategyA instance = (AssembleStrategyA) ReflectUtil.newInstance(aClass);
                Assert.notNull(instance);
                ReflectUtil.setFieldValue(instance, "strategies", subStrategies);
                return instance;
            }
            return null;
        });

    }

    public void execute(String key, Dict context){
        BaseStrategyI baseStrategyA = map.get(key);
        if(ObjectUtil.isEmpty(baseStrategyA)){
            return;
        }
        baseStrategyA.invoke(context);
    }

}
