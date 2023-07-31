package com.berka.rabbitmq.model;

import com.berka.repository.enums.EDogruluk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkorGuncelleModel implements Serializable {

    Long kullaniciid;
    EDogruluk dogruluk;
}
