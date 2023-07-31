package com.berka.rabbitmq.consumer;

import com.berka.rabbitmq.model.SkorGuncelleModel;
import com.berka.service.SkorService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkorGuncelleConsumer {

    private final SkorService service;

    @RabbitListener(queues = "${rabbitmq.skorGuncelleQueue}")
    public void skorGuncelle(SkorGuncelleModel model) {
        service.skorGuncelle(model);
    }
}
