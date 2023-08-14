package com.mohey.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohey.notificationservice.document.NoticeDocument;
import com.mohey.notificationservice.dto.NoticeDto;
import com.mohey.notificationservice.repository.NoticeRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class NoticeService {
    private final int timeDiff = 9 * 60 * 60 * 1000;
    private NoticeRepo noticeRepo;

    public void insertNotice(String kafkaMessage){
        ObjectMapper mapper = new ObjectMapper();
        Date currentTime = new Date(new Date().getTime() + timeDiff);
        log.info("currentTime : " + currentTime);
        try {
            NoticeDto noticeDto = mapper.readValue(kafkaMessage, NoticeDto.class);
            log.info("notice dto: " + noticeDto);
            NoticeDocument document = NoticeDocument.builder()
                    .type(noticeDto.getType())
                    .noticeDetailDto(noticeDto.getNoticeDetailDto())
                    .build();
            log.info("document : " + document);
            noticeRepo.save(document);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
