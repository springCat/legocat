package org.springcat.legocat.rule;

import java.lang.annotation.*;

/**
 * @Description AtomicStrategyClass
 * @Author springCat
 * @Date 2021-7-28 14:10
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Rule {

    String key();

    Class<? extends RuleI>[] strategies() default {};

}
