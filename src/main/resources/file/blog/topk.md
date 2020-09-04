在工作中我们常遇到此类问题，从一个大量甚至海量的数据中取出前几个大的数。必须在海量的文章中取出点击量最大的10篇文章。
此类问题其实就是Top K问题。
给定一个数据（数据量海量 N），想找到前 K 个最大的或最小的元素。

eg：有10亿个Long型整数，如果找出其中最大的10个？
最容易想到的方法是将数据全部排序，然后在排序后的集合中进行查找，最快的排序算法的时间复杂度一般为O（nlogn），如快速排序。每个Long类型占8个字节，10亿个数就要占用7GB+的存储空间，对于一些可用内存小于7GB的计算机而言，很显然是不能一次将全部数据读入内存进行排序的。其实即使内存能够满足要求（我机器内存都是8GB），该方法也并不高效，因为题目的目的是寻找出最大的10个数即可，而排序却是将所有的元素都排序了，做了很多的无用功。

第二种方法采用最小堆。首先读入前10个数来创建大小为10的最小堆，建堆的时间复杂度为O（mlogm）（m为数组的大小即为10），然后遍历后续的数字，并于堆顶（最小）数字进行比较。如果比最小的数小，则继续读取后续数字；如果比堆顶数字大，则替换堆顶元素并重新调整堆为最小堆。整个过程直至10亿个数全部遍历完为止。然后按照中序遍历的方式输出当前堆中的所有10个数字。该算法的时间复杂度为O（nmlogm），空间复杂度是10（常数）。
这种方式Java中有现成的数据结构优先级队列可以使用：`java.util.PriorityQueue`
代码如下：

```java
import org.junit.Test;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author liming
 * @date 2020/9/3
 * @description
 */
public class TopKDemo {

    private final File file = new File("file" + File.separator + "topkdata.txt");
    private final Random random = new Random();
    private final PriorityQueue<Long> priorityQueue = new PriorityQueue<>(10);

    @Test
    public void computeTopK() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                addToTopKQueue(Long.valueOf(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Long.MAX_VALUE = " + Long.MAX_VALUE);
            Long target;
            while ((target = priorityQueue.poll()) != null) {
                System.out.println("target = " + target);
            }
        }
    }

    /**
     * init方法仅运行一次即可，是为准备模拟数据
     */
    @Test
    public void init() {
        long start = System.currentTimeMillis();
        System.out.println("init");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            //先用100万数据，多了电脑可能受不了
            for (int i = 0; i < 1000000; i++) {
                fileWriter.write(String.valueOf(random.nextLong()) + System.lineSeparator());
            }
            //写入10个接近long的最大值的数，便于取出是验证正确结果
            for (int i = 0; i < 10; i++) {
                fileWriter.write(String.valueOf(Long.MAX_VALUE - i) + System.lineSeparator());
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("用时：" + (System.currentTimeMillis() - start));
        }
    }

    public void addToTopKQueue(Long target) {
        if (priorityQueue.size() < 10) {
            priorityQueue.add(target);
        } else {
            Long head = priorityQueue.peek();
            if (target > head) {
                priorityQueue.poll();
                priorityQueue.add(target);
            }
        }
    }

}

```

实际运行： 实际上，最优的解决方案应该是最符合实际设计需求的方案，在实际应用中，可能有足够大的内存，那么直接将数据扔到内存中一次性处理即可。也可能机器有多个核，这样可以采用多线程处理整个数据集。多线程处理时，上述方法需要做线程安全保证。


