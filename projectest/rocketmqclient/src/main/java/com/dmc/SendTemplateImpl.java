package com.dmc;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SendTemplateImpl implements SendTemplate {


    @Autowired
    @Qualifier("defaultMQProducer")
    DefaultMQProducer producer;

    @Override
    public Result<SendResult> synSend(String topic, String tag, String msgBody) {
        Message message = new Message(topic, tag, msgBody.getBytes());
        try {
            SendResult sendResult = producer.send(message);
            return Result.successResult(sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.errorResult();
    }

    @Override
    public Result<SendResult> asynSend(String topic, String tag, String msgBody) {
        //Message message = new Message(topic, tag, msgBody.getBytes());
        return Result.errorResult();
    }

    @Override
    public Result<SendResult> onewaySend(String topic, String tag, String msgBody) {
        Message message = new Message(topic, tag, msgBody.getBytes());
        return Result.errorResult();
    }

    @Override
    public Result<SendResult> consistSend() {
        return null;
    }


}
