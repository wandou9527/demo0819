package com.wandou.demo.bio;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author liming
 * @date 2020-05-17
 * @description
 */
public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 8080);
        OutputStream out = s.getOutputStream();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
//        String msg = scanner.nextLine();
        String msg = "psvm 我是个粉刷匠" + RandomStringUtils.randomNumeric(4);
        out.write(msg.getBytes(StandardCharsets.UTF_8));
        scanner.close();
        out.close();
    }
}
