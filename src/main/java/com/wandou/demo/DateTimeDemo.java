package com.wandou.demo;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import sun.util.calendar.BaseCalendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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

    /**
     * joda
     */
    @Test
    public void m1() {
        //org.joda.time.DateTime
        DateTime dateTime = new DateTime(2018, 9, 15, 12, 0, 0);
        DateTimeZone zone = dateTime.getZone();
        System.out.println(zone);
        System.out.println(dateTime.toString("yyyy-MM-dd"));

        DateTime dateTime1 = dateTime.plusDays(2);
        System.out.println(dateTime1.toString("yyyy-MM-dd"));

        DateTime dateTime2 = DateTime.now(DateTimeZone.UTC);
        System.out.println(dateTime2);
        System.out.println("dateTime2.getZone(): " + dateTime2.getZone());

        TimeZone aDefault = TimeZone.getDefault();
        System.out.println(aDefault);
    }

    /**
     * org.apache.commons.lang3.time.DateUtils
     * getFragmentInMilliseconds()
     */
    @Test
    public void m2() {
//        Locale locale = new DateTimeFormatter().getLocale();
//        System.out.println(locale);

        long timeUndefined = BaseCalendar.Date.TIME_UNDEFINED;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Locale locale = dateTimeFormatter.getLocale();
        System.out.println(locale);//zh_CN
        System.out.println(locale.getDisplayCountry());//中国
        System.out.println(locale.getDisplayLanguage());//中文
        System.out.println(locale.getDisplayName());//中文 (中国)
        System.out.println(locale.getDisplayScript());
        System.out.println(locale.getDisplayVariant());

        long currentTimeMillis = DateTimeUtils.currentTimeMillis();

        Date addYears = DateUtils.addYears(new Date(), 1);
        System.out.println(addYears);

        DateTime dateTime = new DateTime(2018, 9, 15, 12, 7, 9, 8);
        Date date = dateTime.toDate();
        System.out.println(DateUtils.getFragmentInMilliseconds(date, Calendar.MILLISECOND));
        System.out.println(DateUtils.getFragmentInMilliseconds(date, Calendar.SECOND));//对秒取余，剩余时间碎片，返回单位为毫秒
        System.out.println(DateUtils.getFragmentInMilliseconds(date, Calendar.MINUTE));//
//        System.out.println(DateUtils.getFragmentInMilliseconds(date, Calendar.HOUR));//java.lang.IllegalArgumentException: The fragment 10 is not supported
        System.out.println(DateUtils.getFragmentInMilliseconds(date, Calendar.HOUR_OF_DAY));//
        System.out.println(DateUtils.getFragmentInMilliseconds(date, Calendar.YEAR));//

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2);
        calendar.add(Calendar.MONTH, 2);
        System.out.println(calendar);
        System.out.println(DateFormatUtils.format(calendar, "yyyy-MM"));

    }

    /**
     * jdk8 datetime
     */
    @Test
    public void m3() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("localDateTime.plusMonths(3) = " + localDateTime.plusMonths(3));
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("localDateTime.format(DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\")) = " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("localDateTime = " + localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.now();

    }
}
