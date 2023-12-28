package com.example.supportlearningjp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.supportlearningjp.repo")
//@EntityScan(basePackages="com.example.supportlearningjp.model")
//@ComponentScan(basePackages = "com.example.supportlearningjp.config")
//@ComponentScan(basePackages = "com.example.supportlearningjp.dto")
//@ComponentScan(basePackages = "com.example.supportlearningjp.controller")
//@ComponentScan(basePackages = "com.example.supportlearningjp.exception")
//@ComponentScan(basePackages = "com.example.supportlearningjp.fillter")
//@ComponentScan(basePackages = "com.example.supportlearningjp.service")
//@ComponentScan(basePackages = "com.example.supportlearningjp.util")
public class SupportLearningJpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupportLearningJpApplication.class, args);
    }

}
