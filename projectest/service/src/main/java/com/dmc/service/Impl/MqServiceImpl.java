package com.dmc.service.Impl;

import com.dmc.Result;
import com.dmc.SendTemplate;
import com.dmc.service.MqService;
import org.apache.rocketmq.client.producer.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix="topictest1")
public class MqServiceImpl implements MqService {

    private static final Logger log = LoggerFactory.getLogger(MqServiceImpl.class);
    @Autowired
    SendTemplate sendTemplate;

    private String topic;

    private String tag;

    @Override
    public String send(String msg) {
        Result<SendResult> result = sendTemplate.synSend(topic, tag, msg);
        if (result.getData() != null) {
            log.info(result.toString());
            return result.toString();
        }
        return result.getMsg();
    }

    @Override
    public String asyncSend(String msg) {
        sendTemplate.asynSend(topic, tag, msg);
        return "sendOK!";
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
