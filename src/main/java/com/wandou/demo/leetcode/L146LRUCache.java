package com.wandou.demo.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author liming
 * @date 2020/9/19
 * @description
 */

public class L146LRUCache {

    private Map<Integer, Integer> storage;
    private int capacity;
    private Queue<Integer> queue = new LinkedList<>();

    public L146LRUCache() {
    }

    public L146LRUCache(int capacity) {
        this.storage = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer value = storage.get(key);
        if (value != null) {
            addToTail(key);
            return value;
        }
        return 0;
    }

    public void put(int key, int value) {
        addToTail(key);
        storage.put(key, value);
    }

    public Map<Integer, Integer> getStorage() {
        return storage;
    }

    public int getCapacity() {
        return capacity;
    }

    public Queue<Integer> getQueue() {
        return queue;
    }

    public void addToTail(Integer key) {
        queue.remove(key);
        queue.add(key);
        if (queue.size() > capacity) {
            queue.poll();
        }
    }

    public void printQueue() {
//        Integer head;
//        StringBuilder sb = new StringBuilder();
//        while ((head = queue.peek()) != null) {
//            sb.append(head).append(", ");
//        }
        System.out.println("queue: " + queue);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
