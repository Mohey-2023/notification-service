package com.mohey.notificationservice.document;

import com.mohey.notificationservice.dto.NoticeDetailDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "notices")
@ToString
public class NoticeDocument {
    private String type;
    private NoticeDetailDto noticeDetailDto;
    private Date createdTime;

    @Builder
    public NoticeDocument(String type, NoticeDetailDto noticeDetailDto,Date createdTime) {
        this.type = type;
        this.noticeDetailDto = noticeDetailDto;
        this.createdTime = createdTime;
    }
}
