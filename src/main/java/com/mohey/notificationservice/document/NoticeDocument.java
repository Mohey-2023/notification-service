package com.mohey.notificationservice.document;

import com.mohey.notificationservice.dto.NoticeDetailDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "notices")
@ToString
public class NoticeDocument {
    private String type;
    private NoticeDetailDto noticeDetailDto;
    @CreatedDate
    private Date createdTime;

    @Builder
    public NoticeDocument(String type, NoticeDetailDto noticeDetailDto) {
        this.type = type;
        this.noticeDetailDto = noticeDetailDto;
    }
}
