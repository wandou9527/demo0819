package com.wandou.demo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

public class CollectionDemo {
    public static void main(String[] args) {
        boolean b = ifStr("22");
        System.out.println(b);
        m2();
    }

    public static boolean ifStr(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static void m2() {
        Map map = new LinkedHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(new Random().nextInt(), new Random().nextInt());
        }
        System.out.println(map);
    }

    /**
     * jia shang 2018年8月19日
     */

    /**
     * https://blog.csdn.net/tiwerbao/article/details/42836305 apache common
     */
    @Test
    public void m3() {
        int[] arr1 = {1, 5, 8};
        int[] arr2 = {5, 8, 1};
        boolean equals = Arrays.equals(arr1, arr2);
        System.out.println(equals);

        boolean equals1 = ArrayUtils.isEquals(arr1, arr2);
        System.out.println(equals1);

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(2, 3, 1);
        boolean equalCollection = CollectionUtils.isEqualCollection(list1, list2);
        System.out.println("CollectionUtils.isEqualCollection(list1, list2)? " + equalCollection);
        //全包含式的判断
        boolean boo = list1.containsAll(list2) && list2.containsAll(list1);
        System.out.println("全包含式的判断: " + boo);

//        List<Integer> list1 = Arrays.asList(1, 2, 3, 3);
//        List<Integer> list2 = Arrays.asList(2, 3, 1);
//        boolean equalCollection = CollectionUtils.isEqualCollection(list1, list2);
//        System.out.println("isEqualCollection? " + equalCollection);
//
//        boolean boo = list1.containsAll(list2) && list2.containsAll(list1);
//        System.out.println("boo: " + boo);


        Set set1 = new HashSet();
        Set set2 = new HashSet();
        set1.add(1);
        set1.add(4);
        set1.add(9);
        set2.add(4);
        set2.add(1);
        set2.add(9);
        System.out.println("isEqualCollection(set1, set2)? " + CollectionUtils.isEqualCollection(set1, set2));

//        System.out.println("CollectionUtils.isEqualCollection(list1, list2): " + CollectionUtils.isEqualCollection(list1, list2));


    }

    @Test
    public void m4List() {
        final List<Integer> list = new ArrayList();
        list.add(6);
        list.add(9);
        list.add(77);

        for (Integer i : list) {
            System.out.println(i);
        }

//        list.stream().forEach( i () -> { System.out.println(i)});
    }

}