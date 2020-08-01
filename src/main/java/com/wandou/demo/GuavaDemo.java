package com.wandou.demo;

import com.google.common.base.Joiner;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author liming
 * @date 2020/7/27
 */
public class GuavaDemo {

    /**
     * 布隆过滤器
     */
    @Test
    public void t1() {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10000, 0.001);
        bloomFilter.put("abc");
        bloomFilter.put("java");
        bloomFilter.put("mysql");
        bloomFilter.put("redis");
        bloomFilter.put("spring");

        /**
         * 存在为可能存在，不存在一定不存在。因为存在可能是因为哈希冲突
         */
        boolean mightContain = bloomFilter.mightContain("abc");
        System.out.println("mightContain = " + mightContain);
        boolean mightContain1 = bloomFilter.mightContain("ab");
        System.out.println("mightContain1 = " + mightContain1);
        boolean mightContain2 = bloomFilter.mightContain("spring");
        System.out.println("mightContain2 = " + mightContain2);

    }

    @Test
    public void tCommon() {
        Joiner joiner = Joiner.on("-").skipNulls();
        String join = joiner.join("java", "php", "python", "c");
        System.out.println("join = " + join);

    }

}
