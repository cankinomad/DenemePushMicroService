package com.berka.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KullaniciSkorGuncelleModel implements Serializable {

    Long kullaniciid;
    Long skor;
}
