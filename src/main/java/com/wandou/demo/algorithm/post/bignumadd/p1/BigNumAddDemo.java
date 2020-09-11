package com.wandou.demo.algorithm.post.bignumadd.p1;

import org.junit.Test;

/**
 * @author liming
 * @date 2020-08-19
 * @description 算法
 */
public class BigNumAddDemo {

    @Test
    public void bigintegerAddTest() {
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
        bigNumAdd("111", "2010");
        bigNumAdd("1112", "2010999");
        bigNumAdd(Long.MAX_VALUE + "", Long.MAX_VALUE + "");
        bigNumAdd(Long.MAX_VALUE + "", "10");
    }

    public void bigNumAdd(String strNum1, String strNum2) {
        char[] chars1 = strNum1.toCharArray();
        char[] chars2 = strNum2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        boolean oneBiger = len1 > len2;
        int len = Integer.max(len1, len2);
        StringBuilder targetSb = new StringBuilder();
        //进位
        int carry = 0;
        for (int i = 0; i < len; i++) {
            int temp;
            int idx1 = len1 - 1 - i;
            int idx2 = len2 - 1 - i;
            if (oneBiger) {
                if (i < len2) {
                    temp = Integer.parseInt(chars1[idx1] + "") + Integer.parseInt(chars2[idx2] + "") + carry;
                } else {
                    temp = Integer.parseInt(chars1[idx1] + "") + carry;
                }
            } else {
                if (i < len1) {
                    temp = Integer.parseInt(chars1[idx1] + "") + Integer.parseInt(chars2[idx2] + "") + carry;
                } else {
                    temp = Integer.parseInt(chars2[idx2] + "") + carry;
                }
            }
            carry = 0;
            if (temp > 9) {
                carry = 1;
                targetSb.append(temp - 10);
            } else {
                targetSb.append(temp);
            }
        }
        if (carry > 0) {
            targetSb.append(carry);
        }
        System.out.println(targetSb.reverse().toString());
    }

}
