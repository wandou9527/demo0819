package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liming
 * @date 2020/10/28
 * @description 260. 只出现一次的数字 III 【中等】
 */
public class L260_SingleNumberIII {

    @Test
    public void test() {
        int[] nums = {1, 2, 1, 3, 2, 5};
        int[] singleNumbers = singleNumber(nums);
        System.out.println("singleNumbers = " + Arrays.toString(singleNumbers));
    }

    public int[] singleNumber(int[] nums) {
        int length = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (set.contains(num)) {
                set.remove(num);
                continue;
            }
            set.add(num);
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
