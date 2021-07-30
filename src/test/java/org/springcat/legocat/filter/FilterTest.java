package org.springcat.legocat.filter;

import cn.hutool.core.lang.Console;
import cn.hutool.log.Log;
import org.junit.Test;

import java.util.Optional;


/**
 * @Description FilterTest
 * @Author springCat
 * @Date 2021-7-29 18:15
 */
public class FilterTest {

    @Test
    public void test() throws Exception {

        Optional<Object> o = Filter.create().before(context -> {
            Console.log("before1");
            return true;
        }).before(context -> {
            Console.log("before2");
            return true;
        }).invoker(dict -> {
            new Duck().bark();
            return true;
        }).after(context -> {
            Console.log("afer1");
            throw new RuntimeException("exception test");
        })
           .after(context -> Console.log("afer1"))
            .errorHandler(e -> Console.log(e))
             .get();

    }
}
