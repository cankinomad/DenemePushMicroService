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


    @Value("${rabbitmq.tahminExchange}")
    private String tahminExchange;

    @Value("${rabbitmq.skorGuncelleQueue}")
    private String skorGuncelleQueue;

    @Value("${rabbitmq.skorGuncelleBindingKey}")
    private String skorGuncelleBindingKey;




    @Bean
    DirectExchange exchangeTahmin() {
        return new DirectExchange(tahminExchange);
    }

    @Bean
    Queue skorGuncelleQueue() {
        return new Queue(skorGuncelleQueue);
    }

    @Bean
    public Binding skorGuncelleBinding(final DirectExchange exchangeTahmin,final Queue skorGuncelleQueue) {
        return BindingBuilder.bind(skorGuncelleQueue).to(exchangeTahmin).with(skorGuncelleBindingKey);
    }


}


