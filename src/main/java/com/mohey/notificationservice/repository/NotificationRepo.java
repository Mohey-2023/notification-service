package com.mohey.notificationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mohey.notificationservice.document.NotificationDocument;

public interface NotificationRepo extends MongoRepository<NotificationDocument,String> {
}
