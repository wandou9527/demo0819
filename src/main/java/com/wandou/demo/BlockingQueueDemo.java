package com.wandou.demo;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liming
 * @date 2020/11/22
 * @description ArrayBlockingQueue、LinkedBlockingQueue 研究
 * LinkedBlockingQueue
 * head 节点是不变的，而且item不变且为null，初始化时head=last=new Node(); 之后head就不变了
 * <p>
 * 与arrayQueue的区别：array的初始化即开辟数组空间，对空间稍不友好，但增删的时间较快。
 * linked初始几乎不占用数据空间，仅初始化一个head，后续操作时将申请空间，空间友好，时间效率比array稍差。
 */
public class BlockingQueueDemo {

    @Test
    public void testLinkedBlockingQueue() {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
        for (int i = 0; i < 11; i++) {
            System.out.println(i);

            /**
             * LinkedBlockingQueue#offer(java.lang.Object)
             * 向队列加元素，当队列不满添加成功返回true；队列满添加失败将返回false；
             */
            boolean offer = linkedBlockingQueue.offer(i);
            System.out.println("i: " + i + ", offer的返回值: " + offer);

            /**
             * add() 向队列加元素，当队列满将抛出异常
             */
            try {
                linkedBlockingQueue.add(i);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                /**
                 * 将指定的元素插入此队列的尾部，必要时等待可用的空间(即队列为满时将阻塞等待)。
                 */
                linkedBlockingQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testArrayBlockingQueue() {
        BlockingQueue<Integer> arrBlockingQueue = new ArrayBlockingQueue<>(10);
    }

}
