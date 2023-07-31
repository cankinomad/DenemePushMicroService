package com.berka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class SkorYonetimServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkorYonetimServiceApplication.class);
    }
}