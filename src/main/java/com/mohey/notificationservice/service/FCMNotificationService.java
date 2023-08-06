package com.mohey.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.notificationservice.dto.BaseNotificationDto;
import com.mohey.notificationservice.dto.FCMNotificationDto;
import com.mohey.notificationservice.producer.PersonalProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class FCMNotificationService{

    private PersonalProducer personalProducer;
    private  ObjectMapper mapper;

    public void sendPersonal(String kafkaMessage) throws IOException {
        mapper = new ObjectMapper();
        Map<String, Map<String, String>> templates = loadTemplates(mapper);
        try{
            BaseNotificationDto baseNotificationDto = mapper.readValue(kafkaMessage,BaseNotificationDto.class);
            log.info("baseNotificationDto = " + baseNotificationDto);
            String topic = baseNotificationDto.getTopic();
            Map<String, String> template = templates.get(topic);
            String title = template.get("title");
            String body = template.get("body").replace("{senderName}", baseNotificationDto.getSenderName());
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

    private Map<String, Map<String, String>> loadTemplates(ObjectMapper mapper) throws IOException, IOException {
        ClassPathResource resource = new ClassPathResource("template/notification_template.json");
        return mapper.readValue(resource.getInputStream(), new TypeReference<Map<String, Map<String, String>>>() {});
    }
}
