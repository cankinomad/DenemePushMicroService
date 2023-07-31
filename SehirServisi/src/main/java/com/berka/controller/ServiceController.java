package com.berka.controller;

import static com.berka.constant.EndPoints.*;

import com.berka.dto.request.EkleReqeustDto;
import com.berka.dto.request.TahminControlRequestDto;
import com.berka.dto.response.BlurluCografiKonumVeSehirResponseDto;
import com.berka.repository.entity.Sehir;
import com.berka.service.SehirService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SEHIR)
@RequiredArgsConstructor
public class ServiceController {

    private final SehirService service;

    @Operation(summary = "Sehir ekler",description = "istenilen sehir eklenebilir. Eger sehir ismi sistemde kayitliysa hata firlatir.")
    @PostMapping(EKLE)
    public ResponseEntity<Sehir> ekle(@RequestBody EkleReqeustDto dto) {
        return ResponseEntity.ok(service.ekle(dto));
    }


    @Operation(summary = "Sehir ipucu",description = "Sehir id'si girilen sehrin adini ve cografi konumunun ilk 2 harfini ipucu olarka gosterir")
    @GetMapping("/{sehirid}")
    public ResponseEntity<BlurluCografiKonumVeSehirResponseDto> blurluSehirCografiKonumGetir(@PathVariable Long sehirid) {
        return ResponseEntity.ok(service.sehirCografiKonumGetir(sehirid));
    }

    @Operation(summary = "Tahminlerin dogrulugunu kontrol eder",description = "Kullanicilarin yaptigi sehir tahminlerinin dogrulugunu kontrol eder. Bu metoda" +
            "kullanicilar erismez, servisler kendi aralarinda haberlesip kontrolu kendisi yapar. Ilerleyen guncellemelerde bu metod erisimi kisitlanacaktir")
    @PostMapping(TAHMINCONTROL)
    public ResponseEntity<Boolean> tahminControl(@RequestBody TahminControlRequestDto dto) {
        return ResponseEntity.ok(service.tahminControl(dto));
    }
}
