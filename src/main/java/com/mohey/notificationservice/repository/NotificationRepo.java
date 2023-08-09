package com.mohey.notificationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mohey.notificationservice.document.NotificationDocument;

import java.util.Date;
import java.util.List;

public interface NotificationRepo extends MongoRepository<NotificationDocument,String> {
    List<NotificationDocument> findByReceiverUuidAndTypeAndCreatedTimeAfter(String memberUuid, String type, Date date);
}
