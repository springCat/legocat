package org.springcat.legocat.factory;

/**
 * @Description Duck
 * @Author springCat
 * @Date 2021-7-29 17:28
 */
public abstract class Duck {

    protected int sex;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public abstract void bark();
}
