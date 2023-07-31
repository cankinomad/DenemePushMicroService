package com.berka.controller;

import static com.berka.constant.EndPoints.*;

import com.berka.dto.request.GirisRequestDto;
import com.berka.dto.request.KayitRequestDto;
import com.berka.dto.response.KayitResponseDto;
import com.berka.rabbitmq.model.KullaniciSkorGuncelleModel;
import com.berka.repository.entity.Kullanici;
import com.berka.service.KullaniciServisi;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(KULLANICI)
@RequiredArgsConstructor
public class KullaniciController {

    private final KullaniciServisi service;

    @Operation(summary = "Kullanici kaydeder",description = "Gerekli donusum islemlerini ve kontrolleri yaparak kullaniciyi kaydeder")
    @PostMapping(KAYIT)
    public ResponseEntity<KayitResponseDto> saveKullanici(@RequestBody @Valid KayitRequestDto dto) {
        return ResponseEntity.ok(service.saveKullanici(dto));
    }

    @Operation(summary = "Kullanci giris",description = "kullanici adi ve sifre dogru girilmesi durumunda oturum acilir ve token doner.")
    @PostMapping(GIRIS)
    public ResponseEntity<String> giris(GirisRequestDto dto) {
        return ResponseEntity.ok(service.giris(dto));
    }


    @Operation(summary = "Kullanci listeler",description = "Sistemdeki butun kullanicilari listeler")
    @GetMapping(LISTELE)
    public ResponseEntity<List<Kullanici>> hepsiniGetir() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Kullanci skorunu gunceller",description = "Bu metod kullanici tarafindan kullanilmaz.SkorServisten donen skoru burda guncellemek icin kullanilir" +
            "Ileriki surumlerde security eklenerek bu metodun erisimi kisitlanacaktir.")
    @PutMapping(SKORGUNCELLE)
    public ResponseEntity<Boolean> skorGuncelle(@RequestBody KullaniciSkorGuncelleModel model) {
        return ResponseEntity.ok(service.skorGuncelle(model));
    }

    @Operation(summary = "Kullanci tahmin hakkini gunceller",description = "tahmin servisinde tahmin yapan kullanicilar her tahmini icin 1 tahmin hakki kaybeder, yeni tahmin " +
            "hak bilgisini guncelleyen metoddur")
    @PutMapping(TAHMINHAKKINIGUNCELLE)
    public ResponseEntity<Boolean> tahminHakkiControl(@RequestParam Long userid) {
        return ResponseEntity.ok(service.tahminHakkiControl(userid));
    }
    @Operation(summary = "Kullanci tahmin hakkini gunceller",description = "tahmin hakki biten kullanicilar buradan tahmin haklarini yenileyebilirler.")
    @PutMapping(HAKKIMIYENILE)
    public ResponseEntity<String> hakYenile(String token) {
        return ResponseEntity.ok(service.hakYenile(token));
    }


    @PostMapping(FINDBYTOKEN)
    public ResponseEntity<KayitResponseDto> findByToken(String token) {
        return ResponseEntity.ok(service.findByToken(token));
    }

}
