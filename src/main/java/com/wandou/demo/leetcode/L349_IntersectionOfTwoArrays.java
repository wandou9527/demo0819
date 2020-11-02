package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @author liming
 * @date 2020/11/2
 * @description
 */
public class L349_IntersectionOfTwoArrays {

    @Test
    public void test() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] intersection = intersection(nums1, nums2);
        System.out.println("intersection = " + Arrays.toString(intersection));

        int[] nums1_1 = {1};
        int[] nums2_1 = {1};
        int[] intersection1 = intersection(nums1_1, nums2_1);
        System.out.println("intersection1 = " + Arrays.toString(intersection1));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        int maxLen = Math.max(nums1.length, nums2.length);
        int minLen = Math.min(nums1.length, nums2.length);
        for (int i = 0; i < maxLen; i++) {
            if (i <= (nums1.length - 1)) {
                set1.add(nums1[i]);
            }
            if (i <= (nums2.length - 1)) {
                set2.add(nums2[i]);
            }
        }

        Set<Integer> intersection = new HashSet<>(minLen);
        Iterator<Integer> iterator = set1.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (set2.contains(next)) {
                intersection.add(next);
            }
        }
        int[] intersectionArr = new int[intersection.size()];
        Iterator<Integer> iterator1 = intersection.iterator();
        int i = 0;
        while (iterator1.hasNext()) {
            Integer next = iterator1.next();
            intersectionArr[i++] = next;
        }
        return intersectionArr;
    }

}
