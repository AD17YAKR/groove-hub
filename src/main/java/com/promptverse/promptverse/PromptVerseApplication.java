package com.promptverse.promptverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = {"com.promptverse"})
@EntityScan("com.promptverse")
@EnableJpaRepositories(basePackages = "com.promptverse")
@ComponentScan(basePackages = {"com.promptverse.common", "com.promptverse.llms"},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = com.promptverse.llms.config.LLMConfig.class))

public class PromptVerseApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromptVerseApplication.class, args);
    }
}
