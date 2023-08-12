package com.mohey.notificationservice.consumer;

import com.mohey.notificationservice.service.FcmNotificationService;
import com.mohey.notificationservice.service.FriendService;
import com.mohey.notificationservice.service.NotificationRedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class FriendListener {

    private FriendService friendService;
    private FcmNotificationService fcmNotificationService;
    private NotificationRedisService notificationRedisService;

    @KafkaListener(topics="friend-request")
    public void friendRequest(String kafkaMessage) throws IOException {
        log.info("친구 신청 : " + kafkaMessage);
        friendService.insertFriendNoti(kafkaMessage);
        log.info("친구신청 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendMemberPersonal(kafkaMessage);
    }

    @KafkaListener(topics = "friend-accept")
    public void friendAccept(String kafkaMessage) throws IOException {
        log.info("친구 수락 : " + kafkaMessage);
        friendService.insertFriendNoti(kafkaMessage);
        log.info("친구수락 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendMemberPersonal(kafkaMessage);
    }
}
