package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author liming
 * @date 2020/10/28
 * @description 1207. 独一无二的出现次数 【简单】
 * https://leetcode-cn.com/problems/unique-number-of-occurrences/
 */
public class L1207_UniqueNumberOfOccurrences {

    @Test
    public void test() {
        // 预期输出：true
        // 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
        int[] arr = {1, 2, 2, 1, 1, 3};
        // 预期 false
        int[] arr1 = {1, 2};
        // 预期 true
        int[] arr2 = {-3,0,1,-3,1,1,1,-3,10,0};

        boolean flag = uniqueOccurrences(arr);
        System.out.println("flag = " + flag);

        boolean flag1 = uniqueOccurrences(arr1);
        System.out.println("flag1 = " + flag1);

        boolean flag2 = uniqueOccurrences(arr2);
        System.out.println("flag2 = " + flag2);
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            Integer value = map.getOrDefault(num, 0);
            map.put(num, value + 1);
        }
        Set<Integer> counts = new HashSet<>();
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (!counts.add(entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}
