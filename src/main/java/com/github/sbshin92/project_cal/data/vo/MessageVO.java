package com.github.sbshin92.project_cal.data.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MessageVO {
    private int messageId;
    private int senderUserId;
    private int receiverUserId;
    private String messageTitle;
    private String messageDescription;
    private String readStatus;
    private Timestamp createdAt;
    
    // 조인용 필드
    private String senderUserName;
    private String receiverUserName;
}
