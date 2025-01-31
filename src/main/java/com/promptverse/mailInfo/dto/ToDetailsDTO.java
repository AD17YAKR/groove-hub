package com.promptverse.mailInfo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ToDetailsDTO {
    private Long id;
    private String companyEmail;
    private String companyName;
    private String jobLink;
    private List<EmailInfoDTO> emails;

}
