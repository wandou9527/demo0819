package com.wandou.demo;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.TimeZone;

/**
 * @author liming
 * @date 2018/11/5 10:43
 * @description
 * @modify
 */
public class DateTimeDemo {

    @Test
    public void m5TimeZone() {
        TimeZone defaultTimeZone = TimeZone.getDefault();
        System.out.println(defaultTimeZone);
        System.out.println(defaultTimeZone.getClass());

        String[] availableIDs = TimeZone.getAvailableIDs();
        System.out.println(availableIDs);
        for (String str : availableIDs) {
            System.out.println(str);
        }
    }

    @Test
    public void m1() {
        DateTime dateTime = new DateTime(2018, 9, 15, 12, 00, 00);
        DateTimeZone zone = dateTime.getZone();

        System.out.println(dateTime.toString("yyyy-MM-dd"));
        System.out.println(zone);

        DateTime dateTime1 = dateTime.plusDays(2);
        System.out.println(dateTime1.toString("yyyy-MM-dd"));

    }
}
