package com.mohey.notificationservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemberNotificationDto {
    private String topic;
    private String type;
    private String senderUuid;
    private String senderName;
    private String receiverUuid;
    private String receiverName;
    private List<String> deviceTokenList;
}
