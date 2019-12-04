package com.dmc.mq.sendCallbacks;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicTest1Helper implements SendCallback {
    private static final Logger log = LoggerFactory.getLogger(TopicTest1Helper.class);

    @Override
    public void onSuccess(SendResult sendResult) {
        if (sendResult != null) {
            log.info("send result is " + sendResult.toString());
        }
    }

    @Override
    public void onException(Throwable e) {
        e.printStackTrace();
    }
}
