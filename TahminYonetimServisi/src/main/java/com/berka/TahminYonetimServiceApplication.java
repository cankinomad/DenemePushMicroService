package com.berka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TahminYonetimServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TahminYonetimServiceApplication.class);
    }
}