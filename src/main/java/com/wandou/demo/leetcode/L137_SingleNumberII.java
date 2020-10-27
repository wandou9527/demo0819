package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liming
 * @date 2020/10/28
 * @description 137. 只出现一次的数字 II 【中等】
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 * <p>
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-ii
 */
public class L137_SingleNumberII {

    @Test
    public void test() {
        int[] nums = new int[]{2, 2, 3, 2};
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};
        // 预期 -2147483648 实际 0 运行有问题
        int[] nums3 = {-401451, -177656, -2147483646, -473874, -814645, -2147483646, -852036, -457533, -401451, -473874, -401451, -216555, -917279, -457533, -852036, -457533, -177656, -2147483646, -177656, -917279, -473874, -852036, -917279, -216555, -814645, 2147483645, -2147483648, 2147483645, -814645, 2147483645, -216555};
        // 预期 2147483647
        int[] nums4 = {43, 16, 45, 89, 45, -2147483648, 45, 2147483646, -2147483647, -2147483648, 43, 2147483647, -2147483646, -2147483648, 89, -2147483646, 89, -2147483646, -2147483647, 2147483646, -2147483647, 16, 16, 2147483646, 43};
//        int singleNumber = singleNumber(nums);
//        System.out.println("singleNumber = " + singleNumber);

//        int singleNumber1 = singleNumber(nums2);
//        System.out.println("singleNumber1 = " + singleNumber1);

        int singleNumber2 = singleNumber(nums3);
        System.out.println("singleNumber2 = " + singleNumber2);


//        System.out.println("================ V2 ================");
//        int singleNumberV2 = singleNumberV2(nums);
//        System.out.println("singleNumberV2 = " + singleNumberV2);
//        int singleNumberV21 = singleNumberV2(nums2);
//        System.out.println("singleNumberV21 = " + singleNumberV21);
//        int singleNumberV22 = singleNumberV2(nums3);
//        System.out.println("singleNumberV22 = " + singleNumberV22);

        System.out.println("============== V3 ==================");
        int singleNumberV3 = singleNumberV3(nums3);
        System.out.println("singleNumberV3 = " + singleNumberV3);

    }

    public int singleNumber(int[] nums) {
        int length = nums.length;
        long sum = 0L;
        Set<Long> set = new HashSet<>(length);
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            set.add((long) nums[i]);
        }
        long singleSum = set.stream().mapToLong(Long::longValue).sum();
        System.out.println("sum = " + sum);
        System.out.println("singleSum = " + singleSum);
        return (int) (((3 * singleSum) - sum) / 2);
    }

    public int singleNumberV2(int[] nums) {
        int length = nums.length;
        long sum = 0L;
        Set<Integer> set = new HashSet<>(length);
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            set.add(nums[i]);
        }
        long singleSum = 0L;
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            singleSum += iterator.next();
        }
        return (int) (((3 * singleSum) - sum) / 2);
    }

    public int singleNumberV3(int[] nums) {
        int length = nums.length;
        long sum = 0L;
        Set<Integer> set = new HashSet<>(length);
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            set.add(nums[i]);
        }
        //这种流式处理求和有溢出风险
//        long singleSum = set.stream().mapToInt(Integer::intValue).sum();
        long singleSum = set.stream().mapToLong(Integer::longValue).sum();
        System.out.println("sum = " + sum);
        System.out.println("singleSum = " + singleSum);
        System.out.println("3 * singleSum: " + (3 * singleSum));
        return (int) (((3 * singleSum) - sum) / 2);
    }

}
