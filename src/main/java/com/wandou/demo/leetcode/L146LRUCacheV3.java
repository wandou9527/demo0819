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
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
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
            storage.put(key, node);
            if (storage.size() > capacity) {
                Node last = removeTail();
                storage.remove(last.key);
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

//    private void removeHead() {
//        Node first = head.next;
//        head.next = first.next;
//        first.next.prev = head;
//    }

    /**
     *
     */
    public void addToHead(Node node) {
        // head 伪链头
        Node first = head.next;
        head.next = node;
        node.prev = head;
        node.next = first;
        first.prev = node;
    }

    public void moveToHead(Node node) {
        remove(node);
        addToHead(node);
    }

    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        node.prev = null;
        node.next = null;
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
    }

    public Node removeTail() {
        Node last = tail.prev;
        tail.prev = last.prev;
        last.prev.next = tail;
        last.next = null;
        return last;
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
