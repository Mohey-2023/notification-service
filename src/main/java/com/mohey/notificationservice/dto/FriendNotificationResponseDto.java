package com.mohey.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FriendNotificationResponseDto {
    private String topic;
    private String senderUuid;
    private String senderName;
    private Date createdTime;
}
