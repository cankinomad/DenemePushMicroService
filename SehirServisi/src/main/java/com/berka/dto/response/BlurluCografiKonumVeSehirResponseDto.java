package com.berka.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlurluCografiKonumVeSehirResponseDto {

    private String blurlucografikonum;
    private String blurlusehirismi;
}
