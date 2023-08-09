package com.mohey.notificationservice.repository;

import com.mohey.notificationservice.document.NoticeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface NoticeRepo extends MongoRepository<NoticeDocument,String> {
    List<NoticeDocument> findByCreatedTimeAfter(Date date);
}
