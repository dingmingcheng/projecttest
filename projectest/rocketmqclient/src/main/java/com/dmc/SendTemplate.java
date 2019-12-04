package com.dmc;

import org.apache.rocketmq.client.producer.SendResult;

public interface SendTemplate {
    /**
     * 同步发送
     */
    Result<SendResult> synSend(String topic, String tag, String msgBody);

    /**
     * 异步发送
     */
    Result<SendResult> asynSend(String topic, String tag, String msgBody);

    /**
     * 单向发送
     */
    Result<SendResult> onewaySend(String topic, String tag, String msgBody);

    /**
     *
     */
    Result<SendResult> consistSend();
}
