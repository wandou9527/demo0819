package com.wandou.demo;

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

    @Test
    public void t1() {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10000, 0.001);
        bloomFilter.put("abc");
        bloomFilter.put("java");
        bloomFilter.put("mysql");

        /**
         * 存在为可能存在，不存在一定不存在。因为存在可能是因为哈希冲突
         */
        boolean mightContain = bloomFilter.mightContain("abc");
        System.out.println("mightContain = " + mightContain);
        boolean mightContain1 = bloomFilter.mightContain("ab");
        System.out.println("mightContain1 = " + mightContain1);

    }

}
