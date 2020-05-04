package com.wandou.demo;

import com.alibaba.fastjson.JSON;
import com.wandou.model.Book;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liming
 * @date 2018/11/07
 * @description
 */
public class MapDemo {

    private Map map = new HashMap();


    @Test
    public void m1() {
        SortedMap map = new TreeMap();
        map.put(5, 6);
        map.put(2, 6);
        map.put(4, 6);
        System.out.println(map);
    }

    @Test
    public void m2() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Object put = concurrentHashMap.put("lala", "vvv");
        Object put2 = concurrentHashMap.put("lala", "vvv2");
        System.out.println(put);
        System.out.println("put2 = " + put2);
        System.out.println("concurrentHashMap = " + concurrentHashMap);
        System.out.println("concurrentHashMap.size() = " + concurrentHashMap.size());
    }

    @Test
    public void hashMapTest() {
        HashMap map = new HashMap(3);
        map.put("k-lala", "v-zzz");
    }

    /**
     * 对象已经add到list中，再put到map中 map中对象进行修改，list中对象也会变化。
     */
    @Test
    public void m3() {
        Book book = new Book();
        book.setName("三国");
        book.setPrice(1099L);
        ArrayList<Book> arrayList = new ArrayList<>();
        arrayList.add(book);
        System.out.println("arrayList = " + arrayList);

        HashMap<Integer, Book> hashMap = new HashMap<>(4);
        hashMap.put(1, book);
        System.out.println("hashMap = " + hashMap);
        hashMap.get(1).setName("三国-map改");

        System.out.println("arrayList = " + arrayList);
        System.out.println("hashMap = " + hashMap);

    }

    public ArrayList<String> print() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<String> list = new ArrayList();
        HashMap state = new HashMap();
        int index_10_count = 0;

        int tableSize = 16;
        for (int i = 0; i < 1000; i++) {
            String key = "a" + i;

            //hash值 jdk的计算方式
            Method hashMethod = HashMap.class.getDeclaredMethod("hash", Object.class);
            int hash = (int) hashMethod.invoke(map, key);
            int index = ((tableSize - 1) & hash);
            if (index <= 15) {
                list.add(key);
            }
        }

        return list;
    }

    /**
     * 试验hashMap冲突的情况
     */
    @Test
    public void m5() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);

//        ArrayList<String> keys = print();

        ArrayList<String> keys = new ArrayList();
        List<Integer> hashs = new ArrayList<>();

        int tableSize = 16;
        for (int i = 0; i < 1000; i++) {
            String key = "a" + i;

            //hash值 jdk的计算方式
            Method hashMethod = HashMap.class.getDeclaredMethod("hash", Object.class);
            hashMethod.setAccessible(true);
            int hash = (int) hashMethod.invoke(map, key);
            int index = ((tableSize - 1) & hash);
            if (index == 15) {
                System.out.println("keyHash: " + hash);
                keys.add(key);
                hashs.add(hash);
            }
        }


        for (String key : keys) {
            map.put(key, "1");
            //打印当前table的长度，看看是否扩容了
            System.out.println("map.size: " + map.size() + ", table长度: " + ((Object[]) tableField.get(map)).length);
//            System.out.println("map.table: " + ((Object[]) tableField.get(map)));
            if (map.size() == 8) {
                break;
            }
        }

        Map<Integer, Integer> cardinalityMap = CollectionUtils.getCardinalityMap(hashs);
        System.out.println("cardinalityMap = " + cardinalityMap);

        for (Object obj : ((Object[]) tableField.get(map))) {
            System.out.println("mapTable中的元素: " + JSON.toJSONString(obj));
        }

        Set<Map.Entry> entrySet = map.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.println(entry);
        }

    }

}
