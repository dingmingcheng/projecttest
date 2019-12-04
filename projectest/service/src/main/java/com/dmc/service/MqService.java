package com.dmc.service;

public interface MqService {
    String send(String msg);

    String asyncSend(String msg);
}
