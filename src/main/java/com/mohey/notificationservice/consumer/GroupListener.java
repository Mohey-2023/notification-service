package com.mohey.notificationservice.consumer;

import com.mohey.notificationservice.service.FcmNotificationService;
import com.mohey.notificationservice.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class GroupListener {

    private GroupService groupService;
    private FcmNotificationService fcmNotificationService;

    @KafkaListener(topics="group-affirm")
    public void groupAffirm(String kafkaMessage) throws IOException {
        log.info("그룹 참여 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("그룹 참여 DB 삽입 완료");
        fcmNotificationService.sendPersonal(kafkaMessage);
    }
}