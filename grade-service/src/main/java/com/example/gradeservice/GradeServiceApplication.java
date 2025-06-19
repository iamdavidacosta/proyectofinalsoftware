package com.example.gradeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.gradeservice.entity")
public class GradeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradeServiceApplication.class, args);
    }

}
