package com.mohey.notificationservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.notificationservice.dto.FCMNotificationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class PersonalProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    public FCMNotificationDto send(String topic, FCMNotificationDto fcmNotificationDto) {
        log.info("topic : " + topic);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(fcmNotificationDto);
            log.info("json : " + jsonInString);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
        log.info("fcm 푸시 서비스에 데이터를 보냈습니다 : " + fcmNotificationDto);
        return fcmNotificationDto;
    }
}
