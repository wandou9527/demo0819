package com.wandou.demo;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author liming
 * @date 2020-06-08
 * @description
 */
public class IODemo {

    private static File file = new File("/Users/liming/Documents/java/java_self/git_site_self/demo0819/src/main/resources/file/ioDemo.txt");
    private char[] chars = new char[]{'a', '1', '5', 'b', '9', '5', '6'};


    public static void main(String[] args) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        /**
         * 写字节 Writes the specified byte to this output stream.
         * 要写的字节是参数b的8个低阶比特。
         */
        outputStream.write(603);
        outputStream.write(604);
        outputStream.write(605);
        outputStream.write(606);
        outputStream.write(606);
        outputStream.write(607);
        outputStream.write(608);
        outputStream.write(609);

        outputStream.flush();
        outputStream.close();
    }

    @Test
    public void writeFile() throws IOException {
        String name = file.getName();
        System.out.println("name = " + name);
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write("啦啦啦啦😝😋\r\n");
        fileWriter.write(chars + "\r\n");
        fileWriter.flush();

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//        bufferedWriter.newLine();
        bufferedWriter.write("【bufferedWriter写的内容】\r\n");
        bufferedWriter.flush();

        bufferedWriter.close();
        fileWriter.close();
    }

    @Test
    public void readFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        int len = 33;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            int read = fileInputStream.read();
            System.out.println("read = " + read);
            bytes[i] = (byte) read;
        }
        String str = new String(bytes);
        System.out.println("str = " + str);

    }

    @Test
    public void readFileV2() throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        System.out.println("line = " + line);
        line = bufferedReader.readLine();
        System.out.println("line = " + line);
        line = bufferedReader.readLine();
        System.out.println("line = " + line);
        line = bufferedReader.readLine();
        System.out.println("line = " + line);
        line = bufferedReader.readLine();
        System.out.println("line = " + line);
    }

}
