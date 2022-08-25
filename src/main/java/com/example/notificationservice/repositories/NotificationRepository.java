package com.example.notificationservice.repositories;

import com.example.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification,Long> {

    @Query(value = "SELECT * FROM notification WHERE email =?1", nativeQuery = true)
    List<Notification> getByEmail(String email);
}
