package com.dmc.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix="consumer")
public class Consumer implements BeanFactoryPostProcessor{

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    /**
     * 消费者的组名
     */
    private String consumerGroup;

    /**
     * NameServer 地址
     */
    private String namesrvAddr;

    /**
     * Topic名称
     */
    private String topic;

    /**
     * Tag标签名称，对消息进行再归类
     */
    private String tag;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            //确定topic和tag
            consumer.subscribe(topic, tag);

            //从上次消费的地方开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                for (MessageExt msg : list) {
                    String topic = new String(msg.getBody());
                    String tags = new String(msg.getTags());
                    String msgBody = new String(msg.getBody());
                    log.info("topic is {}, tags is {}, msg body is {}", topic, tags, msgBody);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
            log.info("consumer is started.........");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
