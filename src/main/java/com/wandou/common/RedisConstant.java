package com.wandou.common;

/**
 * @author liming
 * @date 2020-03-22
 */
public class RedisConstant {
    /**
     * 消息计数
     */
    public static final String ROCKET_MQ_MSG_COUNT_KEY = Constant.APPLICATION_NAME + ":rocket_mq:msg_count";
    /**
     * 乱序随意的id 多处可用
     */
    public static final String RANDOM_INC_ID_KEY = Constant.APPLICATION_NAME + ":random_inc_id";
}
