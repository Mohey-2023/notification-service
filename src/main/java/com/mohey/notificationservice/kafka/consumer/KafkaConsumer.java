package com.mohey.notificationservice.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohey.notificationservice.repository.NotificationRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {
    private NotificationRepo notificationRepo;

    @Autowired
    public KafkaConsumer(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }


}
