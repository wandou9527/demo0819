package com.wandou.demo;

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

}
