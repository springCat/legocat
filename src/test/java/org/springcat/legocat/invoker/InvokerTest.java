package org.springcat.legocat.invoker;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.util.Optional;


/**
 * @Description InvokerTest
 * @Author springCat
 * @Date 2021-7-29 18:15
 */
public class InvokerTest {

    @Test
    public void test() throws Exception {
        Invoker<String> filter = Invoker.create();
        Optional<String> o = filter.step(context -> {
            context.set("key1","value1");
            Console.log("before1");
        }).step(context -> {
            Console.log("before2");
            Console.log(context.getStr("key1"));
        }).invoker(dict -> {
            new Duck().bark();
            return "ssss";
        }).step(context -> {
            Console.log("afer1");
            Console.log("result");
        }).step(context ->
            Console.log("afer2")
        ).errorHandler((e,context) -> Console.log(e))
          .get();

        o.ifPresent(s -> Console.log(StrUtil.upperFirst(s)));
    }
}
