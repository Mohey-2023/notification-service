package com.mohey.notificationservice.service;

import com.mohey.notificationservice.document.NoticeDocument;
import com.mohey.notificationservice.document.NotificationDocument;
import com.mohey.notificationservice.dto.FriendNotificationResponseDto;
import com.mohey.notificationservice.dto.GroupNotificationResponseDto;
import com.mohey.notificationservice.dto.NoticeResponseDto;
import com.mohey.notificationservice.repository.NoticeRepo;
import com.mohey.notificationservice.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    private final NoticeRepo noticeRepo;
    private final NotificationRepo notificationRepo;

    @Autowired
    public NotificationService(NoticeRepo noticeRepo, NotificationRepo notificationRepo) {
        this.noticeRepo = noticeRepo;
        this.notificationRepo = notificationRepo;
    }

    public List<NoticeResponseDto> getRecentNotices(Date date){
        List<NoticeResponseDto> noticeResponseDtoList = new ArrayList<>();
        List<NoticeDocument> notices = noticeRepo.findByCreatedTimeAfter(date);
        for(NoticeDocument noticeDocument: notices){
            NoticeResponseDto noticeResponseDto = new NoticeResponseDto(
                noticeDocument.getType(),
                noticeDocument.getNoticeDetailDto().getTitle(),
                noticeDocument.getNoticeDetailDto().getBody(), noticeDocument.getCreatedTime()
            );
            noticeResponseDtoList.add(noticeResponseDto);
        }
        return noticeResponseDtoList;
    }

    public List<FriendNotificationResponseDto> getRecentFriendNotifications(String memberUuid,Date date){
        List<FriendNotificationResponseDto> friendNotificationResponseDtoList = new ArrayList<>();
        List<NotificationDocument> notifications = notificationRepo.findByReceiverUuidAndTypeAndCreatedTimeAfter(memberUuid,"friend",date);
        for(NotificationDocument notificationDocument: notifications){
            FriendNotificationResponseDto friendNotificationResponseDto = new FriendNotificationResponseDto(
                    notificationDocument.getTopic(),
                    notificationDocument.getSenderUuid(),
                    notificationDocument.getSenderName(),
                    notificationDocument.getCreatedTime()
            );
            friendNotificationResponseDtoList.add(friendNotificationResponseDto);
        }
        return friendNotificationResponseDtoList;
    }

    public List<GroupNotificationResponseDto> getRecentGroupNotifications(String memberUuid, Date date){
        List<GroupNotificationResponseDto> groupNotificationResponseDtoList = new ArrayList<>();
        List<NotificationDocument> notifications = notificationRepo.findByReceiverUuidAndTypeAndCreatedTimeAfter(memberUuid,"group",date);
        for(NotificationDocument notificationDocument: notifications){
            GroupNotificationResponseDto groupNotificationResponseDto = new GroupNotificationResponseDto(
                    notificationDocument.getTopic(),
                    notificationDocument.getSenderUuid(),
                    notificationDocument.getSenderName(),
                    notificationDocument.getGroupInfoDto().getGroupUuid(),
                    notificationDocument.getGroupInfoDto().getGroupName(),
                    notificationDocument.getCreatedTime()
            );
            groupNotificationResponseDtoList.add(groupNotificationResponseDto);
        }
        return groupNotificationResponseDtoList;
    }
}
