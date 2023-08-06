package com.mohey.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.notificationservice.document.NotificationDocument;
import com.mohey.notificationservice.dto.BaseNotificationDto;
import com.mohey.notificationservice.dto.UserNotificationDetailDto;
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
            BaseNotificationDto baseNotificationDto = mapper.readValue(kafkaMessage,BaseNotificationDto.class);
            log.info("baseNoti : " + baseNotificationDto);
            for(UserNotificationDetailDto userNotificationDetailDto: baseNotificationDto.getUserNotificationDetailDtoList()) {
                NotificationDocument document = NotificationDocument.builder()
                        .topic(baseNotificationDto.getTopic())
                        .type(baseNotificationDto.getType())
                        .receiverUuid(userNotificationDetailDto.getReceiverUuid())
                        .receiverName(userNotificationDetailDto.getReceiverName())
                        .senderName(baseNotificationDto.getSenderName())
                        .groupInfoDto(baseNotificationDto.getGroupNotificationDto())
                        .build();
                log.info("document = " + document);
                notificationRepo.save(document);
            }
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
    }

}
