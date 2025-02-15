package com.promptverse.mailInfo.controller;

import com.promptverse.mailInfo.service.MailInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/mail")
public class MailInfoController {

    private final MailInfoService mailInfoService;

    public MailInfoController(MailInfoService mailInfoService) {
        this.mailInfoService = mailInfoService;
    }

    @PostMapping({ "/send-test", "/send-test/"} )
    public ResponseEntity<Map<String, String>> sendTestEmail() {
        try {
            mailInfoService.sendDummyEmail();
            return ResponseEntity.ok(Collections.singletonMap("message", "Email sent successfully!"));
        } catch (GeneralSecurityException | IOException | MessagingException e) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", "Failed to send email: " + e.getMessage()));
        }
    }

}