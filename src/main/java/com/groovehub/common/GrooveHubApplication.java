package com.groovehub.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.groovehub"})
@EntityScan("com.groovehub")
@EnableJpaRepositories(basePackages = "com.groovehub.auth.repository")
public class GrooveHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrooveHubApplication.class, args);
    }
}
