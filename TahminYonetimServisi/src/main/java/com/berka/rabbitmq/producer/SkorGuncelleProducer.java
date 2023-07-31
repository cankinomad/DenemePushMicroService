package com.berka.rabbitmq.producer;

import com.berka.rabbitmq.model.SkorGuncelleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkorGuncelleProducer {

    private final RabbitTemplate rabbitTemplate;


    @Value("${rabbitmq.tahminExchange}")
    private String tahminExchange;

    @Value("${rabbitmq.skorGuncelleBindingKey}")
    private String skorGuncelleBindingKey;

    public void skorGuncelle(SkorGuncelleModel model) {
        rabbitTemplate.convertAndSend(tahminExchange,skorGuncelleBindingKey,model);
    }
}
