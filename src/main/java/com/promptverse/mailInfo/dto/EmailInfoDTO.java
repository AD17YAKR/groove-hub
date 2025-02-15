package com.promptverse.mailInfo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmailInfoDTO {
    private Long id;
    private String subject;
    private String body;
    private Boolean isResponded;
    private LocalDateTime responseDate;
    private String responseContent;
    private List<String> gmailIds;
    private List<CronJobDTO> cronJobs;
}
