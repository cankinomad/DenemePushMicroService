package com.berka.mapper;

import com.berka.dto.request.TahminControlRequestDto;
import com.berka.dto.request.TahminRequestDto;
import com.berka.rabbitmq.model.SkorGuncelleModel;
import com.berka.repository.entity.Tahmin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITahminMapper {
    ITahminMapper INSTANCE = Mappers.getMapper(ITahminMapper.class);

    TahminControlRequestDto toTahminControlRequestDto(final Tahmin tahmin);

    SkorGuncelleModel toSkorGuncelleModel(final Tahmin tahmin);
}
