package com.mohey.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationStatusDto {
    private boolean hasUnreadNotifications;
}
