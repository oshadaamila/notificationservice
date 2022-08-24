package com.example.notificationservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "notificationq")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}