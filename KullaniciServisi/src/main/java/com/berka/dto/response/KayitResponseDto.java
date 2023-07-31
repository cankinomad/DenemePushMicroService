package com.berka.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KayitResponseDto {

    private Long id;
    private String kullaniciadi;
    private String email;
    private Long skor;
    private int tahminhakki;
}
