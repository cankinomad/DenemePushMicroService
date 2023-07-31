package com.berka.controller;

import static com.berka.constant.EndPoints.*;

import com.berka.dto.request.TahminRequestDto;
import com.berka.repository.entity.Tahmin;
import com.berka.service.TahminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(TAHMIN)
@RequiredArgsConstructor
public class TahminController {

    private final TahminService service;

    @Operation(summary = "Tum Tahminleri getirir")
    @GetMapping(FINDALL)
    public ResponseEntity<List<Tahmin>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Tahmin yap",description = "Kullanicilar oncelikle aldiklari tokenlari buraya girer ve token uzerinden kullanici tahmin hakki kontrol edilir." +
            "tahmin hakki olan kullanicilarin tahminlerinin dogrulugu feignclient ile kontrol edilir. Burdan cikan sonuc SkorServisi ile rabbitmq uzerinden" +
            "iletisime gecilir ve orada bu kisinin skoru varsa guncellenir, yoksa yeni bir skor olusturulup kaydedilir.")
    @PostMapping(YAP)
    public ResponseEntity<String> tahminYap(@RequestBody TahminRequestDto dto) {
        return ResponseEntity.ok(service.tahminYap(dto));
    }
}
