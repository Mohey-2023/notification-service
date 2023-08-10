package com.mohey.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.notificationservice.document.NoticeDocument;
import com.mohey.notificationservice.dto.*;
import com.mohey.notificationservice.producer.PersonalProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class FcmNotificationService {

    private PersonalProducer personalProducer;
    private  ObjectMapper mapper;

    public void sendMemberPersonal(String kafkaMessage) throws IOException {
        mapper = new ObjectMapper();
        Map<String, Map<String, String>> templates = loadTemplates(mapper);
        try{
            MemberNotificationDto memberNotificationDto = mapper.readValue(kafkaMessage, MemberNotificationDto.class);
            log.info("memberNotificationDto = " + memberNotificationDto);
            String topic = memberNotificationDto.getTopic();
            Map<String, String> template = templates.get(topic);
            String title = template.get("title");
            String body = template.get("body")
                    .replace("{senderName}", memberNotificationDto.getSenderName());

            for (String fcmToken : memberNotificationDto.getDeviceTokenList()) {
                log.info("fcmToken : " + fcmToken);
                FCMNotificationDto fcmNotificationDto =
                        new FCMNotificationDto(fcmToken,memberNotificationDto.getTopic(),title, body);
                log.info("fcmNotiDto : " + fcmNotificationDto);
                personalProducer.send("personal-push", fcmNotificationDto);
            }
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
    }
    public void sendGroupPersonal(String kafkaMessage) throws IOException {
        mapper = new ObjectMapper();
        Map<String, Map<String, String>> templates = loadTemplates(mapper);
        try{
            GroupNotificationDto groupNotificationDto = mapper.readValue(kafkaMessage, GroupNotificationDto.class);
            log.info("groupNotificationDto = " + groupNotificationDto);
            String topic = groupNotificationDto.getTopic();
            Map<String, String> template = templates.get(topic);
            String title = template.get("title");
            String body = template.get("body")
                    .replace("{senderName}", groupNotificationDto.getSenderName());
            if(groupNotificationDto.getGroupNotificationDetailDto() != null){
                 body = body.replace("{groupName}", groupNotificationDto.getGroupNotificationDetailDto().getGroupName());
            }
            for(MemberNotificationDetailDto memberNotificationDetailDto : groupNotificationDto.getMemberNotificationDetailDtoList()){
                for (String fcmToken : memberNotificationDetailDto.getDeviceTokenList()) {
                    log.info("fcmToken : " + fcmToken);
                    FCMNotificationDto fcmNotificationDto =
                            new FCMNotificationDto(fcmToken,groupNotificationDto.getTopic(),title, body);
                    log.info("fcmNotiDto : " + fcmNotificationDto);
                    personalProducer.send("personal-push", fcmNotificationDto);
                }
            }
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
    }

    public void sendUrgentNotice(String kafkaMessage) throws IOException{
        try {
            NoticeDto noticeDto = mapper.readValue(kafkaMessage, NoticeDto.class);
            log.info("notice dto: " + noticeDto);
            FCMNoticeDto fcmNoticeDto = FCMNoticeDto.builder()
                    .topic("urgent-notice")
                    .title(noticeDto.getNoticeDetailDto().getTitle())
                    .body(noticeDto.getNoticeDetailDto().getBody())
                    .build();
            log.info("fcmNoticeDto : " + fcmNoticeDto);
            personalProducer.send("all-push", fcmNoticeDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Map<String, String>> loadTemplates(ObjectMapper mapper) throws IOException, IOException {
        ClassPathResource resource = new ClassPathResource("template/notification_template.json");
        return mapper.readValue(resource.getInputStream(), new TypeReference<Map<String, Map<String, String>>>() {});
    }
}
