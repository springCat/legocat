package org.springcat.legocat.factory;

import org.junit.Test;

/**
 * @Description FactoryTest
 * @Author springCat
 * @Date 2021-7-29 17:30
 */
public class FactoryTest {

    @Test
    public void test(){
        ObjectFactory<Duck> factory = new ObjectFactory<>();
        factory.register("yellow",YellowDuck.class);
        factory.register("red",RedDuck.class);

        factory.register("yellowM",YellowDuck.class,1);
        factory.register("redM",RedDuck.class,1);

        Duck red = factory.get("red");
        red.bark();

        Duck yellow = factory.get("yellow");
        yellow.bark();

        Duck redM = factory.get("redM");
        redM.bark();

        Duck yellowM = factory.get("yellowM");
        yellowM.bark();
    }
}
