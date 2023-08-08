package com.mohey.notificationservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class FCMNoticeDto {
    private String topic;
    private String title;
    private String body;

    @Builder
    public FCMNoticeDto(String topic, String title, String body) {
        this.topic = topic;
        this.title = title;
        this.body = body;
    }
}
