package com.wandou.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constant {

    public static final Map<String, Long> tokenUserIdMap = new ConcurrentHashMap<>();
    static {
        tokenUserIdMap.put("aaab1", 1L);
        tokenUserIdMap.put("aaab2", 2L);
        tokenUserIdMap.put("aaab3", 3L);
    }
}
