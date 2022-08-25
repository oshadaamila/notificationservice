package com.example.notificationservice.listener;

import com.example.notificationservice.model.Notification;
import com.example.notificationservice.repositories.NotificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class KafkaConsumer {

    @Autowired
    private NotificationRepository notificationRepository;

    @KafkaListener(topics = "notificationq")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(message, Map.class);
            System.out.println(map);
            Notification notification = new Notification();
            notification.setEmail(map.get("authorEmail"));
            notification.setNotification(map.get("title") + " created at " + map.get("timestamp"));
            notificationRepository.save(notification);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}