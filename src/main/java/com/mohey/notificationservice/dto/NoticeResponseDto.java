package com.mohey.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class NoticeResponseDto {
    private String type;
    private String title;
    private String body;
    private Date createdTime;
}
