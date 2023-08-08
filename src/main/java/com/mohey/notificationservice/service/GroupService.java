package com.mohey.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.notificationservice.document.NotificationDocument;
import com.mohey.notificationservice.dto.GroupNotificationDto;
import com.mohey.notificationservice.dto.MemberNotificationDetailDto;
import com.mohey.notificationservice.repository.NotificationRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GroupService {
    private NotificationRepo notificationRepo;

    public void insertGroupNoti(String kafkaMessage){
        ObjectMapper mapper = new ObjectMapper();
        try{
            GroupNotificationDto groupNotificationDto = mapper.readValue(kafkaMessage,GroupNotificationDto.class);
            log.info("group noti : " + groupNotificationDto);
            for(MemberNotificationDetailDto memberNotificationDetailDto: groupNotificationDto.getMemberNotificationDetailDtoList()) {
                NotificationDocument document = NotificationDocument.builder()
                        .topic(groupNotificationDto.getTopic())
                        .type(groupNotificationDto.getType())
                        .senderUuid(groupNotificationDto.getSenderUuid())
                        .senderName(groupNotificationDto.getSenderName())
                        .receiverUuid(memberNotificationDetailDto.getReceiverUuid())
                        .receiverName(memberNotificationDetailDto.getReceiverName())
                        .groupInfoDto(groupNotificationDto.getGroupNotificationDetailDto())
                        .build();
                log.info("document = " + document);
                notificationRepo.save(document);
            }
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
    }

}
