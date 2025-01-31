package com.promptverse.mailInfo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cron_job")
public class CronJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_info_id", nullable = false)
    private EmailInfo emailInfo;

    @Column(name = "scheduled_time", nullable = false)
    private LocalDateTime scheduledTime;

    @Column(name = "email_type", nullable = false, length = 32)
    private String emailType;

}