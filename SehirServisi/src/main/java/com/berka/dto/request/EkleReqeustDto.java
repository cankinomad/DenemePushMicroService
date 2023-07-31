package com.berka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EkleReqeustDto {

    private String sehirtamisim;
    private String sehirplaka;
    private String sehirBolgesi;
}
