package com.wandou.demo;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.sql.Array;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Demo {
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


    }

}
