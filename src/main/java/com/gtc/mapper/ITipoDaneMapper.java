package com.gtc.mapper;

import com.gtc.persistence.entity.TipoDane;
import com.gtc.service.dto.request.TipoDaneInpDto;
import com.gtc.service.dto.response.TipoDaneResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITipoDaneMapper extends IMapper<TipoDane, TipoDaneInpDto, TipoDaneResDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    TipoDane aEntidad(TipoDaneInpDto tipoDaneInpDto);
}
