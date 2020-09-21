package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * @author liming
 * @date 2020/9/19
 * @description
 */
public class L146LRUCacheTest {

    @Test
    public void test() {
        L146LRUCache l146LRUCache = new L146LRUCache(3);
        l146LRUCache.put(1, 1);
        l146LRUCache.put(2, 2);
        l146LRUCache.put(3, 3);
        l146LRUCache.printQueue();

        int value = l146LRUCache.get(1);
        System.out.println("value = " + value);
        l146LRUCache.printQueue();

        l146LRUCache.put(4, 4);
        l146LRUCache.printQueue();

        int value2 = l146LRUCache.get(3);
        System.out.println("value2 = " + value2);
        l146LRUCache.printQueue();

        int value3 = l146LRUCache.get(2);
        System.out.println("value3 = " + value3);
        l146LRUCache.printQueue();

    }

    public void test2() {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap();
    }
}
