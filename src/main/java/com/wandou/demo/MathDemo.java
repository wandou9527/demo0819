package com.wandou.demo;

import org.junit.Test;

/**
 * @author liming
 * @date 2020/9/26
 * @description
 */
public class MathDemo {

    @Test
    public void test() {
        /**
         * 返回双精度值的自然对数(以e为底数)。
         */
        System.out.println(Math.log(4));

        /**
         * 三角函数，传的参数为弧度表示的角
         * 弧度制 https://baike.baidu.com/item/%E5%BC%A7%E5%BA%A6%E5%88%B6/3315973?fr=aladdin
         * 等于半径长的圆弧所对的圆心角叫做1弧度的角。
         * 那么半圆的弧长为π（3.14159）
         */
        System.out.println(Math.sin(Math.PI / 6));
        System.out.println(Math.tan(60));
        System.out.println(Math.cos(Math.toRadians(60)));
    }
}
