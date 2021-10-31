package com.example.finalproject;

import com.example.finalproject.service.CustomEmailServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebSecurity
@EnableConfigurationProperties
public class FinalProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }
}
