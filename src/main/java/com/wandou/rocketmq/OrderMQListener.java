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
@RocketMQMessageListener(topic = RocketMQConstant.ORDER_TOPIC, consumerGroup = RocketMQConstant.TEST_CONSUMER_GROUP)
public class OrderMQListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(MessageExt message) {
        log.info("======================= OrderMQListener收到mq消息,msg={}", message);
        String msgId = message.getMsgId();
        String tags = message.getTags();
        log.info("msgId: {}, msgKey: {}, tags: {}", msgId, message.getKeys(), tags);
        log.info("msgBody: {}", message.getBody());
        log.info("msgBodyStr: {}", new String(message.getBody()));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setInstanceName(RocketMQConstant.TEST_CONSUMER_GROUP + "InstanceName");
    }
}
