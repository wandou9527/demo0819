package com.wandou.demo;

import com.alibaba.fastjson.JSON;
import com.wandou.demo.thread.ThreadDemo;
import com.wandou.model.dto.MemberPrivilegeDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 回滚revert之后有个空三角形
 */

public class CollectionDemo {
    private final ArrayList<Long> threadUnsafeList = new ArrayList<>();

    public static void main(String[] args) {
        boolean b = ifStr("22");
        System.out.println(b);
        m2();
    }

    public static boolean ifStr(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static void m2() {
        Map map = new LinkedHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(new Random().nextInt(), new Random().nextInt());
        }
        System.out.println(map);
    }

    /**
     * jia shang 2018年8月19日
     */

    /**
     * https://blog.csdn.net/tiwerbao/article/details/42836305 apache common
     */
    @Test
    public void m3() {
        int[] arr1 = {1, 5, 8};
        int[] arr2 = {5, 8, 1};
        boolean equals = Arrays.equals(arr1, arr2);
        System.out.println(equals);

        boolean equals1 = ArrayUtils.isEquals(arr1, arr2);
        System.out.println(equals1);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 1, 1);
        List<Integer> list2 = Arrays.asList(2, 3, 1, 1, 2);
        boolean equalCollection = CollectionUtils.isEqualCollection(list1, list2);
        System.out.println("CollectionUtils.isEqualCollection(list1, list2)? " + equalCollection);//false
        //全包含式的判断 证明不严谨, 【1 1 2】【1 2 2】情况
        boolean boo = list1.containsAll(list2) && list2.containsAll(list1);
        System.out.println("全包含式的判断 list1 list2: " + boo);//true

        Set set1 = new HashSet();
        set1.add(1);
        set1.add(4);
        set1.add(9);
        Set set2 = new HashSet();
        set2.add(4);
        set2.add(1);
        set2.add(9);
        System.out.println("isEqualCollection(set1, set2)? " + CollectionUtils.isEqualCollection(set1, set2));//true

        Map<Integer, Integer> list1CardinalityMap = CollectionUtils.getCardinalityMap(list1);
        System.out.println("list1CardinalityMap: " + list1CardinalityMap);


    }

    @Test
    public void m4ListContains() {
        final List<Integer> list = new ArrayList();
        list.add(6);
        list.add(9);
        list.add(77);
        list.add(77);

        final List<Integer> list2 = new ArrayList();
        list2.add(6);
        list2.add(77);
        list2.add(9);
        list2.add(9);

        System.out.println(list.containsAll(list2));
        System.out.println(list2.containsAll(list));

        Collection<Integer> union = CollectionUtils.union(list, list2);
        System.out.println(union);

    }

    /**
     * ArrayList线程不安全的表现 https://blog.csdn.net/toocruel/article/details/82753615
     */
    @Test
    public void m5ListThreadUnsafe() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        long currentTimeMillis = System.currentTimeMillis();
                        try {
                            threadUnsafeList.add(currentTimeMillis);
                        } catch (Exception e) {
                            System.out.print("f");
                            //java.lang.ArrayIndexOutOfBoundsException
                            //因为两个线程来，都判断了size不需扩容，当仅离扩容阈值差1的时候，一个线程add后完成，另一线程add就会数组越界
                            System.err.println("异常了 " + currentTimeMillis);
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        Thread.sleep(5000L);
        int size = threadUnsafeList.size();
        System.out.println("threadUnsafeList size: " + size);
        for (int i = 0; i < size; i++) {
            System.out.println(threadUnsafeList.get(i));
        }
    }

    @Test
    public void m6UseTool() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("run 方法内线程 " + Thread.currentThread().getName() + " 准备就绪 ");
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println("run 方法内线程 " + Thread.currentThread().getName() + " 开始执行 ");
                    long currentTimeMillis = System.currentTimeMillis();
                    try {
                        threadUnsafeList.add(currentTimeMillis);
                    } catch (Exception e) {
                        System.out.print("ffff");
                        System.err.println("异常了 " + currentTimeMillis);
                        e.printStackTrace();
                    }
                }
            }
        };
        new ThreadDemo().threadToolMethod(10, 10, runnable, countDownLatch);
        Thread.sleep(5000L);
        int size = threadUnsafeList.size();
        System.out.println("threadUnsafeList size: " + size);
        for (int i = 0; i < size; i++) {
            System.out.println(threadUnsafeList.get(i));
        }
    }

    /**
     * 队列
     */
    @Test
    public void m7() throws InterruptedException {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(2);
        linkedBlockingQueue.offer(1);
        linkedBlockingQueue.offer(2);
        boolean offer = linkedBlockingQueue.offer(3);
        System.out.println("offer 添加返回 Boolean " + offer);
        System.out.println("队列 " + linkedBlockingQueue);
//        linkedBlockingQueue.add(4);
        Object poll = linkedBlockingQueue.poll();
        System.out.println("poll 取出 " + poll);

        Object peek = linkedBlockingQueue.peek();
        System.out.println("peek 取出 " + peek);

        Object poll1 = linkedBlockingQueue.poll();
        System.out.println("poll1 取出 " + poll1);
        // take取出方法会阻塞
//        Object take = linkedBlockingQueue.take();
//        System.out.println("take 取出 " + take);
        System.out.println("队列 " + linkedBlockingQueue);
        System.out.println("-----------------------");

        //----------- 优先级队列 -----------------

        PriorityQueue<Object> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(44);
        priorityQueue.add(20);
        priorityQueue.add(45);
        priorityQueue.add(46);
        Object peek1 = priorityQueue.peek();
        System.out.println("peek1 = " + peek1);
        System.out.println("priorityQueue = " + priorityQueue);
        Object poll2 = priorityQueue.poll();
        System.out.println("poll2 = " + poll2);
        System.out.println("priorityQueue = " + priorityQueue);
    }

    @Test
    public void m8Sort() {
        long id = 0L;
        MemberPrivilegeDTO memberPrivilegeDTO = MemberPrivilegeDTO.builder()
                .id(++id)
                .isReceive(0)
                .effectStart(new Date(10))
                .build();
        MemberPrivilegeDTO memberPrivilegeDTO1 = MemberPrivilegeDTO.builder()
                .id(++id)
                .isReceive(1)
                .effectStart(new Date(9))
                .build();
        MemberPrivilegeDTO memberPrivilegeDTO2 = MemberPrivilegeDTO.builder()
                .id(++id)
                .isReceive(0)
                .effectStart(new Date(5))
                .build();

        List<MemberPrivilegeDTO> list = new ArrayList();
        list.add(memberPrivilegeDTO);
        list.add(memberPrivilegeDTO1);
        list.add(memberPrivilegeDTO2);

        List<MemberPrivilegeDTO> resultList = list.stream()
                .sorted(Comparator.comparing(MemberPrivilegeDTO::getIsReceive).thenComparing(MemberPrivilegeDTO::getEffectStart))
                .collect(Collectors.toList());

        System.out.println("list = " + JSON.toJSONString(list, true));
        System.out.println("resultList = " + JSON.toJSONString(resultList, true));

        Stream<MemberPrivilegeDTO> listStream = list.stream();
//        List<MemberPrivilegeDTO> tmpList = listStream.filter(m -> m.getIsReceive() == 1).collect(Collectors.toList());
//        List<MemberPrivilegeDTO> tmpList1 = listStream.filter(m -> m.getIsReceive() != 1).collect(Collectors.toList());

//        List<MemberPrivilegeDTO> resultList1 = Stream.concat(tmpList.stream(), tmpList1.stream()).collect(Collectors.toList());
//        System.out.println("resultList1 = " + JSON.toJSONString(resultList1, true));

        Stream<MemberPrivilegeDTO> tmpStream = listStream.filter(m -> m.getIsReceive() == 1);
        Stream<MemberPrivilegeDTO> tmpStream1 = listStream.filter(m -> m.getIsReceive() != 1);
        List<MemberPrivilegeDTO> resultList1 = Stream.concat(tmpStream, tmpStream1).collect(Collectors.toList());
        System.out.println("resultList1 = " + JSON.toJSONString(resultList1, true));

    }

    /**
     * DelayQueue
     */
    @Test
    public void m9DelayQueue() {
        DelayQueue delayQueue = new DelayQueue<>();
        Delayed delayed = new Delayed() {
            @Override
            public long getDelay(TimeUnit unit) {
                return 0;
            }

            @Override
            public int compareTo(Delayed o) {
                return 0;
            }
        };

        boolean add = delayQueue.add(delayed);

        System.out.println("add = " + add);
        System.out.println("delayQueue = " + delayQueue);
        Delayed poll = delayQueue.poll();
        System.out.println("poll = " + poll);
    }

}
