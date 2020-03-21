package com.wandou.rocketmq;

import com.alibaba.fastjson.JSON;
import com.wandou.common.RocketMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

/**
 * @author liming
 * @date 2020-03-20
 */
@Service
@Slf4j
@RocketMQMessageListener(topic = RocketMQConstant.LIMING_TEST_TOPIC, consumerGroup = "test-consumer-group")
public class TestListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(MessageExt message) {
        log.info("======================= test收到mq消息,msg={}", message);
        String msgId = message.getMsgId();
        log.info("msgId: {}, msgKey: {}", msgId, message.getKeys());
        String tags = message.getTags();
        log.info("tags: {}", JSON.toJSONString(tags));
        log.info("msgBody: {}", message.getBody());
        log.info("msgBodyStr: {}", new String(message.getBody()));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setInstanceName("test-consumer-group");
    }
}
