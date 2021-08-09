package org.springcat.legocat.rule;

import cn.hutool.core.util.ObjectUtil;
import org.springcat.legocat.common.ConcurrentContext;

/**
 * @Description Rule
 * @Author springCat
 * @Date 2021-7-28 12:30
 */
public interface RuleI {

    default RuleI[] getStrategies(){
        return null;
    }

    boolean invoke(ConcurrentContext context);

    boolean execute(ConcurrentContext context);
}
