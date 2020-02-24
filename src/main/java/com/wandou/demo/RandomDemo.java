package com.wandou.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class RandomDemo {

    @Test
    public void m1() {
        for (int i = 0; i < 20; i++) {
            System.out.println("RandomUtils.nextLong() = " + RandomUtils.nextLong());
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("RandomStringUtils.randomNumeric(6) = " + RandomStringUtils.randomNumeric(6));
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.println("RandomStringUtils.randomAscii(6) = " + RandomStringUtils.randomAscii(6));
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("RandomStringUtils.randomAlphabetic(6) = " + RandomStringUtils.randomAlphabetic(6));
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("RandomStringUtils.randomAlphanumeric(6) = " + RandomStringUtils.randomAlphanumeric(6));
        }
        System.out.println();
    }


    @Test
    public void m2() {
        Map<Integer, String> map = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(new Random().nextInt(), RandomStringUtils.randomNumeric(8));
        }
        System.out.println(map);
    }

}
