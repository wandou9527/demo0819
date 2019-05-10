package com.wandou.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: liming
 * @date: 2019/5/10 15:05
 * @description:
 * @modify:
 */
public class DateUtil {

    //试验发生并发问题的情况
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd- hh:mm:ss");


    /**
     * 格式化
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        String formatStr = DateUtil.format.format(date);
        return formatStr;
    }

}
