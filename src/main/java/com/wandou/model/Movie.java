package com.wandou.model;

import lombok.Data;

import java.io.Serializable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author: liming
 * @date: 2019/7/12 13:50
 * @description:
 * @modify:
 */

@Data
public class Movie implements Serializable, Delayed {

    private Long id;

    private String name;

    private Long price;

    /**
     * 延迟时长(毫秒)
     */
    private Long delay;

    private Long executionTime;

    public Movie() {
    }

    public Movie(String name, Long price, long delay) {
        this.name = name;
        this.price = price;
        this.delay = delay;
        this.executionTime = System.currentTimeMillis() + delay;
    }

    /**
     * 延时队列取数据时传的纳秒 first.getDelay(NANOSECONDS)
     *
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long delay = unit.convert(this.executionTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        // 这种返回毫秒的方式将造成资源浪费，因为队列取数据时是根据此方法返回的时间让线程await，单位是纳秒，以毫秒单位返回的时间数字将比纳秒的小
        // 那么不到执行时间线程将被多次唤醒，直到真正到执行时间取出数据。
        // 以纳秒返回将不会有这种现象，实际返回纳秒，线程等待相应的纳秒数，被唤醒一次即可拿到数据。
//        long delay = this.executionTime - System.currentTimeMillis();
        System.out.println("delay: " + delay);
        return delay;
    }

    @Override
    public int compareTo(Delayed o) {
        return this.executionTime.compareTo(((Movie) o).getExecutionTime());
    }
}
