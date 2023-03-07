package com.gtc.mapper;

import com.gtc.service.dto.response.AsignaturaRestDto;
import com.gtc.service.dto.response.sin_otra_clase.DescripcionResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDescripcionMapper {
    DescripcionResDto aDescripcion(AsignaturaRestDto asignaturaRestDto);
}
