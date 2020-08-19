package com.wandou.demo.algorithm;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author liming
 * @date 2020-06-30
 * @description 算法
 */
public class CommonAlgorithmDemo {

    @Test
    public void bigintegerAddTest() {
        System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
        bigNumAdd("111", "2010");
        bigNumAdd_2("111", "2010");
        bigNumAdd(Long.MAX_VALUE + "", Long.MAX_VALUE + "");
        bigNumAdd_2(Long.MAX_VALUE + "", Long.MAX_VALUE + "");

    }

    /**
     * 超长整数相加
     *
     * @param strNum1
     * @param strNum2
     */
    public void bigNumAdd(String strNum1, String strNum2) {
        char[] chars1 = strNum1.toCharArray();
        char[] chars2 = strNum2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        boolean oneBiger = len1 > len2;
        int len = Integer.max(len1, len2);
        List<Integer> list = new ArrayList<>();
        //进位
        int carry = 0;
        for (int i = 0; i < len; i++) {
            int temp;
            if (oneBiger) {
                if (i < len2) {
                    temp = Integer.parseInt(chars1[len1 - 1 - i] + "") + Integer.parseInt(chars2[len2 - 1 - i] + "") + carry;
                } else {
                    temp = Integer.parseInt(chars1[len1 - 1 - i] + "") + carry;
                }
            } else {
                if (i < len1) {
                    temp = Integer.parseInt(chars1[len1 - 1 - i] + "") + Integer.parseInt(chars2[len2 - 1 - i] + "") + carry;
                } else {
                    temp = Integer.parseInt(chars2[len2 - 1 - i] + "") + carry;
                }
            }
            carry = 0;
            if (temp > 9) {
                carry = 1;
                list.add(temp - 10);
            } else {
                list.add(temp);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(list.size() - 1 - i));
        }
        System.out.println(sb.toString());
    }

    public void bigNumAdd_2(String strNum1, String strNum2) {
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
        System.out.println(targetSb.reverse().toString());
    }

    public void bigNumAddV2(String num1, String num2) {
        int minLen = Integer.min(num1.length(), num2.length());
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        //进位
        int carry = 0;
        //倒着遍历，从个位开始计算
        for (int i = minLen - 1; i >= 0 ; i++) {
            int realNum1 = chars1[i];
            int realNum2 = chars2[i];
            int temp = realNum1 + realNum2 + carry;
        }

    }

    @Test
    public void bigIntJdk() {
        BigInteger bigInteger = BigInteger.probablePrime(6, new Random());
        System.out.println("bigInteger = " + bigInteger);
        BigInteger bigInteger1 = new BigInteger(Long.MAX_VALUE + "");
        BigInteger bigInteger2 = new BigInteger(Long.MAX_VALUE - 1 + "");
        BigInteger sum = bigInteger1.add(bigInteger2);
        System.out.println("sum = " + sum);
    }


}
