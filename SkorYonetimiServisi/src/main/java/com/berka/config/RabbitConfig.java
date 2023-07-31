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

    @Value("${rabbitmq.exchangeSkor}")
    private String exchangeSkor;
    @Value("${rabbitmq.kullaniciSkorGuncelleQueue}")
    private String kullaniciSkorGuncelleQueue;
    @Value("${rabbitmq.kullaniciSkorGuncelleBindingKey}")
    private String kullaniciSkorGuncelleBindingKey;



    @Value("${rabbitmq.skorGuncelleQueue}")
    private String skorGuncelleQueue;


    @Bean
    Queue skorGuncelleQueue() {
        return new Queue(skorGuncelleQueue);
    }


    @Bean
    DirectExchange exchangeSkor() {
        return new DirectExchange(exchangeSkor);
    }

    @Bean
    Queue kullaniciSkorGuncelleQueue() {
        return new Queue(kullaniciSkorGuncelleQueue);
    }

    @Bean
    public Binding kullaniciSkorGuncelleBinding(final DirectExchange exchangeSkor, final Queue kullaniciSkorGuncelleQueue) {
        return BindingBuilder.bind(kullaniciSkorGuncelleQueue).to(exchangeSkor).with(kullaniciSkorGuncelleBindingKey);
    }
}



