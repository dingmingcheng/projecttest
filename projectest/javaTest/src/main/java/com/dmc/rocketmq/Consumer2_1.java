package com.dmc.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer2_1 {
    private static final Logger log = LoggerFactory.getLogger(Consumer2_1.class);

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rmq-group2");

        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("TopicA-test", "TagA");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                String topic = new String(msg.getTopic());
                String tags = new String(msg.getTags());
                String msgBody = new String(msg.getBody());
                log.info("topic is {}, tags is {}, msg body is {}", topic, tags, msgBody);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        System.out.println("Consumer2_1 Started.");
    }
}
