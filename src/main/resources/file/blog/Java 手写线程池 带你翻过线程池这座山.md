如果想玩转 Java 的多线程与高并发，线程池是你永远也绕不过的山。既然绕不过，我们就啃他，吃透线程池，玩转高并发。

### Jdk里的线程池
#### 主要属性

```java
private volatile int corePoolSize; //核心线程数
private volatile int maximumPoolSize; //最大线程数
private volatile long keepAliveTime; //存活时间
private final BlockingQueue<Runnable> workQueue; //任务等待队列
private volatile ThreadFactory threadFactory; //线程工厂
private volatile RejectedExecutionHandler handler; //拒绝策略

```
下面介绍一下线程池执行任务的流程，理解各个属性的意义。当一个线程池初始化，向线程池提交任务，线程池新建线程执行任务，随着线程创建，线程数逐渐增多，当达到 `corePoolSize` 线程池将不再新建线程，而是将任务放入任务等待队列 `workQueue` 。再持续向线程池提交任务，当等待队列满了，这时会继续新建线程，直到到达最大线程数 `maximumPoolSize`，如果还继续有任务到来，线程池无法处理，这时就启动拒绝策略。 

这个过程我们可以以生活中的例子比喻一下。大致我们把线程池理解为理发店。那么流程就是：来了顾客开始理发，比如只有4个理发师4个座位，相当于核心线程。那么来了过多的顾客，理发师忙不过来就会先让你去等候区稍等排队等待，前面有理完发的会叫你，相当于等待队列。等待区满了呢？现实中理发店肯定不会拒绝顾客的啊，他可能让你先在外面等。但如果等待区每天都爆满，那么老板可能会考虑扩大店面，扩充理发师团队了。所以，这只是个大致的比喻。

### 自己手写线程池

手写线程池，代码：

```java
public class MyThreadPoolV3 {

    /**
     * 核心线程数（理发师数量）
     */
    private volatile int corePoolSize;
    /**
     * 任务等待队列（等待区座位数）
     */
    private final BlockingQueue<Runnable> workQueue;
    /**
     * 线程容器（理发师作业区）
     */
    private List<Worker> workers;

    public MyThreadPoolV3(int corePoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.workQueue = workQueue;
        workers = Collections.synchronizedList(new ArrayList<>(corePoolSize));
        for (int i = 0; i < corePoolSize; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    /**
     * 将任务加到队列（给顾客安排位置）
     *
     * @param task
     * @return
     */
    public boolean submit(Runnable task) {
        return workQueue.offer(task);
    }


    // ----------------

    /**
     * 线程（理发师）
     */
    private class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                try {
                    //从任务队列取任务，如空则阻塞等待。（去等候区叫顾客，如果没有则原地等待一会）
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null) {
                    //取到任务开始工作（开始理发）
                    task.run();
                }
            }
        }
    }
}
```

测试用例代码：

```java
public class ThreadPoolDemoV3 {

    private static LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(30);
    private static MyThreadPoolV3 threadPoolV3 = new MyThreadPoolV3(3, workQueue);

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(atomicInteger.incrementAndGet()
                        + "号顾客来理发，为其理发的理发师是："
                        + Thread.currentThread().getName());
            }
        };
        for (int i = 0; i < 30; i++) {
            threadPoolV3.submit(task);
        }
    }
}
```

测试结果：
![测试结果](https://img-blog.csdnimg.cn/20200809184650907.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmRvdTk1Mjc=,size_16,color_FFFFFF,t_70)
我们发现无论来多少顾客理发，始终是0、1、2理发师轮流工作。这就是线程池的作用，实现了线程的复用，节省了资源，提升了程序性能。

当然，这个线程池只是个简版，让我们理解线程池的流程。和jdk的线程池比还有很多功能没有完善，等待队列满了将新建线程执行，直到达到最大线程数；线程的存活时间；拒绝策略等。

