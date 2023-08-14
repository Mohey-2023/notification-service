package com.mohey.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.notificationservice.document.NotificationDocument;
import com.mohey.notificationservice.dto.MemberNotificationDto;
import com.mohey.notificationservice.repository.NotificationRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class FriendService {
    private final int timeDiff = 9 * 60 * 60 * 1000;
    private NotificationRepo notificationRepo;

    public void insertFriendNoti(String kafkaMessage){
        ObjectMapper mapper = new ObjectMapper();
        Date currentTime = new Date(new Date().getTime() + timeDiff);
        log.info("currentTime : " + currentTime);
        try{
            MemberNotificationDto memberNotificationDto = mapper.readValue(kafkaMessage, MemberNotificationDto.class);
            log.info("user Noti : " + memberNotificationDto);
            NotificationDocument document = NotificationDocument.builder()
                    .topic(memberNotificationDto.getTopic())
                    .type(memberNotificationDto.getType())
                    .senderUuid(memberNotificationDto.getSenderUuid())
                    .senderName(memberNotificationDto.getSenderName())
                    .receiverUuid(memberNotificationDto.getReceiverUuid())
                    .receiverName(memberNotificationDto.getReceiverName())
                    .createdTime(currentTime)
                    .build();
            log.info("document = " + document);
            notificationRepo.save(document);
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
    }
}
