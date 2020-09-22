package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

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

    @Test
    public void test2() {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        linkedHashMap.put(3, 3);
        System.out.println("linkedHashMap = " + linkedHashMap);
        linkedHashMap.get(2);
        System.out.println("linkedHashMap = " + linkedHashMap);
        linkedHashMap.put(2, 2);
        System.out.println("linkedHashMap = " + linkedHashMap);
        linkedHashMap.put(0, 0);
        System.out.println("linkedHashMap = " + linkedHashMap);
    }

    @Test
    public void test3My() {
        LinkedHashMap<Integer, Integer> myLinkedHashMap = new MyLinkedHashMap();
        myLinkedHashMap.put(5, 5);
        myLinkedHashMap.put(3, 3);
        myLinkedHashMap.put(7, 7);
        System.out.println("myLinkedHashMap = " + myLinkedHashMap);
        myLinkedHashMap.put(9, 9);
        System.out.println("myLinkedHashMap = " + myLinkedHashMap);
    }

    class MyLinkedHashMap extends LinkedHashMap {

        /**
         * 删除老的元素
         *
         * @param eldest
         * @return
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            boolean remove = size() > 3;
            System.out.println("remove? " + remove);
            return remove;
        }
    }
}
