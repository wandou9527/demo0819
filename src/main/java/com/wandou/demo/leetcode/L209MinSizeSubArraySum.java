package com.wandou.demo.leetcode;

import org.junit.Test;

/**
 * @author liming
 * @date 2020/9/19
 * @description 209. 长度最小的子数组 【中等】
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * 示例：
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 */
public class L209MinSizeSubArraySum {

    @Test
    public void test() {
        int s = 7;
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(s, nums));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int length = nums.length;
        if (length <= 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            int sum = 0;
            for (int j = i; j < length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
