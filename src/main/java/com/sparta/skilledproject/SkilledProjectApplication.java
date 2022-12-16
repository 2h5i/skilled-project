package com.sparta.skilledproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class SkilledProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkilledProjectApplication.class, args);
    }

}
