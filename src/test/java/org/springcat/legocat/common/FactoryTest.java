package org.springcat.legocat.common;

import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.util.Optional;

/**
 * @Description FactoryTest
 * @Author springCat
 * @Date 2021-8-9 18:16
 */
public class FactoryTest {

    @Test
    public void test(){
        ObjectPool<String,Colour> factory = ObjectPool.create();
        factory.put("b",Blue.class);
        factory.put("r",Red.class);
        factory.put(Red.class);

        Optional<Colour> b = factory.get("red");
        b.ifPresent(Console::log);
    }
}
