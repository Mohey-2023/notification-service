package com.mohey.notificationservice.repository;

import com.mohey.notificationservice.document.NoticeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeRepo extends MongoRepository<NoticeDocument,String> {
}
