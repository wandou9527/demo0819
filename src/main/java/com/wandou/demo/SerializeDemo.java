package com.wandou.demo;

import com.wandou.model.Book;

import java.io.*;

/**
 * @author liming
 * @date 2020-06-08
 * @description
 */
public class SerializeDemo {

    private static File file = new File("src/main/resources/file/serialize.txt");

    public static void main(String[] args) throws Exception {
        serialize();
        deserialize();
    }

    public static void serialize() throws Exception {
        //序列化到指定的文本
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(new Book("Java从入门到精通", 4500L));
        oos.writeObject(new Book("MySQL从入门到精通", 5200L));
        oos.close();
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        //反序列化到指定的文本
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        //每次回读出一个对象
        Object obj = ois.readObject();
        Book book = (Book) obj;
        System.out.println("我们通过使用字节流的序列化操作，看到了如下信息内容：\n" + book);
        obj = ois.readObject();
        book = (Book) obj;
        System.out.println("我们通过使用字节流的序列化操作，看到了如下信息内容：\n" + book);
        ois.close();
    }

}

