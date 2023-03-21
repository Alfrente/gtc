package com.gtc.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IMapper<ENTIDAD, DTOINPUT, DTOOPUT> {
    ENTIDAD aEntidad(DTOINPUT dto);
    DTOOPUT aOutDto(ENTIDAD entidad);
    List<ENTIDAD> aEntidadList(List<DTOINPUT> dtoList);
    List<DTOOPUT> aOutDtoList(List<ENTIDAD> entidadList);
}
