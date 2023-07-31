package com.berka.manager;

import com.berka.dto.request.TahminControlRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.berka.constant.EndPoints.TAHMINCONTROL;

@FeignClient(url = "http://localhost:9091/api/v1/sehir",decode404 = true,name = "tahmin-sehirmangaer")
public interface ISehirManager {

    @PostMapping(TAHMINCONTROL)
     ResponseEntity<Boolean> tahminControl(@RequestBody TahminControlRequestDto dto);

}
