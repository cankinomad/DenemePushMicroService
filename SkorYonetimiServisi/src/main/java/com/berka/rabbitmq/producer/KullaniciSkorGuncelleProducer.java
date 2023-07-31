package com.berka.rabbitmq.producer;

import com.berka.rabbitmq.model.KullaniciSkorGuncelleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KullaniciSkorGuncelleProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.kullaniciSkorGuncelleBindingKey}")
    private String kullaniciSkorGuncelleBindingKey;

    @Value("${rabbitmq.exchangeSkor}")
    private String exchangeSkor;

    public void kullaniciSkorGuncelle(KullaniciSkorGuncelleModel model) {
        rabbitTemplate.convertAndSend(exchangeSkor,kullaniciSkorGuncelleBindingKey,model);
    }
}
