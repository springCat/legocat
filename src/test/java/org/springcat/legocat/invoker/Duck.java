package org.springcat.legocat.invoker;

import cn.hutool.core.lang.Console;

/**
 * @Description Duck
 * @Author springCat
 * @Date 2021-7-29 18:19
 */
public class Duck {

    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void bark(){
        Console.log("gua gua");
    }

    public void bark1(){
        Console.log("gua gua1");
    }
}
