package com.mohey.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationRedisService {
    private StringRedisTemplate redisTemplate;
    private ObjectMapper mapper;

    public boolean hasUnreadNotifications(String memberUuid) {
        String key = "notificationStatus:" + memberUuid;
        if(!redisTemplate.hasKey(key)){
            redisTemplate.opsForValue().set(key, "read", 30, TimeUnit.DAYS);
        }
        return "unread".equals(redisTemplate.opsForValue().get(key));
    }

    public void setUnreadStatus(String kafkaMessage) throws JsonProcessingException {
        JsonNode jsonNode = mapper.readTree(kafkaMessage);
        String memberUuid = jsonNode.path("receiverUuid").asText();
        log.info("memberUuid : " + memberUuid);
        String key = "notificationStatus:" + memberUuid;
        redisTemplate.opsForValue().set(key,"unread");
    }

    public void setReadStatus(String memberUuid){
        String key = "notificationStatus:" + memberUuid;
        redisTemplate.opsForValue().set(key,"read");
    }
}
