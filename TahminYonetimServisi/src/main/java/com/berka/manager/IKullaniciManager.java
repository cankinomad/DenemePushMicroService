package com.berka.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.berka.constant.EndPoints.TAHMINHAKKINIGUNCELLE;

@FeignClient(url ="http://localhost:9090/api/v1/kullanici",decode404 = true,name = "tahmin-kullanicimanager")
public interface IKullaniciManager {

    @PutMapping(TAHMINHAKKINIGUNCELLE)
    ResponseEntity<Boolean> tahminHakkiControl(@RequestParam Long userid);
}
