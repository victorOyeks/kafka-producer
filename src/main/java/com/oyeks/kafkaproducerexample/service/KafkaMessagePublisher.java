package com.oyeks.kafkaproducerexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.StringReader;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaMessagePublisher {


    private final KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("oyeks-topic", message);
        future.whenComplete((result, ex)->{

            if(ex == null){
                System.out.println("Sendt message = [" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}
