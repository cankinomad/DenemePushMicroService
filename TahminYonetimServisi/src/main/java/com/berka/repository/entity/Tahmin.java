package com.berka.repository.entity;

import com.berka.repository.enums.EDogruluk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tahmin extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    Long kullaniciid;
    Long sehirid;
    String tahmin;
    @Enumerated(EnumType.STRING)
    EDogruluk dogruluk;
}
