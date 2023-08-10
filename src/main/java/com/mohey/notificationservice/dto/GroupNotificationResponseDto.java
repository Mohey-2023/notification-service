package com.mohey.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GroupNotificationResponseDto {
    private String topic;
    private String senderUuid;
    private String senderName;
    private String groupUuid;
    private String groupName;
    private Date createdTime;
}
