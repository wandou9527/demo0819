package com.wandou.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author liming
 * @date 2020-05-17
 * @description
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (!serverSocket.isClosed()) {
            Socket request = serverSocket.accept();
            System.out.println("收到新链接：" + request.toString());
            try {
                //接收数据 打印
                InputStream inputStream = request.getInputStream(); //net + i/o
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String msg;
                while ((msg = reader.readLine()) != null) {
                    if (msg.length() == 0) {
                        break;
                    }
                    System.out.println(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    request.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
