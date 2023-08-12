package com.mohey.notificationservice.consumer;

import com.mohey.notificationservice.service.FcmNotificationService;
import com.mohey.notificationservice.service.GroupService;
import com.mohey.notificationservice.service.NotificationRedisService;
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
    private NotificationRedisService notificationRedisService;

    @KafkaListener(topics="group-affirm")
    public void groupAffirm(String kafkaMessage) throws IOException {
        log.info("모임 참여 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 참여 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-reject")
    public void groupReject(String kafkaMessage) throws IOException {
        log.info("모임 거절 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 거절 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-invite")
    public void groupInvite(String kafkaMessage) throws IOException {
        log.info("모임 초대 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 초대 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-join")
    public void groupJoin(String kafkaMessage) throws IOException {
        log.info("모임 참여 신청 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 참여 신청 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-kick")
    public void groupKick(String kafkaMessage) throws IOException {
        log.info("모임 방출 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 방출 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-delegate")
    public void groupDelegate(String kafkaMessage) throws IOException {
        log.info("모임 방장 권한 위임 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 방장 권한 위임 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }

    @KafkaListener(topics = "group-remind-leader")
    public void groupRemindLeader(String kafkaMessage) throws IOException{
        log.info("모임 1시간 10분 전 방장에게 확인 알림 : " + kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }
    @KafkaListener(topics="group-confirm")
    public void groupConfirm(String kafkaMessage) throws IOException {
        log.info("모임 확정 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 확정 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }

    @KafkaListener(topics="group-update")
    public void groupUpdate(String kafkaMessage) throws IOException {
        log.info("모임 수정 : " + kafkaMessage);
        groupService.insertGroupNoti(kafkaMessage);
        log.info("모임 수정 DB 삽입 완료");
        notificationRedisService.setUnreadStatus(kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }

    @KafkaListener(topics = "group-remind")
    public void groupRemind(String kafkaMessage) throws IOException{
        log.info("모임 30분 전 알림 : " + kafkaMessage);
        fcmNotificationService.sendGroupPersonal(kafkaMessage);
    }

}