package com.groovehub.groovehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class GrooveHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrooveHubApplication.class, args);
    }
}
