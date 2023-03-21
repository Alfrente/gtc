package com.gtc.mapper;

import com.gtc.service.dto.response.AsignaturaRestDto;
import com.gtc.service.dto.response.model.DescripcionResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDescripcionMapper {

    @Mapping(target = "descripcion", source = "descripcion")
    DescripcionResDto aDescripcion(AsignaturaRestDto asignaturaRestDto);
}
