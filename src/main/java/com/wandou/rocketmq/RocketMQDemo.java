package com.wandou.rocketmq;

import com.alibaba.fastjson.JSON;
import com.wandou.common.RocketMQConstant;
import com.wandou.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liming
 * @date 2020-03-31
 * @description
 */
@Slf4j
@Component
public class RocketMQDemo {
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Autowired
    private RedisUtil redisUtil;


    public void sendToDEMO0819_TOPIC(int circulationCount) {
        try {
            if (circulationCount <= 0) {
                circulationCount = 2;
            }
            for (int i = 0; i < circulationCount; i++) {
                Message message = new Message();
                message.setTopic(RocketMQConstant.DEMO0819_TOPIC);
                message.setKeys("key" + redisUtil.randomIncId());
                message.setTags("matter_log");
                Map<String, Object> msgBodyMap = new HashMap<>();
                msgBodyMap.put("lala", "springbootDemo0819 mq 发 - " + redisUtil.randomIncId());
                msgBodyMap.put("userId", RandomUtils.nextInt(1, 25));
                msgBodyMap.put("mType", RandomUtils.nextInt(1, 4));
                log.info("msgBodyMap: {}", msgBodyMap);
                message.setBody((JSON.toJSONString(msgBodyMap)).getBytes());
                SendResult sendResult = defaultMQProducer.send(message);
                log.info("sendResult: {}", sendResult);
            }
        } catch (Exception e) {
            log.error("mq send 异常", e);
        }
    }

}
