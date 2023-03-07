package com.gtc.mapper;

import com.gtc.persistence.entity.Dane;
import com.gtc.service.dto.request.DaneInpDto;
import com.gtc.service.dto.response.DaneResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ITipoDaneMapper.class})
public interface IDaneMapper extends IMapper<Dane, DaneInpDto, DaneResDto>{
    @Override
    @Mapping(target = "tipoDane", ignore = true)
    @Mapping(target = "id", ignore = true)
    Dane aEntidad(DaneInpDto daneInpDto);

    @Override
    @Mapping(target = "tipoDaneResDto", source = "tipoDane")
    DaneResDto aOutDto(Dane dane);
}
