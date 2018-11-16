package com.wandou.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5Util {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};
    private static char hexDigitsUp[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public String pMd5(Object[] object) {
        String result = "";
        StringBuffer param = new StringBuffer();
        if (object != null) {
            for (Object o : object) {
                param.append(o);
            }
        } else {
            return result;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            md5.update((param.toString()).getBytes("UTF-8"));
            byte b[] = md5.digest();

            int i;
            StringBuffer buf = new StringBuffer("");

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (Exception e) {
            System.out.println("去哪网请求MD5加密失败！");
            e.printStackTrace();
        }
        return result;
    }
    //金融卡券加密

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     *
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 从mall复制过来，post100用的
     * 跟工具库的一样
     *
     * @param s
     * @return
     */
    public final String post100Encode(String s) {
        try {
            MessageDigest md5Instance = MessageDigest.getInstance("MD5");
            byte[] btInput = s.getBytes();
            // 使用指定的字节更新摘要
            md5Instance.update(btInput);
            // 获得密文
            byte[] md = md5Instance.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigitsUp[byte0 >>> 4 & 0xf];
                str[k++] = hexDigitsUp[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void m1() throws UnsupportedEncodingException {
        String str = "str";

        String str1 = post100Encode(str);
        System.out.println("post100Encode: " + str1);

        String str2 = org.springframework.util.DigestUtils.md5DigestAsHex(str.getBytes());
        System.out.println("DigestUtils: " + str2);

        String str3 = pMd5(new Object[]{str});
        System.out.println("ctripMd5: " + str3);

        String str4 = MD5Encode(str);
        System.out.println("MD5Encode: " + str4);
        //TODO 四种都是一样的结果
    }

}
