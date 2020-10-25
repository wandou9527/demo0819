package com.wandou.demo.leetcode;

import org.junit.Test;

/**
 * @author liming
 * @date 2020/10/20
 * @description 136. 只出现一次的数字【简单】
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 */
public class L136_SingleNumber {

    @Test
    public void test() {
        int single = singleNumber(new int[]{1, 9, 3, 2, 5, 8, 1, 2, 3, 5, 8});
        System.out.println("single = " + single);
    }

    @Test
    public void test1() {
        // 1
        System.out.println("(0 ^ 1) = " + (0 ^ 1));
        // 8
        System.out.println("(0 ^ 8) = " + (0 ^ 8));
        // 0
        System.out.println("(0 ^ 8 ^ 8) = " + (0 ^ 8 ^ 8));
        // 6
        System.out.println("(3 ^ 8 ^ 8) = " + (3 ^ 6 ^ 6));
    }

    public int singleNumber(int[] nums) {
        int target = 0;
        for (int n : nums) {
            target ^= n;
        }
        return target;
    }

}
