package com.mohey.notificationservice.consumer;

import com.mohey.notificationservice.service.FcmNotificationService;
import com.mohey.notificationservice.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class NoticeListener {
    private NoticeService noticeService;
    private FcmNotificationService fcmNotificationService;

    @KafkaListener(topics="urgent-notice")
    public void noticeUrgent(String kafkaMessage) throws IOException {
        log.info("긴급 공지 : " + kafkaMessage);
        noticeService.insertNotice(kafkaMessage);
        log.info("긴급 공지 DB 삽입 완료");
        fcmNotificationService.sendUrgentNotice(kafkaMessage);
    }
}
