@[TOC](文章目录)

# 前言
我们都知道ArrayList是线程不安全的，那么它不安全在哪里？又会出现什么并发问题呢？

<hr style=" border:solid; width:100px; height:1px;" color=#000000 size=1">

<font color=#999AAA >提示：以下是本篇文章正文内容，下面案例可供参考

# 一、ArrayList源码摘录
```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    /**
     * 列表元素集合数组
     * 说明ArrayList基于数组存储数据
     */
    transient Object[] elementData; 

    /**
     * 列表大小，elementData中存储的元素个数
     */
    private int size;
}
```

add() 方法

```java
/**
 * Appends the specified element to the end of this list.
 * 将指定的元素追加到列表的末尾。
 * add() 方法做了如下操作：
 *     1.检查容量是否足够，如不够将进行扩容，并自增 modCount
 *     2.将指定的元素追加到列表的末尾
 *
 * @param e element to be appended to this list
 * @return <tt>true</tt> (as specified by {@link Collection#add})
 */
public boolean add(E e) {
    //确保容量足够，如果不够进行扩容
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    //将e存在index为size的位置（即最后一位的下一位置），size++
    //我们都知道，++操作不是原子指令，多线程情况下将发生并发问题
    elementData[size++] = e;
    return true;
}
```

# 二、测试用例

```java
@Test
public void listThreadUnsafe() throws InterruptedException {
    List<String> list = new ArrayList<>();
    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                list.add("t1-" + i);
            }
        }
    });
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                list.add("t2-" + i);
            }
        }
    });
    t1.start();
    t2.start();

    Thread.sleep(2000);
    int size = list.size();
    System.out.println("size = " + size);
    for (int i = 0; i < size; i++) {
        System.out.println("索引为" + i + "的元素为：" + list.get(i));
    }
}
```
本用例多跑几次，将出现下面几种并发问题。

# 三、ArrayList线程不安全的表现
add()实际执行的过程为：
```java
elementData[size] = e;
size = size + 1;
```
## 1.  并发环境下进行add操作时可能会导致elementData数组越界
```java
问题现场如下：
有两个线程：t1，t2。有ArrayList size=9（即其中有9个元素）。elementData.length=10
t1进入add()方法，这时获取到size值为9，调用ensureCapacityInternal()方法判断容量是否需要扩容
t2也进入add()方法，这时获取到size值也为9，也调用ensureCapacityInternal()方法判断容量是否需要扩容
t1发现自己的需求为size+1=10，容量足够，无需扩容
t1发现自己的需求为也size+1=10，容量足够，无需扩容
t1开始设置元素操作，elementData[size++] = e，成功，此时size变为10
t2也开始进行设置元素操作，它尝试设置elementData[10] = e，而elementData没有进行过扩容，它的下标最大为9。于是此时会报出一个数组越界的异常：ArrayIndexOutOfBoundsException

```
用例体现为：
![测试结果](https://img-blog.csdnimg.cn/20200827112447557.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmRvdTk1Mjc=,size_16,color_FFFFFF,t_70#pic_center)


## 2. 一个线程的值覆盖另一个线程添加的值
这个问题要分多钟情况了

### 2.1 情况1 size大小符合预期，但是中间有null值存在

流程描述如下：

```java
问题现场如下：
有两个线程：t1，t2。有ArrayList size=5（即其中有5个元素）。elementData.length=10
t1进入add()方法，这时获取到size值为5，调用ensureCapacityInternal()方法判断容量是否需要扩容
t2也进入add()方法，这时获取到size值也为5，也调用ensureCapacityInternal()方法判断容量是否需要扩容
t1发现自己的需求为size+1=6，容量足够，无需扩容
t1发现自己的需求为也size+1=6，容量足够，无需扩容
t1开始设置元素操作，elementData[size] = e，成功，
t2也开始设置元素操作，elementData[size] = e，成功，注意此时t1的size+1还没执行
t1 size = size + 1 = 6，并写入主存
t2 size = size + 1 = 7
这样，size符合预期，但是t2设置的值被覆盖，而且索引为6的位置将永远为null，因为size已经为7，下次add()也会从7开始。除非手动set值。

```
用例体现如下：
![测试结果](https://img-blog.csdnimg.cn/20200827140802221.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmRvdTk1Mjc=,size_16,color_FFFFFF,t_70#pic_center)


我们发现，t2的“t2-0”元素被覆盖。

### 2.2 情况2 size大小比预期的小
情况分析：

```java
问题现场如下：
有两个线程：t1，t2。有ArrayList size=5（即其中有5个元素）。elementData.length=10
t1进入add()方法，这时获取到size值为5，调用ensureCapacityInternal()方法判断容量是否需要扩容
t2也进入add()方法，这时获取到size值也为5，也调用ensureCapacityInternal()方法判断容量是否需要扩容
t1发现自己的需求为size+1=6，容量足够，无需扩容
t1发现自己的需求为也size+1=6，容量足够，无需扩容
t1开始设置元素操作，elementData[size] = e，成功，
t2也开始设置元素操作，elementData[size] = e，成功，注意此时t1的size+1还没执行
t1 size = size + 1 = 6，暂未写入主存
t2 size = size + 1 此时因为t1操作完size还未写入主存，所以size依然为5，+1后仍为6
t1将size=6 写入主存
t2将size=6 写入主存
这样，size=6 比预期结果小了。

```
用例体现：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200827142521167.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmRvdTk1Mjc=,size_16,color_FFFFFF,t_70#pic_center)



<hr style=" border:solid; width:100px; height:10px;" color=#000000 size=50">

# 总结
上面介绍的情况都有其出现的概率，并不是每次都出现，只是在临界状态下出现错误。但是，作为程序的编写者，即使有千万分之一的概率，我们也要尽量去避免它，这是程序员的基本素养。

## Tips
关于写入主存。
基本现在的CPU都是多核心的，每个核心有各自的高速缓存，计算任务需要在高速缓存中进行，对于缓存的访问速度 `L1 > L2 > L3 > 内存`。L1、L2为各核心独有，L3为多个核心共享。
我们的程序运行在主内存，但计算需要在CPU中完成。
当执行计算任务时，比如 `size+1` 操作，CPU先将 `size` 的值读进CPU缓存，在CPU缓存中计算 +1，然后再将结果写入主内存。