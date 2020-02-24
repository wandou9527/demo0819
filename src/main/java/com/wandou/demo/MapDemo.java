package com.wandou.demo;

import com.wandou.model.Book;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liming
 * @date 2018/11/07
 * @description
 */
public class MapDemo {

    @Test
    public void m1() {
        SortedMap map = new TreeMap();
        map.put(5, 6);
        map.put(2, 6);
        map.put(4, 6);
        System.out.println(map);
    }

    @Test
    public void m2() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Object put = concurrentHashMap.put("lala", "vvv");
        System.out.println(put);
        concurrentHashMap.size();
    }

    public void hashMapTest() {
        HashMap map = new HashMap();
        map.put("k-lala", "v-zzz");
    }

    /**
     * 对象已经add到list中，再put到map中 map中对象进行修改，list中对象也会变化。
     */
    @Test
    public void m3() {
        Book book = new Book();
        book.setName("三国");
        book.setPrice(1099L);
        ArrayList<Book> arrayList = new ArrayList<>();
        arrayList.add(book);
        System.out.println("arrayList = " + arrayList);

        HashMap<Integer, Book> hashMap = new HashMap<>(4);
        hashMap.put(1, book);
        System.out.println("hashMap = " + hashMap);
        hashMap.get(1).setName("三国-map改");

        System.out.println("arrayList = " + arrayList);
        System.out.println("hashMap = " + hashMap);

    }

}
