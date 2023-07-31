package com.berka.controller;

import static com.berka.constant.EndPoints.*;

import com.berka.dto.request.SkorGuncelleRequestDto;
import com.berka.rabbitmq.model.SkorGuncelleModel;
import com.berka.repository.entity.Skor;
import com.berka.service.SkorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SKOR)
@RequiredArgsConstructor
public class SkorController {

    private final SkorService service;

    @Operation(summary = "Tum Kullanicilarin skorlarini getirir")
    @GetMapping(FINDALL)
    public ResponseEntity<List<Skor>> findall() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Skor tablosuna kullanici ekler veya varsa gunceller",description = "Bu metod erisimi disariya kapanacaktir.")
    @PostMapping(EKLE)
    public ResponseEntity<String> skorGuncelle(@RequestBody SkorGuncelleModel model) {
        return ResponseEntity.ok(service.skorGuncelle(model));
    }
    @Operation(summary = "Skoru buyukten kucuge dogru olan kullanicilari siralar")
    @GetMapping(SIRALA)
    public ResponseEntity<List<Skor>> sirala() {
        return ResponseEntity.ok(service.sirala());
    }

}
