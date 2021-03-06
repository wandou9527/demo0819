package com.wandou.test;

import com.alibaba.fastjson.JSON;
import com.wandou.common.RocketMQConstant;
import com.wandou.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liming
 * @date 2020-03-20
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketMQTest {

    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Autowired
    private RedisUtil redisUtil;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 2020-03-20 发送 ok
     *
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    @Test
    public void m1() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 1000; j++) {
                Message message = new Message();
                message.setTopic(RocketMQConstant.LIMING_TEST_TOPIC);
                message.setKeys("" + i + "-" + j + "-" + redisUtil.randomIncId());
                message.setTags("boot fa");
                message.setBody(("springboot 发 - " + atomicInteger.incrementAndGet()).getBytes());
                SendResult sendResult = defaultMQProducer.send(message);
                log.info("sendResult: {}", sendResult);
            }
            TimeUnit.SECONDS.sleep(RandomUtils.nextLong(10, 30));
        }
    }


    @Test
    public void m2() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        int key = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 10; j++) {
                Message message = new Message(RocketMQConstant.ORDER_TOPIC,
                        "oTagA",
                        "key" + redisUtil.randomIncId(),
                        ("orderMsg" + RandomStringUtils.randomNumeric(6)).getBytes());
                SendResult sendResult = defaultMQProducer.send(message);
                log.info("OrderSendResult: {}", sendResult);
            }
        }
    }


    @Test
    public void m3() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message();
        message.setTopic(RocketMQConstant.LIMING_TEST_TOPIC);
        message.setKeys("key" + redisUtil.randomIncId());
        message.setTags("boot dan fa");
        message.setBody(("springboot 发 - " + redisUtil.randomIncId()).getBytes());
        SendResult sendResult = defaultMQProducer.send(message);
        log.info("sendResult: {}", sendResult);

    }

    @Test
    public void m4SendToDEMO0819_TOPIC() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message();
        message.setTopic(RocketMQConstant.DEMO0819_TOPIC);
        message.setKeys("key" + redisUtil.randomIncId());
        message.setTags("boot dan fa");
        Map<String, Object> msgBodyMap = new HashMap<>();
        msgBodyMap.put("lala", "springbootDemo0819 mq 发 - " + redisUtil.randomIncId());
        msgBodyMap.put("userId", null);
        System.out.println("msgBodyMap = " + msgBodyMap);
        message.setBody((JSON.toJSONString(msgBodyMap)).getBytes());
        SendResult sendResult = defaultMQProducer.send(message);
        log.info("sendResult: {}", sendResult);

    }

}
