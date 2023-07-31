package com.berka.rabbitmq.consumer;

import com.berka.rabbitmq.model.KullaniciSkorGuncelleModel;
import com.berka.service.KullaniciServisi;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KullaniciSkorGuncelleConsumer {

    private final KullaniciServisi kullaniciServisi;


    @RabbitListener(queues = "${rabbitmq.kullaniciSkorGuncelleQueue}")
    public void kullaniciSkorGuncelle(KullaniciSkorGuncelleModel model) {
        kullaniciServisi.skorGuncelle(model);
    }
}
