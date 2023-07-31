package com.berka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TahminRequestDto {

    @NotBlank
    String token;
    @NotBlank
    String tahmin;
    @NotNull
    Long sehirid;

}
