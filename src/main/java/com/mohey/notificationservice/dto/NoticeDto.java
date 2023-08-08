package com.mohey.notificationservice.dto;

import lombok.Data;

@Data
public class NoticeDto {
    private String type; // 긴급 공지 (urgent), 일반 공지 (general)
    private NoticeDetailDto noticeDetailDto;
}
