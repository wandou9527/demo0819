package com.wandou.demo.leetcode;

import org.junit.Test;

/**
 * @author liming
 * @date 2020/11/3 每日打卡
 * @description 941. 有效的山脉数组 【简单】
 */
public class L941_ValidMountainArray {

    @Test
    public void test() {
        // 输出：false
        int[] A = {2, 1};
        // 输出：false
        int[] A1 = {3, 5, 5};
        // 输出：true
        int[] A2 = {0, 3, 2, 1};
        // true
        int[] A3 = {0, 1, 2, 4, 2, 1};
        // false
        int[] A4 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        // true
        int[] A5 = {1, 3, 2};

        boolean a = validMountainArray(A);
        System.out.println("a = " + a);

        boolean a1 = validMountainArray(A1);
        System.out.println("a1 = " + a1);

        boolean a2 = validMountainArray(A2);
        System.out.println("a2 = " + a2);

        boolean a3 = validMountainArray(A3);
        System.out.println("a3 = " + a3);

        boolean a4 = validMountainArray(A4);
        System.out.println("a4 = " + a4);

        boolean a5 = validMountainArray(A5);
        System.out.println("a5 = " + a5);
    }

    public boolean validMountainArray(int[] A) {
        int length = A.length;
        if (length < 3) {
            return false;
        }
        int i = 0;
        while (i + 1 < length && A[i] < A[i + 1]) {
            i++;
        }
        if (i == 0 || i == length - 1) {
            return false;
        }
        while (i + 1 < length && A[i] > A[i + 1]) {
            i++;
        }
        return i == length - 1;
    }
}
