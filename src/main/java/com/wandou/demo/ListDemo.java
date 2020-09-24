package com.wandou.demo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author liming
 * @date 2020/9/23
 * @description
 */
public class ListDemo {

    private LinkedList<String> linkedList = new LinkedList<>();
    private ArrayList<String> arrayList = new ArrayList<>();

    private int num = 1000000;


    @Before
    public void init() {
        System.out.println("num = " + num);
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            linkedList.add(new String("l" + i));
        }
        System.out.println("linkedList add time: " + (System.currentTimeMillis() - start));
        long arrStart = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            arrayList.add(new String("a" + i));
        }
        System.out.println("arrayList add time: " + (System.currentTimeMillis() - arrStart));
    }

    @Test
    public void forTest() {
        long arrStart = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            String s = arrayList.get(i);
        }
        System.out.println("arr for index time: " + (System.currentTimeMillis() - arrStart));

        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            String s = linkedList.get(i);
//            System.out.println("linkedList for: " + i + ": " + s);
        }
        System.out.println("linked for index time:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void iteratorTest() {
        long start = System.currentTimeMillis();
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
        }
        System.out.println("arrayList iterator time: " + (System.currentTimeMillis() - start));

        long linkedStart = System.currentTimeMillis();
        Iterator<String> linkedIt = linkedList.iterator();
        while (linkedIt.hasNext()) {
            String next = linkedIt.next();
        }
        System.out.println("linkedList iterator time: " + (System.currentTimeMillis() - linkedStart));
    }

    @Test
    public void linkedListTest() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("aaa");
//        String str = linkedList.get(1);
        Iterator<String> iterator = linkedList.iterator();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("hhh");
        Iterator<String> arrayListIterator = arrayList.iterator();
    }
}
