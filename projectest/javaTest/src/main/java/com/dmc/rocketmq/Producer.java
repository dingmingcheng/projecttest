package com.dmc.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Value;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;

import java.util.Date;

public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("rmq-producer-group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000); //每5秒发送一次MQ
                Message msg = new Message("TopicA-test", "TagA", (new Date() + " Hello dmc ,QuickStart" + i).getBytes());
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
