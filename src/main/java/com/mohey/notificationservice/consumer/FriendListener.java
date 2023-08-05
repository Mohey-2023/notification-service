package com.mohey.notificationservice.consumer;

import com.mohey.notificationservice.service.FriendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class FriendListener {

    private FriendService friendService;

    @KafkaListener(topics="friend-request")
    public void friendRequest(String kafkaMessage){
        log.info("친구 신청 : " + kafkaMessage);
        friendService.insertFriendNoti(kafkaMessage);
    }

    @KafkaListener(topics = "friend-accept")
    public void friendAccept(String kafkaMessage){
        log.info("친구 수락 : " + kafkaMessage);
        friendService.insertFriendNoti(kafkaMessage);
    }
}
