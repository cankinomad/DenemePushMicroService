package com.berka.mapper;

import com.berka.rabbitmq.model.KullaniciSkorGuncelleModel;
import com.berka.repository.entity.Skor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ISkorMapper {

    ISkorMapper INSTANCE = Mappers.getMapper(ISkorMapper.class);

    KullaniciSkorGuncelleModel toKullaniciSkorGuncelleModel(final Skor skor);
}
