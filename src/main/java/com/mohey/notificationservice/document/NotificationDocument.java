package com.mohey.notificationservice.document;

import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "notifications")
@ToString
public class NotificationDocument {
    private String topic;
    private String type;
    private String receiverUuid;
    private String receiverName;
    private String senderName;
    private Map<String, Object> groupInfoDto;
    @CreatedDate
    private Date createdTime;

    @Builder
    public NotificationDocument(String topic, String type, String receiverUuid,
                                String receiverName, String senderName, Map<String, Object> groupInfoDto) {
        this.topic = topic;
        this.type = type;
        this.receiverUuid = receiverUuid;
        this.receiverName = receiverName;
        this.senderName = senderName;
        this.groupInfoDto = groupInfoDto;
    }
}
