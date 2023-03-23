package com.gtc.mapper;

import com.gtc.persistence.entity.Dane;
import com.gtc.service.dto.request.DaneInpDto;
import com.gtc.service.dto.response.DaneResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ITipoDaneMapper.class})
public interface IDaneMapper extends IMapper<Dane, DaneInpDto, DaneResDto>{

    @Mapping(target = "tipoDane", ignore = true)
    @Mapping(target = "id", ignore = true)
    Dane aEntidad(DaneInpDto daneInpDto);

    @Mapping(target = "tipoDaneResDto", source = "tipoDane")
    DaneResDto aOutDto(Dane dane);

    DaneInpDto aInpDt(Dane dane);
}
