package org.springcat.legocat.common;

import java.util.StringJoiner;

/**
 * @Description Colour
 * @Author springCat
 * @Date 2021-8-9 18:16
 */
public class Colour {

    @Override
    public String toString() {
        return new StringJoiner(", ", Colour.class.getSimpleName() + "[", "]")
                .toString();
    }
}
