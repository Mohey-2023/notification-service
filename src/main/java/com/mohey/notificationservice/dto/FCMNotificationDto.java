package com.mohey.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FCMNotificationDto {
    private String fcmToken;
    private String type;
    private String title;
    private String body;
}
