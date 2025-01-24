package com.promptverse.promptverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = {"com.promptverse"})
@EntityScan("com.promptverse")
@EnableJpaRepositories(basePackages = "com.promptverse")
public class PromptVerseApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromptVerseApplication.class, args);
    }
}
