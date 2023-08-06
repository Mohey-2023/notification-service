package com.mohey.notificationservice.consumer;

import com.mohey.notificationservice.service.FCMNotificationService;
import com.mohey.notificationservice.service.FriendService;
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
    private FCMNotificationService fcmNotificationService;

    @KafkaListener(topics="friend-request")
    public void friendRequest(String kafkaMessage) throws IOException {
        log.info("친구 신청 : " + kafkaMessage);
        friendService.insertFriendNoti(kafkaMessage);
        log.info("친구신청 DB 삽입 완료");
        fcmNotificationService.sendPersonal(kafkaMessage);
    }

    @KafkaListener(topics = "friend-accept")
    public void friendAccept(String kafkaMessage){
        log.info("친구 수락 : " + kafkaMessage);
        friendService.insertFriendNoti(kafkaMessage);
    }
}
