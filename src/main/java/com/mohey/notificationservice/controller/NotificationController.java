package com.mohey.notificationservice.controller;

import com.mohey.notificationservice.dto.*;
import com.mohey.notificationservice.service.NotificationRedisService;
import com.mohey.notificationservice.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/notifications")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationRedisService notificationRedisService;

    @Autowired
    public NotificationController(NotificationService notificationService, NotificationRedisService notificationRedisService) {
        this.notificationService = notificationService;
        this.notificationRedisService = notificationRedisService;
    }

    @GetMapping(value="/{memberUuid}",produces = "application/json;charset=UTF-8")
    public ResponseEntity<NotificationsResponseDto> getNotifications(@PathVariable("memberUuid") String memberUuid){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        log.info("day : " + calendar);
        List<NoticeResponseDto> notices = notificationService.getRecentNotices(calendar.getTime());
        List<FriendNotificationResponseDto> friendNotifications = notificationService.getRecentFriendNotifications(memberUuid, calendar.getTime());
        List<GroupNotificationResponseDto> groupNotifications = notificationService.getRecentGroupNotifications(memberUuid,calendar.getTime());
        log.info("notices : " + notices);
        log.info("frienNotifications : " +friendNotifications);
        log.info("groupNotifications : " + groupNotifications);
        notificationRedisService.setReadStatus(memberUuid);
        NotificationsResponseDto notificationsResponseDto = new NotificationsResponseDto(notices, friendNotifications,groupNotifications);
        return new ResponseEntity<>(notificationsResponseDto, HttpStatus.OK);
    }

    @GetMapping(value="/status/{memberUuid}",produces = "application/json;charset=UTF-8")
    public ResponseEntity<NotificationStatusDto> checkNotificationStatus(@PathVariable("memberUuid") String memberUuid){
        boolean hasUnreadNotifications = notificationRedisService.hasUnreadNotifications(memberUuid);
        NotificationStatusDto notificationStatusDto = new NotificationStatusDto(hasUnreadNotifications);
        return new ResponseEntity<>(notificationStatusDto,HttpStatus.OK);
    }
}
