package com.berka.dto.request;

import com.berka.repository.enums.EDogruluk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkorGuncelleRequestDto {

    Long kullaniciid;
    EDogruluk dogruluk;
}
