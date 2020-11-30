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
        HashMap<String, Object> map = new HashMap(3);
        map.put("k-lala", "v-zzz");
        map.put("k-lala2", "v-zzz2");
        map.forEach((k, v) -> {
            System.out.println(k + ", " + v);
        });
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

    /**
     * 试验hashMap冲突的情况
     */
    @Test
    public void m5() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);

        List<String> keys = getSameIndexKeys(15);
        List<Integer> hashs = new ArrayList<>();
        for (String key : keys) {
            map.put(key, "value");
            //打印当前table的长度，看看是否扩容了
            System.out.println("map.size: " + map.size() + ", table长度: " + ((Object[]) tableField.get(map)).length);
            if (map.size() == 8) {
                break;
            }
        }

        Map<Integer, Integer> cardinalityMap = CollectionUtils.getCardinalityMap(hashs);
        System.out.println("cardinalityMap = " + cardinalityMap);

        Object[] table = (Object[]) tableField.get(map);
        System.out.println("map table: " + Arrays.toString(table));

        Set<Map.Entry> entrySet = map.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.println("entry: " + entry);
        }
    }

    /**
     * 为blog写的
     *
     * @param idx
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public List<String> getSameIndexKeys(int idx) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList();
        HashMap hashMap = new HashMap();
        int tableSize = 16;
        for (int i = 0; i < 1000; i++) {
            String key = "a" + i;
            //hash值 jdk的计算方式
            Method hashMethod = HashMap.class.getDeclaredMethod("hash", Object.class);
            hashMethod.setAccessible(true);
            int hash = (int) hashMethod.invoke(hashMap, key);
            int index = ((tableSize - 1) & hash);
            if (index == idx) {
                list.add(key);
            }
        }
        return list;
    }

    /**
     * 为blog写的
     *
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Test
    public void testReSize() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<String> keys = getSameIndexKeys(3);
        Field tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);
        HashMap<String, String> hashMap = new HashMap();
        for (String key : keys) {
            hashMap.put(key, "value");
            //打印当前table的长度，看看是否扩容了
            System.out.println("map.size: " + hashMap.size() + ", table长度: " + ((Object[]) tableField.get(hashMap)).length);
        }
    }

    @Test
    public void m6LinkedHashMap() throws NoSuchFieldException, IllegalAccessException {
        /**
         * LinkedHashMap 继承 HashMap，put 实际是调用的 HashMap.put()，
         * 但LinkedHashMap 重写了 HashMap#afterNodeInsertion(boolean)，做了清除最不常用数据的处理。
         * LinkedHashMap 的链表结构是通过 newNode() 方法实现的。
         */
        Field headField = LinkedHashMap.class.getDeclaredField("head");
        headField.setAccessible(true);
        Field tailField = LinkedHashMap.class.getDeclaredField("tail");
        tailField.setAccessible(true);

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put("a", "av");
        Object head = headField.get(linkedHashMap);
        System.out.println("head = " + head);
        Object tail = tailField.get(linkedHashMap);
        System.out.println("tail = " + tail);

        linkedHashMap.put("b", "bv");
        head = headField.get(linkedHashMap);
        System.out.println("head = " + head);
        tail = tailField.get(linkedHashMap);
        System.out.println("tail = " + tail);

        linkedHashMap.put("c", "cv");
        head = headField.get(linkedHashMap);
        System.out.println("head = " + head);
        tail = tailField.get(linkedHashMap);
        System.out.println("tail = " + tail);

        linkedHashMap.put("z", "zv");
        head = headField.get(linkedHashMap);
        System.out.println("head = " + head);
        tail = tailField.get(linkedHashMap);
        System.out.println("tail = " + tail);

        linkedHashMap.put("d", "dv");
        head = headField.get(linkedHashMap);
        System.out.println("head = " + head);
        tail = tailField.get(linkedHashMap);
        System.out.println("tail = " + tail);
    }

}
