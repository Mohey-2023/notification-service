package com.mohey.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.notificationservice.dto.BaseNotificationDto;
import com.mohey.notificationservice.dto.FCMNotificationDto;
import com.mohey.notificationservice.producer.PersonalProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FCMNotificationService{

    private PersonalProducer personalProducer;

    public void sendPersonal(String kafkaMessage){
        ObjectMapper mapper = new ObjectMapper();
        try{
            BaseNotificationDto baseNotificationDto = mapper.readValue(kafkaMessage,BaseNotificationDto.class);
            log.info("baseNotificationDto = " + baseNotificationDto);
            String title = "title입니다.";
            String body = "body입니다.";
            for (String fcmToken : baseNotificationDto.getDeviceTokenList()) {
                log.info("fcmToken : " + fcmToken);
                FCMNotificationDto fcmNotificationDto =
                        new FCMNotificationDto(fcmToken,title, body);
                log.info("fcmNotiDto : " + fcmNotificationDto);
                personalProducer.send("personal-push", fcmNotificationDto);
            }
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
    }
}
