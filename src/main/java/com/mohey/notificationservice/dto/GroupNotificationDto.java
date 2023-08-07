package com.mohey.notificationservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupNotificationDto {
    private String topic;
    private String type;
    private String senderUuid;
    private String senderName;
    private List<MemberNotificationDetailDto> memberNotificationDetailDtoList;
    private GroupNotificationDetailDto groupNotificationDetailDto;
}
