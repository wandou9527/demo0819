package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liming
 * @date 2020/9/22
 * @description 3. 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class L3_LongestSubstringWithoutRepeating {

    @Test
    public void test() {
        int len = lengthOfLongestSubstring("efghiif");
        System.out.println("len = " + len);
    }

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int subLen = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (!set.add(s.charAt(j))) {
                    break;
                }
            }
            subLen = Math.max(set.size(), subLen);
            set.clear();
        }
        return subLen;
    }
}
