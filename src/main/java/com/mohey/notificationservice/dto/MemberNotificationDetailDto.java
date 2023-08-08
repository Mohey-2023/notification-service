package com.mohey.notificationservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemberNotificationDetailDto {
    private String receiverUuid;
    private String receiverName;
    private List<String> deviceTokenList;
}