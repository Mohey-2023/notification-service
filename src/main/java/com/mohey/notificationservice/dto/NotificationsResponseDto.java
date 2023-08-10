package com.mohey.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NotificationsResponseDto {
    List<NoticeResponseDto> notices;
    List<FriendNotificationResponseDto> friendNotifications;
    List<GroupNotificationResponseDto> groupNotifications;
}
