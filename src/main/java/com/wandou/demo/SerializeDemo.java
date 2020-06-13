package com.wandou.demo;

import com.wandou.model.Book;

import java.io.*;

/**
 * @author liming
 * @date 2020-06-08
 * @description
 */
public class SerializeDemo {

    private static File file = new File("/Users/liming/Documents/java/java_self/git_site_self/demo0819/src/main/resources/file/serialize.txt");

    public static void main(String[] args) throws Exception, IOException {
        serialize();
        deserialize();
    }

    public static void serialize() throws Exception {
        //序列化到指定的文本
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(new Book("Java开发", 4500L));
        oos.close();
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        //反序列化到指定的文本
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object obj = ois.readObject();
        Book book = (Book) obj;
        System.out.println("我们通过使用字节流的序列化操作，看到了如下信息内容：\n" + book);
        ois.close();
    }

}

