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
        log.info("모임 참여 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 참여 DB 삽입 완료");
        fcmNotificationService.sendPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-reject")
    public void groupReject(String kafkaMessage) throws IOException {
        log.info("모임 거절 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 거절 DB 삽입 완료");
        fcmNotificationService.sendPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-invite")
    public void groupInvite(String kafkaMessage) throws IOException {
        log.info("모임 초대 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 초대 DB 삽입 완료");
        fcmNotificationService.sendPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-join")
    public void groupJoin(String kafkaMessage) throws IOException {
        log.info("모임 참여 신청 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 참여 신청 DB 삽입 완료");
        fcmNotificationService.sendPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-kick")
    public void groupKick(String kafkaMessage) throws IOException {
        log.info("모임 방출 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 방출 DB 삽입 완료");
        fcmNotificationService.sendPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-delegate")
    public void groupDelegate(String kafkaMessage) throws IOException {
        log.info("모임 방장 권한 위임 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 방장 권한 위임 DB 삽입 완료");
        fcmNotificationService.sendPersonal(kafkaMessage);
    }
}