package org.springcat.legocat.common;

import java.util.StringJoiner;

/**
 * @Description Red
 * @Author springCat
 * @Date 2021-7-28 16:12
 */

public class Red extends Colour  {

    @Override
    public String toString() {
        return new StringJoiner(", ", Red.class.getSimpleName() + "[", "]")
                .toString();
    }

}
