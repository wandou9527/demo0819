package com.wandou.demo.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @author liming
 * @date 2020/11/1
 * @description 381. O(1) 时间插入、删除和获取随机元素 - 允许重复 【困难】
 */
public class L381_InsertDeleteGetrandomO1DuplicatesAllowed {

    private List<Integer> data = new ArrayList<>();
    private Map<Integer, Set<Integer>> idxsMap = new HashMap<>();
    private Random random = new Random();

    /**
     * Initialize your data structure here.
     */
    public L381_InsertDeleteGetrandomO1DuplicatesAllowed() {

    }

    @Test
    public void test() {
        // 初始化一个空的集合。
        L381_InsertDeleteGetrandomO1DuplicatesAllowed collection = new L381_InsertDeleteGetrandomO1DuplicatesAllowed();

        // 向集合中插入 1 。返回 true 表示集合不包含 1 。
        boolean insert = collection.insert(1);
        System.out.println("向集合中插入 1 。返回 true 表示集合不包含 1 。: " + insert);

        // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
        boolean insert1 = collection.insert(1);
        System.out.println("向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。: " + insert1);

        // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
        boolean insert2 = collection.insert(2);
        System.out.println("向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。: " + insert2);

        // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
        int random = collection.getRandom();
        System.out.println("getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。: " + random);

        // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
        boolean remove = collection.remove(1);
        System.out.println("从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。: " + remove);

        // getRandom 应有相同概率返回 1 和 2 。
        int random1 = collection.getRandom();
        System.out.println("getRandom 应有相同概率返回 1 和 2 。: " + random1);

    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        data.add(val);
        Set<Integer> idxs = this.idxsMap.getOrDefault(val, new HashSet<>());
        boolean flag = idxs.isEmpty();
        idxs.add(data.size() - 1);
        idxsMap.put(val, idxs);
        return flag;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    // 参考官方，貌似问题出在 1
    public boolean removeV2(int val) {
        if (!idxsMap.containsKey(val)) {
            return false;
        }
        Iterator<Integer> it = idxsMap.get(val).iterator();
        int i = it.next();
        int lastNum = data.get(data.size() - 1);
        data.set(i, lastNum);
        idxsMap.get(val).remove(i);
        idxsMap.get(lastNum).remove(data.size() - 1);
        if (i < data.size() - 1) {
            idxsMap.get(lastNum).add(i);
        }
        //[1]
        if (idxsMap.get(val).size() == 0) {
            idxsMap.remove(val);
        }
        data.remove(data.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        Set<Integer> idxs = idxsMap.get(val);
        if (idxs == null || idxs.isEmpty()) {
            return false;
        }
        int size = data.size();
        Iterator<Integer> iterator = idxs.iterator();
        Integer idx = iterator.next();
        Integer tail = data.get(size - 1);
        data.set(idx, tail);

        idxs.remove(idx);
        data.remove(size - 1);
        if (idx < (size - 1)) {
            idxsMap.get(tail).add(idx);
        }
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return data.get(random.nextInt(data.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
