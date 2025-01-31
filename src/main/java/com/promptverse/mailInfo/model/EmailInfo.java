package com.promptverse.mailInfo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "email_info")
public class EmailInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_details_id", nullable = false)
    private ToDetails toDetails;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Column(name = "is_responded", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isResponded = false;

    @Column(name = "response_date")
    private LocalDateTime responseDate;

    @Column(name = "response_content", columnDefinition = "TEXT")
    private String responseContent;

    @ElementCollection
    @CollectionTable(name = "sendgrid_ids_table", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "sendgrid_id")
    private List<String> sendgridIds = new ArrayList<>();


    @OneToMany(mappedBy = "emailInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CronJob> cronJobs = new ArrayList<>();

}