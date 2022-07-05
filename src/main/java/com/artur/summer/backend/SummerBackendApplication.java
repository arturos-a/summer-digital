package com.artur.summer.backend;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SummerBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SummerBackendApplication.class, args);
    }
}
