package com.wandou.demo;

import org.junit.Test;

import java.util.*;

/**
 * @author liming
 * @date 2018/8/24 17:55
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

}
