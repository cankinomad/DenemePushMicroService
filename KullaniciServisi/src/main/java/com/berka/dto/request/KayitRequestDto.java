package com.berka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KayitRequestDto {

    @NotBlank(message = "Kullanici adi bos birakilamaz")
    private String kullaniciadi;
    @NotBlank(message = "Sifre bos biraklamaz")
    @Size(min = 2,max = 32,message = "Sifre en az 2, en fazla 32 karakterden olusmalidir")
    private String sifre;
    @Email(message = "girdiginiz email duzgun formatta degildir")
    @NotBlank(message = "Email bos birakilamaz")
    private String email;
}
