package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author liming
 * @date 2020/11/12
 * @description 922. 按奇偶排序数组 II 简单
 */
public class L922_SortArrayByParityII {

    @Test
    public void test() {
        //输出：[4,5,2,7]
        //解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
        int[] arr = {4, 2, 5, 7};

        int[] ans = sortArrayByParityII(arr);
        System.out.println("ans = " + Arrays.toString(ans));
    }

    public int[] sortArrayByParityII(int[] A) {
        int length = A.length;
        PriorityQueue<Short> priorityQueue1 = new PriorityQueue<>(length / 2);
        PriorityQueue<Short> priorityQueue2 = new PriorityQueue<>(length / 2);
        for (int i = 0; i < length; i++) {
            if ((A[i] & 1) == 1) {
                priorityQueue1.offer((short) A[i]);
            } else {
                priorityQueue2.offer((short) A[i]);
            }
        }
        for (int i = 0; i < length; i++) {
            A[i++] = priorityQueue2.poll();
            A[i] = priorityQueue1.poll();
        }
        return A;
    }
}
