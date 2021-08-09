package org.springcat.legocat.strategy;

import java.lang.annotation.*;

/**
 * @Description AtomicStrategyClass
 * @Author springCat
 * @Date 2021-7-28 14:10
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Strategy {

    String key();

    Class<? extends BaseStrategyI>[] strategies() default {};

}
