package com.wandou.demo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liming
 * @date 2020/9/25
 * @description
 */

public class L146LRUCacheV2 {

    private Map<Integer, Integer> storage;
    private int capacity;
    private Queue<Integer> queue = new LinkedBlockingQueue<>();

    public L146LRUCacheV2(int capacity) {
        this.storage = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = storage.get(key);
        if (value != null) {
            addToTail(key);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        addToTail(key);
        storage.put(key, value);
    }

    public void addToTail(Integer key) {
        queue.remove(key);
        queue.add(key);
        while (queue.size() > capacity) {
            Integer head = queue.poll();
            if (head != null) {
                storage.remove(head);
            }
        }
    }
}
