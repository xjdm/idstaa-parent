package com.idstaa.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @author chenjie
 * @date 2021/1/3 17:44
 */
@EnableBinding({Sink.class})
public class MessageConsumerService {

    @StreamListener(Sink.INPUT)
    public void receiveMessage(Message<String> message){
        System.out.println("=====接收消息"+message);
    }
}
