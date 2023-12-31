package com.berka.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.kullaniciSkorGuncelleQueue}")
    private String kullaniciSkorGuncelleQueue;


    @Bean
    Queue kullaniciSkorGuncelleQueue() {
        return new Queue(kullaniciSkorGuncelleQueue);
    }


}



