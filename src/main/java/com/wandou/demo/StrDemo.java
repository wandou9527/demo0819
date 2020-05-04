package com.wandou.demo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.*;

public class StrDemo {
    /**
     * @date 2018-8-21
     * https://www.cnblogs.com/deltadeblog/p/7594038.html
     */
    @Test
    public void m1() {
        String s1 = "haha";
        String s2 = "haha";
        System.out.println("s1 == s2 ? " + (s1 == s2));

        //--------------------
        String s3 = new String("aa");
        String s4 = new String("aa");
        System.out.println("s3 == s4 ? " + (s3 == s4));
        System.out.println("s3.equals(s4) ? " + s3.equals(s4));

        String s5 = "HelloJava";
        String s6 = "Hello" + "Java";
        System.out.println("s5 == s6 ? " + (s5 == s6));

        String s7 = "Hello";
        String s8 = "Java";
        String s9 = s7 + s8;
        System.out.println("s5 == s9 ? " + (s5 == s9));//false

        final String s10 = "Hello";
        final String s11 = "Java";
        String s12 = s10 + s11;
        System.out.println("s5 == s12 ? " + (s5 == s12));//true
        //这里s10与s11位由final修饰为宏变量，编译器会把程序中所有用到该变量的地方直接替换成该变量的值，故s12编译时就确定了。指向常量池中的对象。

        String s13 = s5;
        System.out.println("s5 == s13 ? " + (s5 == s13));
    }

    @Test
    public void m2() throws UnsupportedEncodingException {
        String localChartSet = System.getProperty("file.encoding");
        System.out.println("localChartSet>>>>" + localChartSet);   //查看本地默认字符集

        String str = "str";
        byte[] bytes1 = str.getBytes("utf-8");

        for (int i = 0; i < bytes1.length; i++) {
            System.out.println("#" + i + ": " + (char) bytes1[i]);
            System.out.println("#: " + bytes1[i]);
        }

        byte[] bytes = DigestUtils.md5Digest(bytes1);//返回的为什么编码？直接转应该走本地编码即utf-8
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("##" + i + ": " + bytes[i]);
            System.out.println("##: " + bytes[i]);
        }
        System.out.println(new String(bytes, "iso-8859-1"));//java 默认使用 iso8859-1
        System.out.println(new String(bytes, "utf-8"));
        System.out.println(new String(bytes, "utf-16"));
        System.out.println(new String(bytes, "gbk"));
        System.out.println(new String(bytes, "gb2312"));
        System.out.println(new String(bytes, "ASCII"));

        String base64ToStr = Base64.getEncoder().encodeToString(bytes);
        System.out.println("base64ToStr: " + base64ToStr);

        String md5DigestAsHex = DigestUtils.md5DigestAsHex(bytes1);
        System.out.println("md5DigestAsHex: " + md5DigestAsHex);
    }

    @Test
    public void m3() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(4).append(3).append(9);
        System.out.println(stringBuffer);
        System.out.println(stringBuffer.reverse());

        String yyyyMMddHHmmss = DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS", TimeZone.getTimeZone("Asia/Shanghai"));
//        yyyyMMddHHmmss = yyyyMMddHHmmss.substring(2);
        StringBuffer stringBuffer1 = new StringBuffer(yyyyMMddHHmmss);
        System.out.println(yyyyMMddHHmmss);
        stringBuffer1.insert(1, "01");
        stringBuffer1.append(new Random().nextInt(10));
        System.out.println(stringBuffer1);
    }

    @Test
    public void m4() {
        String substring = StringUtils.substring("2019-05-29 12:00:00", 0, 10);
        System.out.println(substring); // 2019-05-29
    }

    @Test
    public void m5Format() {
        String format = MessageFormat.format("a{0}a{1}a", "0", 1);
        System.out.println(format); // 结果 a0a1a

        String format1 = String.format("a %s ap %d qq", 1, 2);
        System.out.println(format1);

        //%s 会将参数 toString 或 String.valueOf
        //结果 a2 null ap  qq
        System.out.println(String.format("a2 %s ap %s qq", null, ""));

        Calendar c = Calendar.getInstance();
        String s = String.format("Duke's Birthday: %1$tm %1$te,%1$tY", c);
        System.out.println("s = " + s);

        System.out.println(String.format("b f b: %%", 12));
    }

    /**
     * 反转
     */
    @Test
    public void m6ReverseStr() {
        String str = "abc123";
        StringBuilder sb = new StringBuilder(str);
        System.out.println("sb.reverse().toString() = " + sb.reverse().toString());

        int length = str.length();
        char[] arr = new char[length];
        for (int i = 0; i < length; i++) {
            arr[i] = str.charAt(length - 1 - i);
        }
        System.out.println("new String(arr) = " + new String(arr));
    }
}
