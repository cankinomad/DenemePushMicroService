package com.berka.mapper;

import com.berka.dto.request.KayitRequestDto;
import com.berka.dto.response.KayitResponseDto;
import com.berka.repository.entity.Kullanici;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IKullaniciMapper {

    IKullaniciMapper INSTANCE = Mappers.getMapper(IKullaniciMapper.class);

    Kullanici toKullanici(final KayitRequestDto dto);

    KayitResponseDto toKayitResponseDto(final Kullanici kullanici);
}
