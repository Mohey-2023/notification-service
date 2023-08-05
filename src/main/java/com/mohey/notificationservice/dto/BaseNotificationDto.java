package com.mohey.notificationservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class BaseNotificationDto {
    private String topic;
    private String type;
    private String receiverUuid;
    private String receiverName;
    private String senderName;
    private List<String> deviceTokenList;
    private GroupNotificationDto groupNotificationDto;
}
