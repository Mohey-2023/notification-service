package com.mohey.notificationservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserNotificationDetailDto {
    private String receiverUuid;
    private String receiverName;
    private List<String> deviceTokenList;
}