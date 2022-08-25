package com.example.notificationservice;

import com.example.notificationservice.model.Notification;
import com.example.notificationservice.repositories.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/notifications")
public class NotificationController {

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/{email}")
    public ResponseEntity<List<Notification>> findUserById(@PathVariable(value = "email") String email) {
        Optional<List<Notification>> notifications = Optional.ofNullable(notificationRepository.getByEmail(email));
        System.out.println("This Colled");
        if(notifications.isPresent()) {
            return ResponseEntity.ok().body(notifications.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Notification> findAllNotifications() {
        return (List<Notification>) notificationRepository.findAll();
    }
}
