package com.wandou.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liming
 * @date 2020/9/25
 * @description 用HashMap与链表实现
 */

public class L146LRUCacheV3 {

    private Map<Integer, Node> storage;
    private int capacity;
    private Node head;
    private Node tail;

    public L146LRUCacheV3(int capacity) {
        this.storage = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!storage.containsKey(key)) {
            return -1;
        }
        Node node = storage.get(key);
        moveToHead(node);
        return node.key;
    }

    public void put(int key, int value) {
        Node node = storage.get(key);
        if (node == null) {
            node = new Node(key, value);
            addToHead(node);
        } else {
            node.value = value;
            moveToHead(node);
        }
        storage.put(key, node);
        afterPut(node);
    }

    private void afterPut(Node node) {
        if (storage.size() > capacity) {
            storage.remove(tail.key);
            removeTail();
            addToHead(node);
        }
    }

    /**
     *
     */
    public void addToHead(Node node) {
        //前链表头
        Node lastHead = head;
        head = node;
        //前头为null说明链表为空
        if (lastHead == null) {
            tail = node;
        } else {
            head.next = lastHead;
        }
    }

    public void moveToHead(Node node) {
        remove(node);
        addToHead(node);
    }

    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
    }

    public void removeTail() {
        Node prev = tail.prev;
        tail.prev = null;
        tail = prev;
        tail.next = null;
    }


    /**
     * node
     */
    static class Node {
        Integer key;
        Integer value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
