package com.wandou.demo.leetcode;

/**
 * @author liming
 * @date 2020/9/22
 * @description
 */
public class L50_PowXN {

    /**
     * 位运算不能用在double上
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        int a = 2 << 2;
        return a << n;
    }
}
