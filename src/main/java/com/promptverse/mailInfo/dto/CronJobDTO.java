package com.promptverse.mailInfo.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CronJobDTO {
    private Long id;
    private LocalDateTime scheduledTime;
    private String emailType;
}
