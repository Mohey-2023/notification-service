package com.mohey.notificationservice.controller;

import com.mohey.notificationservice.dto.FriendNotificationResponseDto;
import com.mohey.notificationservice.dto.GroupNotificationResponseDto;
import com.mohey.notificationservice.dto.NoticeResponseDto;
import com.mohey.notificationservice.dto.NotificationsResponseDto;
import com.mohey.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(value="/{memberUuid}",produces = "application/json;charset=UTF-8")
    public NotificationsResponseDto getNotifications(@PathVariable("memberUuid") String memberUuid){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        List<NoticeResponseDto> notices = notificationService.getRecentNotices(calendar.getTime());
        List<FriendNotificationResponseDto> friendNotifications = notificationService.getRecentFriendNotifications(memberUuid, calendar.getTime());
        List<GroupNotificationResponseDto> groupNotifications = notificationService.getRecentGroupNotifications(memberUuid,calendar.getTime());
        return new NotificationsResponseDto(notices, friendNotifications,groupNotifications);
    }
}
