package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author liming
 * @date 2020/11/19 每日打卡
 * @description 283. 移动零 [简单]
 */
public class L283_MoveZeroes {

    @Test
    public void test() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println("nums = " + Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int left = 0;
        int right = 0;
        while (right < length) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public void moveZeroesOld(int[] nums) {
        System.out.println("初始数组: " + Arrays.toString(nums));
        int length = nums.length;
        int nextZero = length - 1;
        for (int i = 0; i < length; i++) {
            if (nums[length - 1 - i] == 0) {
                nextZero--;
            } else {
                break;
            }
        }
        for (int i = 0; i < nextZero; i++) {
            if (nums[i] == 0) {
                // 交换
                nums[i] = nums[i] ^ nums[nextZero];
                nums[nextZero] = nums[i] ^ nums[nextZero];
                nums[i] = nums[i] ^ nums[nextZero];
                nextZero--;
            }
        }
    }

}
