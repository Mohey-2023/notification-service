package com.mohey.notificationservice.document;

import java.util.Date;

import com.mohey.notificationservice.dto.GroupNotificationDetailDto;
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
    private String senderUuid;
    private String senderName;
    private String receiverUuid;
    private String receiverName;
    private GroupNotificationDetailDto groupInfoDto;
    private Date createdTime;

    @Builder
    public NotificationDocument(String topic, String type, String senderUuid, String senderName, String receiverUuid, String receiverName, GroupNotificationDetailDto groupInfoDto, Date createdTime) {
        this.topic = topic;
        this.type = type;
        this.senderUuid = senderUuid;
        this.senderName = senderName;
        this.receiverUuid = receiverUuid;
        this.receiverName = receiverName;
        this.groupInfoDto = groupInfoDto;
        this.createdTime = createdTime;
    }
}
