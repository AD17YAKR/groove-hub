package com.promptverse.mailInfo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "to_details")
public class ToDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_email", nullable = false)
    private String companyEmail;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "job_link", nullable = false, length = 512)
    private String jobLink;

    @OneToMany(mappedBy = "toDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailInfo> emails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}